package com.example.smartHouse.controller;

import com.example.smartHouse.dto.AccommodationDto;
import com.example.smartHouse.dto.RedefineDto;
import com.example.smartHouse.dto.TakeDto;
import com.example.smartHouse.entity.Accommodation;
import com.example.smartHouse.service.AccommodationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @PostMapping("/register")
    public String registerAccommodation(@RequestBody AccommodationDto accommodationDto) {
        accommodationService.registerAccommodation(accommodationDto);
        return "success";
    }

    @PostMapping("/take")
    public Map<String, String> takeAccommodation(@RequestBody TakeDto takeDto) {
        Boolean b = accommodationService.take(takeDto.getId(), takeDto.getDate());
        Map<String, String> response = new HashMap<>();
        if (b) {
            response.put("message", "Accommodation taken successfully!");
            return response;
        }
        response.put("message", "ERROR");
        return response;
    }

    @PostMapping("/redefine")
    public ResponseEntity<?> redefineAccommodation(@Valid @RequestBody RedefineDto redefineDto, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body("{\"message\":\"" + errorMessage + "\"}");
        }
        boolean isRedefined = accommodationService.redefine(redefineDto.getId(), redefineDto.getDatumi());

        if (isRedefined) {
            return ResponseEntity.ok().body("{\"message\":\"Accommodation redefined successfully!\"}");
        } else {
            return ResponseEntity.ok().body("{\"message\":\"ERROR\"}");
        }
    }

}

package com.example.smartHouse.controller;

import com.example.smartHouse.dto.AccommodationDto;
import com.example.smartHouse.dto.TakeDto;
import com.example.smartHouse.entity.Accommodation;
import com.example.smartHouse.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
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

}

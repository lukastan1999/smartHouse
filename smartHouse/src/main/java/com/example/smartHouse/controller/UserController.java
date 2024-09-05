package com.example.smartHouse.controller;

import com.example.smartHouse.dto.LoginDto;
import com.example.smartHouse.dto.RegistrationRequest;
import com.example.smartHouse.entity.User;
import com.example.smartHouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
//        userService.registerUser(request);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body("Registration successful. Please check your email to activate your account.");
//    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody RegistrationRequest request) {
        userService.registerUser(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful. Please check your email to activate your account.");
        return response;
    }


    @PostMapping("/login")
    public User loginUser(@RequestBody LoginDto loginDto) {
        User user = userService.loginUser(loginDto);
        return user;
    }


    @GetMapping("/activate")
    public String activateUser(@RequestParam("token") String token) {
        boolean isActivated = userService.activateUser(token);
        if (isActivated) {
            return "Account activated successfully!";
        } else {
            return "Activation link is invalid or has expired. It is also possible " +
                    "that the account is activated. Please register again or log in.";
        }
    }

}

package com.example.smartHouse.controller;

import com.example.smartHouse.dto.RegistrationRequest;
import com.example.smartHouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest request) {
        userService.registerUser(request);
        return "Registration successful. Please check your email to activate your account.";
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

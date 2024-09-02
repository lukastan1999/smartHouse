package com.example.smartHouse.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;
    private String address;
    private String phoneNumber;
    private String role; // "HOST" or "GUEST"
}

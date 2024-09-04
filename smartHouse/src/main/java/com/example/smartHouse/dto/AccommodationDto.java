package com.example.smartHouse.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AccommodationDto {
    private Long userId;
    private String title;
    private String description;
    private String location;
    private String amenities;
    private Integer minGuest;
    private Integer maxGuest;
    private String type;
    private List<LocalDate> available;
    private Double price;
}


package com.example.smartHouse.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TakeDto {
    private Long id;
    private LocalDate date;
}

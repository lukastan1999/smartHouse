package com.example.smartHouse.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RedefineDto {
    private Long id;
    private List<LocalDate> datumi;
}

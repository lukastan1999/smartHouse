package com.example.smartHouse.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class RedefineDto {
    @NotNull(message = "Accommodation ID cannot be null.")
    private Long id;
    @NotEmpty(message = "Date list cannot be empty.")
    private List<LocalDate> datumi;
}

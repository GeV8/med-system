package com.example.medsystem.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicationResponseDto {
    private int id;
    private String name;
    private Integer quantity;
    private LocalDate expiryDate;
    private String unit;
}

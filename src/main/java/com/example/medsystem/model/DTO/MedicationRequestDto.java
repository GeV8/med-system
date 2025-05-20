package com.example.medsystem.model.DTO;


import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicationRequestDto {


    private String name;

    private int quantity;

    private LocalDate expiryDate;

    private String unit;
}

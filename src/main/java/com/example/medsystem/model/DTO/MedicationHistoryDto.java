package com.example.medsystem.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MedicationHistoryDto {
    private int id;
    private int quantityChange;
    private LocalDateTime changedAt;
    private String changedByUsername;
}
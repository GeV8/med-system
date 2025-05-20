package com.example.medsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "medications")
@Data
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    private int quantity;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(length = 50)
    private String unit;
}

package com.example.medsystem.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medication_history")
public class MedicationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    private int quantityChange;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    private String changedBy;

    public MedicationHistory(Medication medication, int quantityChange, String changedBy) {
        this.medication = medication;
        this.quantityChange = quantityChange;
        this.changedAt = LocalDateTime.now();
        this.changedBy = changedBy;
    }
}

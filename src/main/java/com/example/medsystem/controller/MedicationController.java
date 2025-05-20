package com.example.medsystem.controller;


import com.example.medsystem.model.DTO.MedicationRequestDto;
import com.example.medsystem.model.DTO.MedicationHistoryDto;
import com.example.medsystem.model.DTO.MedicationResponseDto;
import com.example.medsystem.service.MedicationService;
import com.example.medsystem.service.impl.MedicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationServiceImpl medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<MedicationResponseDto> createMedication(@RequestBody MedicationRequestDto dto) {
        MedicationResponseDto saved = medicationService.saveMedication(dto);
        return ResponseEntity
                .created(URI.create("/api/medications/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponseDto>> getAllMedications() {
        List<MedicationResponseDto> list = medicationService.getAllMedications();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationResponseDto> getMedicationById(@PathVariable int id) {
        MedicationResponseDto medication = medicationService.getMedicationById(id);
        return ResponseEntity.ok(medication);
    }

    // Оновлення кількості медикаменту
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedicationQuantity(@PathVariable int id, @RequestParam int newQuantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String changedBy = authentication.getName();
        medicationService.updateMedicationQuantity(id, newQuantity, changedBy);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<MedicationHistoryDto>> getMedicationHistory(@PathVariable int id) {
        return ResponseEntity.ok(medicationService.getMedicationHistory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicationResponseDto> deleteMedicationById(@PathVariable int id) {
         medicationService.deleteMedication(id);
        return ResponseEntity.ok().build();
    }
}


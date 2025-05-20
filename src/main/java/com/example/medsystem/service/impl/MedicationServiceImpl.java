package com.example.medsystem.service.impl;

import com.example.medsystem.model.DTO.MedicationRequestDto;
import com.example.medsystem.model.DTO.MedicationHistoryDto;
import com.example.medsystem.model.DTO.MedicationResponseDto;
import com.example.medsystem.model.Medication;
import com.example.medsystem.model.MedicationHistory;
import com.example.medsystem.repository.MedicationHistoryRepository;
import com.example.medsystem.repository.MedicationRepository;

import com.example.medsystem.service.MedicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationHistoryRepository medicationHistoryRepository;
    private final MedicationRepository medicationRepository;


    public MedicationServiceImpl(MedicationRepository medicationRepository, MedicationHistoryRepository medicationHistoryRepository) {
        this.medicationHistoryRepository = medicationHistoryRepository;
        this.medicationRepository = medicationRepository;

    }

    public MedicationResponseDto saveMedication(MedicationRequestDto dto) {
        Medication medication = new Medication();
        medication.setName(dto.getName());
        medication.setQuantity(dto.getQuantity());
        medication.setExpiryDate(dto.getExpiryDate());
        medication.setUnit(dto.getUnit());

        return toDto(medicationRepository.save(medication));
    }

    public List<MedicationResponseDto> getAllMedications() {
        return medicationRepository.findAll().stream()
                .map(MedicationServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    public MedicationResponseDto getMedicationById(int id) {
        return toDto(medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with id: " + id)));
    }

    public void updateMedicationQuantity(int id, int newQuantity, String changedBy) {
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Medication not found"));
        int quantityChange = newQuantity - medication.getQuantity();
        medication.setQuantity(newQuantity);
        medicationRepository.save(medication);

        MedicationHistory history = new MedicationHistory(medication, quantityChange, changedBy);
        medicationHistoryRepository.save(history);
    }

    public List<MedicationHistoryDto> getMedicationHistory(int medicationId) {
        List<MedicationHistory> historyList = medicationHistoryRepository.findByMedicationId(medicationId);

        return historyList.stream().map(history -> {
            MedicationHistoryDto dto = new MedicationHistoryDto();
            dto.setId(history.getId());
            dto.setQuantityChange(history.getQuantityChange());
            dto.setChangedAt(history.getChangedAt());
            dto.setChangedByUsername(history.getChangedBy());
            return dto;
        }).collect(Collectors.toList());
    }

    public void deleteMedication(int id) {
        if (!medicationRepository.existsById(id)) {
            throw new RuntimeException("Medication not found with id " + id);
        }
        medicationRepository.deleteById(id);
    }

    public static MedicationResponseDto toDto(Medication medication) {
        MedicationResponseDto dto = new MedicationResponseDto();
        dto.setId(medication.getId());
        dto.setName(medication.getName());
        dto.setQuantity(medication.getQuantity());
        dto.setExpiryDate(medication.getExpiryDate());
        dto.setUnit(medication.getUnit());
        return dto;
    }
}

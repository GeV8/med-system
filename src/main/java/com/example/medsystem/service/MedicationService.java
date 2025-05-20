package com.example.medsystem.service;

import com.example.medsystem.model.DTO.MedicationHistoryDto;
import com.example.medsystem.model.DTO.MedicationRequestDto;
import com.example.medsystem.model.DTO.MedicationResponseDto;
import com.example.medsystem.model.Medication;
import com.example.medsystem.model.MedicationHistory;

import java.util.List;
import java.util.stream.Collectors;

public interface MedicationService {
     List<MedicationResponseDto> getAllMedications();

     MedicationResponseDto saveMedication(MedicationRequestDto dto);

     MedicationResponseDto getMedicationById(int id);

     void updateMedicationQuantity(int id, int newQuantity, String changedBy);

     List<MedicationHistoryDto> getMedicationHistory(int medicationId);

     void deleteMedication(int id);
}

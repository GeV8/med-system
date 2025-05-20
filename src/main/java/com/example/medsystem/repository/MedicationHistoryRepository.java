package com.example.medsystem.repository;



import com.example.medsystem.model.MedicationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationHistoryRepository extends JpaRepository<MedicationHistory, Integer> {

    List<MedicationHistory> findByMedicationId(int medicationId);

}

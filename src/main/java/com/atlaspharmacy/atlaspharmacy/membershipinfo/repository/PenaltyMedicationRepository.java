package com.atlaspharmacy.atlaspharmacy.membershipinfo.repository;


import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.PenaltyMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PenaltyMedicationRepository extends JpaRepository<PenaltyMedication, Long> {
    @Query(value = "SELECT p FROM PenaltyMedication p WHERE p.patient.id = ?1")
    List<PenaltyMedication> getAllMedicationPenaltyByPatient(Long id);
}

package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescribedDrug, Long> {

    @Query(value = "SELECT p FROM PrescribedDrug p WHERE p.eprescription.patient.id = ?1")
    List<PrescribedDrug> getPrescribedDrugBy(Long patientId);
}

package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long> {
    List<EPrescription> findByPatientId(Long patientId);

}

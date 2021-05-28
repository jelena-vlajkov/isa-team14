package com.atlaspharmacy.atlaspharmacy.medicalrecord.repository;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    @Query(value = "SELECT m FROM MedicalRecord m WHERE m.patient.id = ?1")
    MedicalRecord getByPatientId(Long patientId);


    @Query(value = "SELECT m FROM MedicalRecord m WHERE m.patient.name = ?1")
    List<MedicalRecord> getByPatientName(String name);
}

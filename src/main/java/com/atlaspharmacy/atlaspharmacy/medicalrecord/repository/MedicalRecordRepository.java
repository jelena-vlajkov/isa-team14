package com.atlaspharmacy.atlaspharmacy.medicalrecord.repository;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}

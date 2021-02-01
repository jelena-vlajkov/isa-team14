package com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord getByPatientId(Long patientId) {
        return medicalRecordRepository.findAll()
                .stream()
                .filter(medicalRecord -> medicalRecord.isPatient(patientId))
                .findFirst()
                .orElse(null);
    }
}

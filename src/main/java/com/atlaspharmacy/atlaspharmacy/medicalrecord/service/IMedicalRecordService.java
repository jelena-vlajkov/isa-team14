package com.atlaspharmacy.atlaspharmacy.medicalrecord.service;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;

public interface IMedicalRecordService {
    MedicalRecord getByPatientId(Long patientId);
}

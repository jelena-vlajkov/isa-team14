package com.atlaspharmacy.atlaspharmacy.medicalrecord.mapper;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;

public class MedicalRecordMapper {

    private MedicalRecordMapper() { }

    public static MedicalRecordDTO mapToDto(MedicalRecord medicalRecord) {
        return new MedicalRecordDTO(medicalRecord.getPatient().getId(), medicalRecord.getPatient().getName() + " " + medicalRecord.getPatient().getSurname(),
                medicalRecord.getAllergies(), medicalRecord.getIngredients());
    }
}

package com.atlaspharmacy.atlaspharmacy.medicalrecord.service;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;

import java.util.List;

public interface IMedicalRecordService {
    MedicalRecord getByPatientId(Long patientId);
    boolean addPatientIngredients(MedicalRecordDTO dto);
    List<Ingredient> getPatientIngredient(Long id);
}

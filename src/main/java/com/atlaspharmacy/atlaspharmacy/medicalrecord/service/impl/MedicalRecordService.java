package com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public MedicalRecord getByPatientName(String name){
        return medicalRecordRepository.findAll()
                .stream()
                .filter(m -> m.getPatient().getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addPatientIngredients(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = getByPatientName(medicalRecordDTO.getPatientName());
        medicalRecord.setIngredients(medicalRecordDTO.getIngredients());
        medicalRecordRepository.save(medicalRecord);
        return  true;
    }


}

package com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        MedicalRecord medicalRecord = getByPatientId(medicalRecordDTO.getPatientId());

        List<Ingredient> oldIngredienst = medicalRecord.getIngredients();
        List<Ingredient> newIngredients = medicalRecordDTO.getIngredients();

        List<Ingredient> setIngredients = new ArrayList<>(Stream.of(oldIngredienst, newIngredients).flatMap(List::stream)
                .collect(Collectors.toMap(Ingredient::getName, d -> d, (Ingredient x, Ingredient y) -> x == null ? y : x)).values());




        /*
        Set<Ingredient> ingredientSet = new HashSet<>(medicalRecord.getIngredients());//svi postojeci
        List<Ingredient> newIngredients = medicalRecordDTO.getIngredients();//novi
        ingredientSet.addAll(newIngredients);//novi + postojeci
        medicalRecordDTO.getIngredients().clear();
        medicalRecordDTO.getIngredients().addAll(ingredientSet);
        */


        medicalRecord.setIngredients(setIngredients);
        medicalRecordRepository.save(medicalRecord);
        return  true;
    }

    @Override
    public List<Ingredient> getPatientIngredient(Long id) {
        MedicalRecord medicalRecord = getByPatientId(id);
        return  medicalRecord.getIngredients();
    }


}

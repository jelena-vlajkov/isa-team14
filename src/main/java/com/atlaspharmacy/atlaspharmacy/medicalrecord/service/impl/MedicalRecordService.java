package com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicationToRecommendDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicationRepository medicationRepository;
    private final IPharmacyStorageService pharmacyStorageService;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, MedicationRepository medicationRepository, IPharmacyStorageService pharmacyStorageService) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicationRepository = medicationRepository;
        this.pharmacyStorageService = pharmacyStorageService;
    }

    @Override
    public MedicalRecord getByPatientId(Long patientId) {
        return medicalRecordRepository.getByPatientId(patientId);
    }

    public MedicalRecord getByPatientName(String name){
        List<MedicalRecord> medicalRecords = medicalRecordRepository.getByPatientName(name);
        if (medicalRecords.size() != 0) {
            return medicalRecords.get(0);
        }
        return null;
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
    public List<MedicationToRecommendDTO> recommendMedicationForPatient(Long patientId, Long pharmacyId) {
        MedicalRecord medicalRecord = getByPatientId(patientId);
        List<Allergy> allergies = medicalRecord.getAllergies();
        List<Ingredient> ingredients = medicalRecord.getIngredients();
        List<MedicationToRecommendDTO> medicationsToRecommend = new ArrayList<>();

        List<Medication> medications = medicationRepository.findAll();
        boolean addMedication = true;
        for (Medication m : medications) {
            for (Ingredient i : m.getIngredients()) {
                if (ingredients.stream().anyMatch(ig -> ig.getName().equals(i.getName()))) {
                    addMedication = false;
                    break;
                }

                for (Allergy a : i.getAllergies()) {
                    for (Ingredient ig : ingredients) {
                        if (ig.getAllergies().stream().anyMatch(al -> al.getName().equals(a.getName()))) {
                            addMedication = false;
                            break;
                        }
                    }
                }
            }

            if (addMedication && !pharmacyStorageService.isMedicationInPharmacy(m.getCode(), pharmacyId)) {
                MedicationToRecommendDTO dto = new MedicationToRecommendDTO();
                dto.setId(m.getId());
                dto.setName(m.getName());
                dto.setAvailable(false);
                medicationsToRecommend.add(dto);
                addMedication = true;
                break;
            }

            if (addMedication) {
                MedicationToRecommendDTO dto = new MedicationToRecommendDTO();
                dto.setId(m.getId());
                dto.setName(m.getName());
                dto.setAvailable(true);
                medicationsToRecommend.add(dto);
                addMedication = true;
            }
            addMedication = true;
        }

        return medicationsToRecommend;
    }

    public List<Ingredient> getPatientIngredient(Long id) {
        MedicalRecord medicalRecord = getByPatientId(id);
        return  medicalRecord.getIngredients();
    }

    @Override
    public List<MedicationToRecommendDTO> recommendSimilarMedication(Long medicationId, Long pharmacyId) throws Exception {
        if (!medicationRepository.findById(medicationId).isPresent()) {
            throw new Exception("Invalid medication");
        }
        List<MedicationToRecommendDTO> toRecommend = new ArrayList<>();

        Medication medication = medicationRepository.findById(medicationId).get();
        for (Medication m : medication.getSubstituteMedication()) {
            if (pharmacyStorageService.isMedicationInPharmacy(m.getCode(), pharmacyId)) {
                MedicationToRecommendDTO dto = new MedicationToRecommendDTO();
                dto.setName(m.getName());
                dto.setId(m.getId());
                dto.setAvailable(true);
                toRecommend.add(dto);
                break;
            }
            MedicationToRecommendDTO dto = new MedicationToRecommendDTO();
            dto.setName(m.getName());
            dto.setId(m.getId());
            dto.setAvailable(false);
            toRecommend.add(dto);

        }
        return toRecommend;
    }


}

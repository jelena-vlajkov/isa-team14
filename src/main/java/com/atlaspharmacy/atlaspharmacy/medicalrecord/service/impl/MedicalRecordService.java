package com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, MedicationRepository medicationRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicationRepository = medicationRepository;
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

    @Override
    public List<MedicationDTO> recommendMedicationForPatient(Long patientId) {
        MedicalRecord medicalRecord = getByPatientId(patientId);
        List<Allergy> allergies = medicalRecord.getAllergies();

        List<Medication> medications = medicationRepository.findAll();
        /*
        for (Medication m : medications) {
            if (allergies.stream().filter())
        }*/

        return MedicationMapper.convertToDTOS(medications);
    }


}

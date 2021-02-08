package com.atlaspharmacy.atlaspharmacy.medication.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationMapper {

        public MedicationMapper() {
        }

        public static MedicationDTO convertToMedicationDTO(Medication m){
            MedicationDTO medicationDTO = new MedicationDTO(
                    m.getId(),
                    m.getName(),
                    m.getDrugForm(),
                    m.getDrugType(),
                    m.getProducer(),
                    m.getTypeOfPrescribing(),
                    m.getAdditionalNotes(),
                    m.getContraindications(),
                    m.getDailyDose(),
                    m.getDrugKind(),
                    m.getCode(),
                    m.getGrade());
            medicationDTO.setSubstituteMedication(new ArrayList<>());

            for(Medication med: m.getSubstituteMedication()){
                medicationDTO.getSubstituteMedication().add(med.getId());
            }
            medicationDTO.setIngredients(new ArrayList<>());

            for(Ingredient i : m.getIngredients()){
                medicationDTO.getIngredients().add(i.getId());
            }
            return medicationDTO;
        }

        public static void convertToMedication(Medication m, MedicationDTO mdto){
            m.setId(mdto.getId());
            m.setName(mdto.getName());
            m.setDailyDose(mdto.getDailyDose());
            m.setAdditionalNotes(mdto.getAdditionalNotes());
            m.setContraindications(mdto.getContraindications());
            m.setDrugKind(mdto.getDrugKind());
            m.setDrugForm(mdto.getDrugForm());
            m.setDrugType(mdto.getDrugType());
            m.setProducer(mdto.getProducer());
            m.setTypeOfPrescribing(mdto.getTypeOfPrescribing());
            m.setCode(mdto.getCode());
            m.setGrade(mdto.getGrade());
        }

        public static List<MedicationDTO> convertToDTOS(List<Medication> medications){
            List<MedicationDTO> dtos = new ArrayList<>();
            Long amount = null;
            for(Medication m : medications){
                MedicationDTO dto = convertToMedicationDTO(m);
                dtos.add(dto);
            }

            return dtos;
        }
}


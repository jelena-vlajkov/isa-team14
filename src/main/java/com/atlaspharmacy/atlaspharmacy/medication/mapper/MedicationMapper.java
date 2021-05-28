package com.atlaspharmacy.atlaspharmacy.medication.mapper;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicationToRecommendDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationMapper {

        public MedicationMapper() {
        }
        public static MedicationDTO convertToSubMedDTO(Medication m){
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
                    m.getGrade(),
                    m.getDosage());
            medicationDTO.setSubstituteMedication(new ArrayList<>());
            medicationDTO.setIngredients(IngredientMapper.convertToDTOS(m.getIngredients()));
            return medicationDTO;
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
                    m.getGrade(),
                    m.getDosage());
            medicationDTO.setSubstituteMedication(new ArrayList<>());

            if(m.getSubstituteMedication().size()!=0){
                for(Medication med: m.getSubstituteMedication()){
                    medicationDTO.getSubstituteMedication().add(convertToSubMedDTO(med));
                }
            }


            medicationDTO.setIngredients(IngredientMapper.convertToDTOS(m.getIngredients()));

            return medicationDTO;
        }

        public static Medication convertToMedication(MedicationDTO mdto){
            Medication m = new Medication();
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
            m.setDosage(mdto.getDosage());
            return m;
        }

        public static List<MedicationDTO> convertToDTOS(List<Medication> medications){
            List<MedicationDTO> dtos = new ArrayList<>();
            for(Medication m : medications){
                MedicationDTO dto = convertToMedicationDTO(m);
                dtos.add(dto);
            }

            return dtos;
        }

    public static List<MedicationToRecommendDTO> convertRecommendations(List<Medication> medications) {
        List<MedicationToRecommendDTO> medicationsConverted = new ArrayList<>();
        for (Medication m : medications) {
            medicationsConverted.add(convertOneRecommendation(m));
        }
        return medicationsConverted;
    }

    public static MedicationToRecommendDTO convertOneRecommendation(Medication medication) {
            MedicationToRecommendDTO dto = new MedicationToRecommendDTO();
            dto.setName(medication.getName());
            dto.setId(medication.getId());
            return dto;

    }
}


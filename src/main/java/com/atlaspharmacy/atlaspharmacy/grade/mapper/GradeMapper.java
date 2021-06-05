package com.atlaspharmacy.atlaspharmacy.grade.mapper;

import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.*;
import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;

public class GradeMapper {

    public static GradeDTO gradeToDto(Grade grade) {
        GradeDTO dto= new GradeDTO();

        if (grade.getType().equals(GradeType.Values.MedicationGrade)) {
            MedicationGrade medicationGrade = (MedicationGrade) grade;
            dto.setMedicationId(medicationGrade.getMedication().getId());
            dto.setGradeType(GradeType.Values.MedicationGrade);
            dto.setMedicationName(medicationGrade.getMedication().getName());
        }

        if (grade.getType().equals(GradeType.Values.PharmacyGrade)) {
            PharmacyGrade pharmacyGrade = (PharmacyGrade) grade;
            dto.setPharmacyId(pharmacyGrade.getPharmacy().getId());
            dto.setGradeType(GradeType.Values.PharmacyGrade);
            dto.setPharmacyName(pharmacyGrade.getPharmacy().getName());
        }

        if (grade.getType().equals(GradeType.Values.PharmacistGrade)) {
            PharmacistGrade pharmacistGrade = (PharmacistGrade) grade;
            dto.setPharmacistId(pharmacistGrade.getPharmacist().getId());
            dto.setGradeType(GradeType.Values.PharmacistGrade);
            dto.setPharmacistName(pharmacistGrade.getPharmacist().getName() + " " + pharmacistGrade.getPharmacist().getSurname());
        }

        if (grade.getType().equals(GradeType.Values.DermatologistGrade)) {
            DermatologistGrade dermatologistGrade = (DermatologistGrade) grade;
            dto.setDermatologistId(dermatologistGrade.getDermatologist().getId());
            dto.setGradeType(GradeType.Values.DermatologistGrade);
            dto.setDermatologistName(dermatologistGrade.getDermatologist().getName() + " " + dermatologistGrade.getDermatologist().getSurname());
        }

        dto.setId(grade.getId());
        dto.setGrade(grade.getGrade());
        dto.setPatientId(grade.getPatient().getId());

        return dto;
    }

    public static Grade dtoToGrade(GradeDTO dto) {
        Grade grade = null;

        if(dto.getGradeType().equals(GradeType.Values.MedicationGrade)) {
            grade = new MedicationGrade();
        }

        if(dto.getGradeType().equals(GradeType.Values.PharmacyGrade)) {
            grade = new PharmacyGrade();
        }

        if(dto.getGradeType().equals(GradeType.Values.PharmacistGrade)) {
            grade = new PharmacistGrade();
        }

        if(dto.getGradeType().equals(GradeType.Values.DermatologistGrade)) {
            grade = new DermatologistGrade();
        }

        if (grade != null) {
            grade.setGrade(dto.getGrade());
        }

        return grade;
    }
}

package com.atlaspharmacy.atlaspharmacy.grade.mapper;

import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.MedicationGrade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.PharmacyGrade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;

public class GradeMapper {

    public static GradeDTO gradeToDto(Grade grade) {
        GradeDTO dto= new GradeDTO();

        if (grade.getType().equals(GradeType.Values.MedicationGrade)) {
            MedicationGrade medicationGrade = (MedicationGrade) grade;
            dto.setMedicationId(medicationGrade.getMedication().getId());
            dto.setGradeType(GradeType.Values.MedicationGrade);
        }

        if (grade.getType().equals(GradeType.Values.PharmacyGrade)) {
            PharmacyGrade pharmacyGrade = (PharmacyGrade) grade;
            dto.setPharmacyId(pharmacyGrade.getPharmacy().getId());
            dto.setGradeType(GradeType.Values.PharmacyGrade);
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

        if (grade != null) {
            grade.setGrade(dto.getGrade());
        }

        return grade;
    }
}

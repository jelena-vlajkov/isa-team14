package com.atlaspharmacy.atlaspharmacy.grade.mapper;

import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.MedicationGrade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;

public class GradeMapper {

    public static GradeDTO gradeToDto(Grade grade) {
        GradeDTO dto= new GradeDTO();

        if (grade.getType().equals(GradeType.Values.MedicationGrade)) {
            MedicationGrade medicationGrade = (MedicationGrade) grade;
            dto.setMedicationId(medicationGrade.getMedication().getId());
        }

        dto.setId(grade.getId());
        dto.setGrade(grade.getGrade());
        dto.setPatientId(grade.getPatient().getId());
        dto.setGradeType(GradeType.Values.MedicationGrade);

        return dto;
    }

    public static Grade dtoToGrade(GradeDTO dto) {
        Grade grade = null;

        if(dto.getGradeType().equals(GradeType.Values.MedicationGrade)) {
            grade = new MedicationGrade();
        }

        if (grade != null) {
            grade.setGrade(dto.getGrade());
        }

        return grade;
    }
}

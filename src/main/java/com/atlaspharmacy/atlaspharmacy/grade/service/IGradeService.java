package com.atlaspharmacy.atlaspharmacy.grade.service;

import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;

import java.util.List;

public interface IGradeService {
    List<Grade> findAll();
    Grade findById(Long gradeId);
    List<Grade> findByPatient(Long id);
    Grade newMedicationGrade(GradeDTO dto);
    Grade updateGivenGrade(Long gradeId, int newGrade);
    Grade newPharmacyGrade(GradeDTO dto);
    Grade newPharmacistGrade(GradeDTO dto);
}

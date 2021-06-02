package com.atlaspharmacy.atlaspharmacy.grade.repository;

import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(value = "SELECT g FROM Grade g WHERE g.patient.id = ?1")
    List<Grade> findByPatient(Long patientId);
}

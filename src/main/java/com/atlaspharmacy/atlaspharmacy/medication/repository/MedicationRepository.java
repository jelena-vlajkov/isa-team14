package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query(value = "SELECT m FROM Medication m WHERE m.name = ?1")
    List<Medication> findByName(String name);

    @Query(value = "SELECT m FROM Medication m WHERE m.drugType = ?1")
    List<Medication> findByType(Long type);

    @Query(value = "SELECT m FROM Medication m WHERE m.drugForm = ?1")
    List<Medication> findByForm(Long form);

    @Query(value = "SELECT m FROM Medication m WHERE m.drugKind = ?1")
    List<Medication> findByKind(Long kind);

    @Query(value = "SELECT m FROM Medication m WHERE m.typeOfPrescribing = ?1")
    List<Medication> findByPrescribing();
}

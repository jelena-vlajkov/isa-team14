package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicationRepository extends JpaRepository<Medication, Long> {


}

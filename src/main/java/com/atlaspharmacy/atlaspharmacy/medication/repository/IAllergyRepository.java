package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAllergyRepository extends JpaRepository<Allergy, Long> {

}

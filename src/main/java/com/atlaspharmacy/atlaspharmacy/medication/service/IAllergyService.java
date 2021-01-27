package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.repository.IAllergyRepository;

import java.util.List;

public interface IAllergyService {
    AllergyDTO findById(Long id);
    List<AllergyDTO> findAll();
    void saveAllergy(Allergy allergy, AllergyDTO allergyDTO) throws Exception;
}

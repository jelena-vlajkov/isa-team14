package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharamcy;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface IPharmacyService {
    Pharamcy getById(Long id);
}

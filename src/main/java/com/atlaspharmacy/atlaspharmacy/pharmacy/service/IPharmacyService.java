package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface IPharmacyService {
    Pharmacy getById(Long id);
}

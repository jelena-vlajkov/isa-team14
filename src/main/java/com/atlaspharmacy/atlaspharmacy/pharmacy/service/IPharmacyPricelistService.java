package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PricelistType;

public interface IPharmacyPricelistService {
    double counselingCost(Long pharmacyId) throws Exception;
    double examinationCost(Long pharmacyId) throws Exception;
}

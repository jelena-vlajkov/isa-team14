package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyPricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PricelistType;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyPricelistRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyPricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PharmacyPricelistService implements IPharmacyPricelistService {
    private final PharmacyPricelistRepository pharmacyPricelistRepository;

    @Autowired
    public PharmacyPricelistService(PharmacyPricelistRepository pharmacyPricelistRepository) {
        this.pharmacyPricelistRepository = pharmacyPricelistRepository;
    }

    @Override
    public double counselingCost(Long pharmacyId) throws Exception {
        PharmacyPricelist pharmacyPricelist = pharmacyPricelistRepository.findPricelistByPharmacyAndType(pharmacyId, PricelistType.Values.Counseling);
        if (pharmacyPricelist != null) {
            return pharmacyPricelist.getCost();
        }
        throw new Exception("No pricelist for todays date");
    }

    @Override
    public double examinationCost(Long pharmacyId) throws Exception {
        PharmacyPricelist pharmacyPricelist = pharmacyPricelistRepository.findPricelistByPharmacyAndType(pharmacyId, PricelistType.Values.Examination);
        if (pharmacyPricelist != null) {
            return pharmacyPricelist.getCost();
        }
        throw new Exception("No pricelist for todays date");
    }
}

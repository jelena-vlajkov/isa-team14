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
        List<PharmacyPricelist> allPricelists = pharmacyPricelistRepository.findAll();

        for (PharmacyPricelist pharmacyPricelist : allPricelists) {
            if (pharmacyPricelist.isInRange(new Date()) && pharmacyPricelist.getPharmacy().getId().equals(pharmacyId)
                && pharmacyPricelist.getType().equals(PricelistType.Values.Counseling))  {
                return pharmacyPricelist.getCost();
            }
        }
        throw new Exception("No pricelist for todays date");
    }

    @Override
    public double examinationCost(Long pharmacyId) throws Exception {
        List<PharmacyPricelist> allPricelists = pharmacyPricelistRepository.findAll();

        for (PharmacyPricelist pharmacyPricelist : allPricelists) {
            if (pharmacyPricelist.isInRange(new Date()) && pharmacyPricelist.getPharmacy().getId().equals(pharmacyId)
                    && pharmacyPricelist.getType().equals(PricelistType.Values.Examination))  {
                return pharmacyPricelist.getCost();
            }
        }
        throw new Exception("No pricelist for todays date");
    }
}

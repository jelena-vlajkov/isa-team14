package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.IPharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository _pharmacyRepository;
    final private static String EXCEPTION = "Exception in Pharmacy Service Implementation method:";
    final private static String DOES_NOT_EXIST = "Pharmacy with Id does not exist";

    @Autowired
    public PharmacyService(IPharmacyRepository pharmacyRepository) {
        _pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Pharmacy getById(Long id) {
        Pharmacy pharmacy = _pharmacyRepository.getById(id).orElse(null);
        if(pharmacy == null){
            throw  new NoSuchElementException(EXCEPTION + " findById" + DOES_NOT_EXIST);
        }

        return  pharmacy;
    }
}

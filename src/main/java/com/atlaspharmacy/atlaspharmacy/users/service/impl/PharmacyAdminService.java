package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.repository.IPharmacyAdminRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {

    private final IPharmacyAdminRepository _pharmacyAdminRepository;

    @Autowired
    public PharmacyAdminService(IPharmacyAdminRepository _iPharmacyAdminRepository) {
        this._pharmacyAdminRepository = _iPharmacyAdminRepository;
    }

    @Override
    public Pharmacy getPharmacyByPharmacyAdmin(Long id) {
        PharmacyAdmin pharmacyAdmin= _pharmacyAdminRepository.findById(id).orElse(null);

        return pharmacyAdmin.getPharmacy();
    }
    @Override
    public PharmacyAdmin getById(Long id) {
        return _pharmacyAdminRepository.findById(id).orElse(null);
    }
}

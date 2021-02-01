package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.repository.IPharmacyAdminRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {

    private final IPharmacyAdminRepository _pharmacyAdminRepository;

    @Autowired
    public PharmacyAdminService(IPharmacyAdminRepository _iPharmacyAdminRepository) {
        this._pharmacyAdminRepository = _iPharmacyAdminRepository;
    }

    @Override
    public Pharmacy getPharmacyByPharmacyAdmin(Long id) {
        PharmacyAdmin pharmacyAdmin= _pharmacyAdminRepository.findAll()
                                       .stream()
                                       .filter(admin -> admin.getId()==id).findFirst()
                                       .orElse(null);
        return pharmacyAdmin.getPharmacy();
    }
}

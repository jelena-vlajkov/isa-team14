package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;

import java.util.Optional;

public interface IPharmacyAdminService {

    Pharmacy getPharmacyByPharmacyAdmin(Long id);
    PharmacyAdmin getById(Long id);
}

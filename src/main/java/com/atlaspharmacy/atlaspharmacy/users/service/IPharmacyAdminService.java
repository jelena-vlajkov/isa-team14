package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;

public interface IPharmacyAdminService {

    Pharmacy getPharmacyByPharmacyAdmin(Long id);
}

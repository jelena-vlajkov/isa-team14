package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacyAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;

import java.text.ParseException;
import java.util.Optional;

public interface IPharmacyAdminService {

    PharmacyDTO getPharmacyByPharmacyAdmin(Long id);
    PharmacyAdmin getById(Long id);
    PharmacyAdmin registerPharmacyAdmin(PharmacyAdminDTO pharmacyAdminDTO) throws InvalidEmail, ParseException;
}

package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import java.util.List;

public interface IPharmacistService {
    public List<Pharmacist> findByPharmacy(Long id);
}

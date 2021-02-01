package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;

import java.util.List;

public interface IDermatologistService {
    public List<Dermatologist> findAllByPharmacy(Long id);
}

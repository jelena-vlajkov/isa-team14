package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;

import java.util.List;

public interface IDermatologistService {
    public List<Dermatologist> findAllByPharmacy(Long id);
    public Dermatologist registerDermatologist(DermatologistDTO dto) throws InvalidEmail;
    List<Dermatologist> getAllDermatologistsToComplain(Long id);
}

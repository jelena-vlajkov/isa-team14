package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.IPharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistService implements IPharmacistService {

    private IPharmacistRepository _pharmacistRepository;

    @Autowired
    public PharmacistService(IPharmacistRepository _pharmacistRepository) {
        this._pharmacistRepository = _pharmacistRepository;
    }

    @Override
    public List<Pharmacist> findByPharmacy(Long id) {
        return _pharmacistRepository.findAll().stream()
                .filter(pharmacist -> pharmacist.getPharmacy().getId()==id)
                .collect(Collectors.toList());

    }
}

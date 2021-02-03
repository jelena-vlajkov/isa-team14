package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository _dermatologistRepository;

    @Autowired
    public DermatologistService(DermatologistRepository _dermatologistRepository) {
        this._dermatologistRepository = _dermatologistRepository;
    }

    @Override
    public List<Dermatologist> findAllByPharmacy(Long id) {
        List<Dermatologist> dermatologists=_dermatologistRepository.findAll();

        List<Dermatologist> dermatologistsByPharmacy= new ArrayList<>();
        for (Dermatologist dermatologist: dermatologists) {
            if (dermatologist.getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getId()==id))
            {
                dermatologistsByPharmacy.add(dermatologist);
            }
        }
        return dermatologistsByPharmacy;
    }
}

package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.PenaltyRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IPenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PenaltyService implements IPenaltyService {
    private final PenaltyRepository penaltyRepository;

    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public void savePenalty(Penalty penalty) {
        penaltyRepository.save(penalty);
    }

    @Override
    public List<Penalty> getByPatient(Long id) {
        return penaltyRepository.findAll()
                .stream()
                .filter(penalty -> penalty.isPatient(id))
                .collect(Collectors.toList());
    }
}

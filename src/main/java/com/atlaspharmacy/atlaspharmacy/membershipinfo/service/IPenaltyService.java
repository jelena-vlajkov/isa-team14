package com.atlaspharmacy.atlaspharmacy.membershipinfo.service;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;

import java.util.List;

public interface IPenaltyService {
    void savePenalty(Penalty penalty);
    List<Penalty> getByPatient(Long id);
}

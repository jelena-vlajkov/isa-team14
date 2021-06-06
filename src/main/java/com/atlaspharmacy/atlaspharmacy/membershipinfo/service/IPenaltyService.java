package com.atlaspharmacy.atlaspharmacy.membershipinfo.service;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.PenaltyMedication;

import java.util.List;

public interface IPenaltyService {
    void savePenalty(Penalty penalty) throws Exception;
    List<Penalty> getByPatient(Long id);
    void saveMedicationPenalty(PenaltyMedication penaltyMedication) throws Exception;
    List<PenaltyMedication> getMedicationPenaltyByPatient(Long patientId);
    int getNumberOfPatientPenaltiesForThisMonth(Long patientId) throws Exception;
}

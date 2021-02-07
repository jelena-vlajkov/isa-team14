package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;

import java.util.List;

public interface IEPrescriptionService {
    List<EPrescription> getPatientsEPrescription(Long id);
}

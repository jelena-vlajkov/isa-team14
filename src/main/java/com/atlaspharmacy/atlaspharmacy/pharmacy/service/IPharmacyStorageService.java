package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;

import java.util.List;

public interface IPharmacyStorageService {
    List<PharmacyStorage> getMedicationsByPharmacy(Long pharmacyId);
    Medication getMedicationInPharmacy(Long medicationId, Long pharmacyId);
}

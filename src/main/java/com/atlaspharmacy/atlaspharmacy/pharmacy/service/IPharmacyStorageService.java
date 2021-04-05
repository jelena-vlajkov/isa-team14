package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;

import java.util.List;

public interface IPharmacyStorageService {
    List<PharmacyStorage> getMedicationsByPharmacy(Long pharmacyId);
    PharmacyStorage getMedicationInPharmacy(Long medicationId, Long pharmacyId);
    boolean isMedicationInPharmacy(Long code, Long id);
    void addNewMedicationsToStorage(Order order);
}

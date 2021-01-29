package com.atlaspharmacy.atlaspharmacy.notifications.service;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;

public interface INotificationService {
    void medicationQuantityLow(PharmacyStorage pharmacyStorage);
}

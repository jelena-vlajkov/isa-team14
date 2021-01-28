package com.atlaspharmacy.atlaspharmacy.notifications.service;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;

public interface INotificationService {
    void medicationQuantityLow(Medication medication, Pharmacy pharmacy);
}

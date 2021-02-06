package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierStorageMedication;

import java.util.List;

public interface ISupplierStorageService {
    List<SupplierStorageMedication> getSuppliersMedications(Long id);
}

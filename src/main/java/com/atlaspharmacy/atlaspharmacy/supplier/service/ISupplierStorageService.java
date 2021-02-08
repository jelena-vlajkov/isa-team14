package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.NewMedicationToStorageDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierStorageMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierStorageMedication;

import java.util.List;

public interface ISupplierStorageService {
    List<SupplierStorageMedication> getSuppliersMedications(Long id);
    boolean medicationPresentInStorage(OrderedMedicationDTO orderedMedicationDTO, Long supplierId);
    SupplierStorageMedicationDTO getSuppliersStorage(Long id);
    SupplierStorageMedication addNewMedicationToStorage(NewMedicationToStorageDTO dto);
    SupplierStorageMedication updateQuantity(NewMedicationToStorageDTO dto);
}

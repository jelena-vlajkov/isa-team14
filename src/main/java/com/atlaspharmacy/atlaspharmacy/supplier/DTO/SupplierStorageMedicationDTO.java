package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

import java.util.List;

public class SupplierStorageMedicationDTO {
    private List<MedicationInStorageDTO> medicationInStorage;
    private SupplierDTO supplier;

    public SupplierStorageMedicationDTO() {
    }

    public SupplierStorageMedicationDTO(List<MedicationInStorageDTO> medicationInStorage, SupplierDTO supplier) {
        this.medicationInStorage = medicationInStorage;
        this.supplier = supplier;
    }

    public List<MedicationInStorageDTO> getMedicationInStorage() {
        return medicationInStorage;
    }

    public void setMedicationInStorage(List<MedicationInStorageDTO> medicationInStorage) {
        this.medicationInStorage = medicationInStorage;
    }

    public SupplierDTO getSupplierDTO() {
        return supplier;
    }

    public void setSupplierDTO(SupplierDTO supplierDTO) {
        this.supplier = supplierDTO;
    }
}

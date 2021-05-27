package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

import java.util.List;

public class SupplierStorageMedicationDTO {
    private List<OrderedMedicationDTO> medicationInStorage;
    private SupplierDTO supplier;

    public SupplierStorageMedicationDTO() {
    }

    public SupplierStorageMedicationDTO(List<OrderedMedicationDTO> medicationInStorage, SupplierDTO supplier) {
        this.medicationInStorage = medicationInStorage;
        this.supplier = supplier;
    }

    public List<OrderedMedicationDTO> getMedicationInStorage() {
        return medicationInStorage;
    }

    public void setMedicationInStorage(List<OrderedMedicationDTO> medicationInStorage) {
        this.medicationInStorage = medicationInStorage;
    }

    public SupplierDTO getSupplierDTO() {
        return supplier;
    }

    public void setSupplierDTO(SupplierDTO supplierDTO) {
        this.supplier = supplierDTO;
    }
}

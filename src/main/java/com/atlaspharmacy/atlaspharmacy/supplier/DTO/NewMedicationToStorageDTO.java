package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

public class NewMedicationToStorageDTO {
    private MedicationDTO medication;
    private Long quantity;
    private SupplierDTO supplier;

    public NewMedicationToStorageDTO() {
    }

    public NewMedicationToStorageDTO(MedicationDTO medication, Long quantity, SupplierDTO supplier) {
        this.medication = medication;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

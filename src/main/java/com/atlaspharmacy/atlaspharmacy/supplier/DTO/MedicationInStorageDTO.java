package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;

public class MedicationInStorageDTO {
    private Long quantity;
    private MedicationDTO medication;

    public MedicationInStorageDTO() {
    }

    public MedicationInStorageDTO(Long quantity, MedicationDTO medication_id) {
        this.quantity = quantity;
        this.medication = medication_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication_id) {
        this.medication = medication_id;
    }
}

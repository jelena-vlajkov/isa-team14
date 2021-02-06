package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import javax.persistence.Embeddable;

@Embeddable
public class SupplierMedicationInStorage {
    private Long quantity;
    private Long medication;

    public SupplierMedicationInStorage() {
    }

    public SupplierMedicationInStorage(Long quantity, Long medication_id) {
        this.quantity = quantity;
        this.medication = medication_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getMedication_id() {
        return medication;
    }

    public void setMedication_id(Long medication_id) {
        this.medication = medication_id;
    }
}

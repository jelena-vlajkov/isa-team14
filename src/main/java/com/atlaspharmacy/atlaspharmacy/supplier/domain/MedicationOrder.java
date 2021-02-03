package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import javax.persistence.Embeddable;

@Embeddable
public class MedicationOrder {
    private Long medication;
    private Long quantity;

    public MedicationOrder(Long medication, Long quantity) {
        this.medication = medication;
        this.quantity = quantity;
    }

    public MedicationOrder() {
    }

    public Long getMedication() {
        return medication;
    }

    public void setMedication(Long medication) {
        this.medication = medication;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

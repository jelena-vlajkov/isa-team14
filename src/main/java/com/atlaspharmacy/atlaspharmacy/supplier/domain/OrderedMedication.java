package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import javax.persistence.Embeddable;

@Embeddable
public class OrderedMedication {
    private Long medicationId;
    private Long quantity;

    public OrderedMedication(Long medication, Long quantity) {
        this.medicationId = medication;
        this.quantity = quantity;
    }

    public OrderedMedication() {
    }

    public Long getMedication() {
        return medicationId;
    }

    public void setMedication(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}

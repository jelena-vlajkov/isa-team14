package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import javax.persistence.Embeddable;

@Embeddable
public class MedicationOrder {
    private Long medicationId;
    private Long quantity;
    private String medicationName;
    public MedicationOrder(Long medicationId, Long quantity,String medicationName) {
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.medicationName=medicationName;
    }

    public MedicationOrder() {
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

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
}

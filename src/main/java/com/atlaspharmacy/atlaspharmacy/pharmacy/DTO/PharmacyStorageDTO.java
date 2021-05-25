package com.atlaspharmacy.atlaspharmacy.pharmacy.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;

public class PharmacyStorageDTO {
    private Long medicationId;
    private String medicationName;
    private Long medicationCode;
    private double quantity;
    private Pharmacy pharmacy;


    public PharmacyStorageDTO() {
    }

    public PharmacyStorageDTO(Long medicationId, String medicationName, double quantity, Pharmacy pharmacy,Long medicationCode) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.quantity = quantity;
        this.pharmacy = pharmacy;
        this.medicationCode = medicationCode;
    }

    public Long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(Long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}

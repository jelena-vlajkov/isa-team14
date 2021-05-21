package com.atlaspharmacy.atlaspharmacy.medication.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class PrescribedDrugDTO {
    private Long id;
    private Long quantity;
    private String prescribedMedicationName;
    private Long prescriptionId;

    public PrescribedDrugDTO(Long id, Long quantity, String prescribedMedicationName, Long prescriptionId) {
        this.id = id;
        this.quantity = quantity;
        this.prescribedMedicationName = prescribedMedicationName;
        this.prescriptionId = prescriptionId;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getPrescribedMedicationName() {
        return prescribedMedicationName;
    }

    public void setPrescribedMedicationName(String prescribedMedicationName) {
        this.prescribedMedicationName = prescribedMedicationName;
    }
}

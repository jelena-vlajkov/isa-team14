package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;

public class OrderedMedicationDTO {
    private Long medicationId;
    private String medicationName;
    private Long quantity;

    public OrderedMedicationDTO() {
    }

    public OrderedMedicationDTO(Long medicationId, String medicationName, Long quantity) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.quantity = quantity;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
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

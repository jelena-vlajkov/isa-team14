package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;

public class OrderedMedicationDTO {
    private MedicationDTO medication;
    private Long quantity;

    public OrderedMedicationDTO(MedicationDTO medication, Long quantity) {
        this.medication = medication;
        this.quantity = quantity;
    }

    public OrderedMedicationDTO( ) {
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

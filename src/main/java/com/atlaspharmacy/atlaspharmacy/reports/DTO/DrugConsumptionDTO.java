package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;

public class DrugConsumptionDTO {
    private MedicationDTO medication;
    private int numberOfIssued;

    public DrugConsumptionDTO(MedicationDTO medication, int numberOfIssued) {
        this.medication = medication;
        this.numberOfIssued = numberOfIssued;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public int getNumberOfIssued() {
        return numberOfIssued;
    }

    public void setNumberOfIssued(int numberOfIssued) {
        this.numberOfIssued = numberOfIssued;
    }
}

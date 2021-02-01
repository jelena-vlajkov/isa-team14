package com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;

import java.util.List;

public class MedicalRecordDTO {
    private String patientName;
    private List<Allergy> allergies;


    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(String patientName, List<Allergy> allergies) {
        this.patientName = patientName;
        this.allergies = allergies;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }
}

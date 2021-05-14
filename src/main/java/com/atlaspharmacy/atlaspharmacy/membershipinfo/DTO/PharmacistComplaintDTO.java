package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;

public class PharmacistComplaintDTO {
    private Long id;
    private PatientDTO patient;
    private String text;
    private PharmacistDTO pharmacist;
    private boolean isAnswered;

    public PharmacistComplaintDTO() {
    }

    public PharmacistComplaintDTO(Long id, PatientDTO patient, String text, PharmacistDTO pharmacist, boolean isAnswered) {
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.pharmacist = pharmacist;
        this.isAnswered = isAnswered;
    }



    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PharmacistDTO getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(PharmacistDTO pharmacist) {
        this.pharmacist = pharmacist;
    }
}

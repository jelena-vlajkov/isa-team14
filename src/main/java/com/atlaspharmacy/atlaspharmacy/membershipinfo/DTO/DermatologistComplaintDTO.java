package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;

public class DermatologistComplaintDTO {
    private Long id;
    private PatientDTO patient;
    private String text;
    private DermatologistDTO dermatologist;
    private boolean isAnswered;

    public DermatologistComplaintDTO() {
    }

    public DermatologistComplaintDTO(Long id, PatientDTO patient, String text, DermatologistDTO dermatologist, boolean isAnswered) {
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.dermatologist = dermatologist;
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

    public DermatologistDTO getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(DermatologistDTO dermatologist) {
        this.dermatologist = dermatologist;
    }
}

package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;

public class PharmacyComplaintDTO {
    private Long id;
    private PatientDTO patient;
    private String text;
    private PharmacyDTO pharmacy;
    private boolean isAnswered;

    public PharmacyComplaintDTO() {
    }

    public PharmacyComplaintDTO(Long id, PatientDTO patient, String text, PharmacyDTO pharmacy, boolean isAnswered) {
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.pharmacy = pharmacy;
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

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }
}

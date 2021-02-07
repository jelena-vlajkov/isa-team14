package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;

public class ComplaintDTO {
    private Long id;
    private PatientDTO patient;
    private String text;
    private Long usertToComplainId;
    private String role;
    private boolean isAnswered;

    public ComplaintDTO() {
    }

    public ComplaintDTO(Long id, PatientDTO patient, String text, Long usertToComplainId, String role, boolean isAnswered) {
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.usertToComplainId = usertToComplainId;
        this.role = role;
        this.isAnswered = isAnswered;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Long getUsertToComplainId() {
        return usertToComplainId;
    }

    public void setUsertToComplainId(Long usertToComplainId) {
        this.usertToComplainId = usertToComplainId;
    }
}

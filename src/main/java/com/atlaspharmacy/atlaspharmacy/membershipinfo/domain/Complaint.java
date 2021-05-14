package com.atlaspharmacy.atlaspharmacy.membershipinfo.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Patient patient;

    private Long usertToComplainId;
    private String role;
    private String text;
    private boolean isAnswered;

    public Complaint(Long id, Patient patient, Long usertToComplainId, String text, String role, boolean isAnswered) {
        this.id = id;
        this.patient = patient;
        this.usertToComplainId = usertToComplainId;
        this.text = text;
        this.role = role;
        this.isAnswered = isAnswered;
    }

    public Complaint() {
    }
    public Complaint(Long id) {
        this.id = id;
    }
    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getUsertToComplainId() {
        return usertToComplainId;
    }

    public void setUsertToComplainId(Long usertToComplainId) {
        this.usertToComplainId = usertToComplainId;
    }
}

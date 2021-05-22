package com.atlaspharmacy.atlaspharmacy.medication.DTO;

import java.util.Date;

public class EPrescriptionDTO {
    private Long id;
    private Date date;
    private String patientName;
    private String pharmacyName;


    public EPrescriptionDTO(Long id, Date date, String patientName, String pharmacyName) {
        this.id = id;
        this.date = date;
        this.patientName = patientName;
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}

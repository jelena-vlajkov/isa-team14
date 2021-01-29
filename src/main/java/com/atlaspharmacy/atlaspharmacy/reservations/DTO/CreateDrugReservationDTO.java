package com.atlaspharmacy.atlaspharmacy.reservations.DTO;

import java.util.Date;

public class CreateDrugReservationDTO {
    private Long patientId;
    private String patientMail;
    private String pharmacyName;
    private String medicationName;
    private int uniqueIdentifier;
    private Date expirationDate;


    public CreateDrugReservationDTO(long patientId, String patientMail, String pharmacyName, String medicationName, Integer uniqueIdentifier, Date expirationDate) {
        this.patientId = patientId;
        this.patientMail = patientMail;
        this.pharmacyName = pharmacyName;
        this.medicationName = medicationName;
        this.uniqueIdentifier = (new Date() + "" + patientMail).hashCode();
        this.expirationDate = expirationDate;
    }

    public CreateDrugReservationDTO(){

    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientMail() {
        return patientMail;
    }

    public void setPatientMail(String patientMail) {
        this.patientMail = patientMail;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(int uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

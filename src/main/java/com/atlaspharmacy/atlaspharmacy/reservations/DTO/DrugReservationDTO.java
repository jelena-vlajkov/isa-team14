package com.atlaspharmacy.atlaspharmacy.reservations.DTO;

import java.util.Date;

public class DrugReservationDTO {
    private String patientName;
    private String patientEmail;
    private String medicationName;
    private String uniqueIdentifier;
    private Date expirationDate;

    public DrugReservationDTO(String patientName, String patientEmail, String medicationName, String uniqueIdentifier, Date expirationDate) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.medicationName = medicationName;
        this.uniqueIdentifier = uniqueIdentifier;
        this.expirationDate = expirationDate;
    }

    public DrugReservationDTO() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

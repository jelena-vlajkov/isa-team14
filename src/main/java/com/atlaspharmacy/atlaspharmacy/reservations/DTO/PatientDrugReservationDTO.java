package com.atlaspharmacy.atlaspharmacy.reservations.DTO;

import java.util.Date;

public class PatientDrugReservationDTO {
    private String medicationName;
    private String pharmacyName;
    private String  medicationProducer;
    private Date expirationDate;


    public PatientDrugReservationDTO(String medicationName, String pharmacyName, String medicationProducer, Date expirationDate) {
        this.medicationName = medicationName;
        this.pharmacyName = pharmacyName;
        this.medicationProducer = medicationProducer;
        this.expirationDate = expirationDate;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getMedicationProducer() {
        return medicationProducer;
    }

    public void setMedicationProducer(String medicationProducer) {
        this.medicationProducer = medicationProducer;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

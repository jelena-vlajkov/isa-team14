package com.atlaspharmacy.atlaspharmacy.reservations.DTO;

import java.util.Date;

public class PatientDrugReservationDTO {
    private Long id;
    private String medicationName;
    private String pharmacyName;
    private String  medicationProducer;
    private Date expirationDate;
    private boolean issued;
    private boolean canceled;


    public PatientDrugReservationDTO(String medicationName, String pharmacyName, String medicationProducer, Date expirationDate, boolean issued, boolean canceled) {
        this.medicationName = medicationName;
        this.pharmacyName = pharmacyName;
        this.medicationProducer = medicationProducer;
        this.expirationDate = expirationDate;
        this.issued = issued;
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
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

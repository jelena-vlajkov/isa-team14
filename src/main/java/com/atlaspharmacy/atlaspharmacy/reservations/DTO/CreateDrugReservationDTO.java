package com.atlaspharmacy.atlaspharmacy.reservations.DTO;

public class CreateDrugReservationDTO {
    private Long patientId;
    private Long pharmacyId;
    private Long medicationId;
    private int therapyDays;

    public CreateDrugReservationDTO() {
    }

    public CreateDrugReservationDTO(Long patientId, Long pharmacyId, Long medicationId, int therapyDays) {
        this.patientId = patientId;
        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.therapyDays = therapyDays;
    }

    public int getTherapyDays() {
        return therapyDays;
    }

    public void setTherapyDays(int therapyDays) {
        this.therapyDays = therapyDays;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }
}

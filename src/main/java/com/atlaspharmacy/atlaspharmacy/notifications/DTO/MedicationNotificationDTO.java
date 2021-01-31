package com.atlaspharmacy.atlaspharmacy.notifications.DTO;

public class MedicationNotificationDTO {
    private String medicationName;
    private Long medicationId;
    private Long pharmacyId;
    private String pharmacyName;

    public MedicationNotificationDTO() {
    }

    public MedicationNotificationDTO(String medicationName, Long id, Long pharmacyId, String pharmacyName) {
        this.medicationName = medicationName;
        this.medicationId = id;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}

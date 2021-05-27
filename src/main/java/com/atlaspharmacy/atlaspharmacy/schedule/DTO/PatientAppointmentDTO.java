package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

public class PatientAppointmentDTO {
    private Long pharmacyId;
    private Long patientId;
    private Long medicalStaffId;
    private String date;


    public PatientAppointmentDTO() {
    }

    public PatientAppointmentDTO(Long pharmacyId, Long patientId, Long medicalStaffId, String date) {
        this.pharmacyId = pharmacyId;
        this.patientId = patientId;
        this.medicalStaffId = medicalStaffId;
        this.date = date;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

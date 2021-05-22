package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import java.util.Date;

public class ScheduleAppointmentDTO {
    private Long medicalStaffId;
    private Date startTime;
    private Date endTime;
    private Long patientId;
    private String type;
    private Long pharmacyId;

    public ScheduleAppointmentDTO(Long medicalStaffId, Date startTime, Date endTime, Long patientId, String type) {
        this.medicalStaffId = medicalStaffId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        this.type = type;
    }

    public ScheduleAppointmentDTO() {
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

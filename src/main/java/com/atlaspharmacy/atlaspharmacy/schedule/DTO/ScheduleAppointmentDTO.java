package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import java.util.Date;

public class ScheduleAppointmentDTO {
    private Long medicalStaffId;
    private Date startDate;
    private Date endDate;
    private Long patientId;
    private String type;

    public ScheduleAppointmentDTO(Long medicalStaffId, Date startDate, Date endDate, Long patientId, String type) {
        this.medicalStaffId = medicalStaffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patientId = patientId;
        this.type = type;
    }

    public ScheduleAppointmentDTO() {
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

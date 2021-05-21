package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import java.util.Date;

public class AppointmentDTO {
    private Long id;
    private Date startTime;
    private Date endTime;
    private double appointmentCost;
    private String appointmentType;
    private boolean canceled;
    private String patientName;
    private String patientEmail;
    private String medicalStaffName;
    private String medicalStaffEmail;
    private long duration;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Date startTime, Date endTime, double appointmentCost, String type, boolean isCanceled, String patientName, String patientEmail, String medicalStaffName, String medicalStaffEmail, Long id, long duration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentCost = appointmentCost;
        this.appointmentType = type;
        this.canceled = isCanceled;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.medicalStaffEmail = medicalStaffEmail;
        this.medicalStaffName = medicalStaffName;
        this.id = id;
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getAppointmentCost() {
        return appointmentCost;
    }

    public void setAppointmentCost(double appointmentCost) {
        this.appointmentCost = appointmentCost;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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

    public String getMedicalStaffName() {
        return medicalStaffName;
    }

    public void setMedicalStaffName(String medicalStaffName) {
        this.medicalStaffName = medicalStaffName;
    }

    public String getMedicalStaffEmail() {
        return medicalStaffEmail;
    }

    public void setMedicalStaffEmail(String medicalStaffEmail) {
        this.medicalStaffEmail = medicalStaffEmail;
    }
}


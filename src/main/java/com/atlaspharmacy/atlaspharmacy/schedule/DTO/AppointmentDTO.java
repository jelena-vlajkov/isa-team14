package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import java.util.Date;

public class AppointmentDTO {
    private Date startTime;
    private Date endTime;
    private double cost;
    private String type;
    private boolean isCanceled;
    private String patientName;
    private String patientEmail;
    private String medicalStaffName;
    private String medicalStaffEmail;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Date startTime, Date endTime, double cost, String type, boolean isCanceled, String patientName, String patientEmail, String medicalStaffName, String medicalStaffEmail) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.type = type;
        this.isCanceled = isCanceled;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.medicalStaffEmail = medicalStaffEmail;
        this.medicalStaffName = medicalStaffName;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
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


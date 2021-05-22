package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;

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
    private Long pharmacyId;
    private Long patientId;
    private boolean finished;


    public AppointmentDTO() {
    }

    public AppointmentDTO(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AppointmentDTO(Date startTime, Date endTime, double appointmentCost, String type, boolean isCanceled, String patientName, String patientEmail, String medicalStaffName, String medicalStaffEmail, Long pharmacyId) {
       this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentCost = appointmentCost;
        this.appointmentType = type;
        this.canceled = isCanceled;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.medicalStaffEmail = medicalStaffEmail;
        this.medicalStaffName = medicalStaffName;
        this.pharmacyId = pharmacyId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


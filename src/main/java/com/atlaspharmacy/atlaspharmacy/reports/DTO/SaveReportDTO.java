package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import com.atlaspharmacy.atlaspharmacy.reports.domain.enums.ReportType;

import java.util.Date;
import java.util.List;

public class SaveReportDTO {
    private Date date;
    private List<String> medications;
    private Long patientId;
    private String reportNotes;
    private Long medicalStaffId;
    private String type;
    private Long appointmentId;
    private Long pharmacyId;

    public SaveReportDTO() {
        date = new Date();
    }


    public SaveReportDTO(Date date, List<String> medications, Long patientId, String reportNotes, Long medicalStaffId, String type, Long pharmacyId) {
        this.date = date;
        this.medications = medications;
        this.patientId = patientId;
        this.reportNotes = reportNotes;
        this.medicalStaffId = medicalStaffId;
        this.type = type;
        this.pharmacyId = pharmacyId;
    }


    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getReportNotes() {
        return reportNotes;
    }

    public void setReportNotes(String reportNotes) {
        this.reportNotes = reportNotes;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExaminationType() {
        return type.equals(ReportType.Values.Examination);
    }
}

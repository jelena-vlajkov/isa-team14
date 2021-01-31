package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import java.util.Date;
import java.util.List;

public class ReportDTO {

    private Date date;
    private List<String> medications;
    private String patientName;
    private String patientEmail;
    private String reportNotes;

    public ReportDTO() {
    }

    public ReportDTO(Date date, List<String> medications, String patientName, String patientEmail, String reportNotes) {
        this.date = date;
        this.medications = medications;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.reportNotes = reportNotes;
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

    public String getReportNotes() {
        return reportNotes;
    }

    public void setReportNotes(String reportNotes) {
        this.reportNotes = reportNotes;
    }
}

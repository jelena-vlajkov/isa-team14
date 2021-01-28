package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;

import java.util.Date;
import java.util.List;

public class SaveReportDTO {
    private Date date;
    private List<String> medications;
    private Long patientId;
    private String reportNotes;
    private Long dermatologistId;

    public SaveReportDTO() {
    }

    public SaveReportDTO(Date date, List<String> medications, Long patientId, String reportNotes, Long dermatologistId) {
        this.date = date;
        this.medications = medications;
        this.patientId = patientId;
        this.reportNotes = reportNotes;
        this.dermatologistId = dermatologistId;
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

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }
}

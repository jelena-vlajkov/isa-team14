package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;

import java.util.Date;

public class DrugInquiryReportDTO {
    private Date date;
    private MedicationDTO medication;

    public DrugInquiryReportDTO() {}

    public DrugInquiryReportDTO(Date date, MedicationDTO medicationDTO) {
        this.date = date;
        this.medication = medicationDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medicationDTO) {
        this.medication = medicationDTO;
    }
}

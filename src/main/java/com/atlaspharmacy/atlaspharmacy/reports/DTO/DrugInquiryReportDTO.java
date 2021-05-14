package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;

import java.util.Date;

public class DrugInquiryReportDTO {
    private Date date;
    private MedicationDTO medicationDTO;

    public DrugInquiryReportDTO() {}

    public DrugInquiryReportDTO(Date date, MedicationDTO medicationDTO) {
        this.date = date;
        this.medicationDTO = medicationDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MedicationDTO getMedicationDTO() {
        return medicationDTO;
    }

    public void setMedicationDTO(MedicationDTO medicationDTO) {
        this.medicationDTO = medicationDTO;
    }
}

package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import java.util.Date;

public class SearchParametersDTO {
    private String name;
    private Date date;
    private Long medicalStaffId;

    public SearchParametersDTO() {
    }

    public SearchParametersDTO(String name, Date date, Long medicalStaffId) {
        this.name = name;
        this.date = date;
        this.medicalStaffId = medicalStaffId;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

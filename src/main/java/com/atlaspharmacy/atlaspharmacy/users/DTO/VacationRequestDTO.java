package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;

import java.util.Date;

public class VacationRequestDTO {
    private Long id;
    private Long medicalStaffId;
    private Date startDate;
    private Date endDate;
    private String vacationReason;
    private MedicalStaffDTO medicalStaffDTO;

    public VacationRequestDTO() {
    }

    public VacationRequestDTO(Long id, Long medicalStaffId, Date startDate, Date endDate, String vacationReason, MedicalStaffDTO medicalStaffDTO) {
        this.id = id;
        this.medicalStaffId = medicalStaffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vacationReason = vacationReason;
        this.medicalStaffDTO = medicalStaffDTO;
    }

    public MedicalStaffDTO getMedicalStaffDTO() {
        return medicalStaffDTO;
    }

    public void setMedicalStaffDTO(MedicalStaffDTO medicalStaffDTO) {
        this.medicalStaffDTO = medicalStaffDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getVacationReason() {
        return vacationReason;
    }

    public void setVacationReason(String vacationReason) {
        this.vacationReason = vacationReason;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }
}

package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class VacationRequestDTO {
    private String startDate;
    private String endDate;
    private Long medicalStaffId;
    private String vacationReason;

    public VacationRequestDTO() {
    }

    public VacationRequestDTO(String startDate, String endDate, Long medicalStaffId, String vacationReason) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicalStaffId = medicalStaffId;
        this.vacationReason = vacationReason;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public String getVacationReason() {
        return vacationReason;
    }

    public void setVacationReason(String vacationReason) {
        this.vacationReason = vacationReason;
    }
}

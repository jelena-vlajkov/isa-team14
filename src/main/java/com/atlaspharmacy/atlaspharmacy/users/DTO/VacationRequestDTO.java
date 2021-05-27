package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;

import java.util.Date;

public class VacationRequestDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private MedicalStaffDTO medicalStaff;
    private String vacationReason;
    private PharmacyDTO pharmacy;

    public VacationRequestDTO() {}

    public VacationRequestDTO(Long id, Date startDate, Date endDate, MedicalStaffDTO medicalStaff, String vacationReason, PharmacyDTO pharmacy) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicalStaff = medicalStaff;
        this.vacationReason = vacationReason;
        this.pharmacy = pharmacy;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public MedicalStaffDTO getMedicalStaff() { return medicalStaff; }

    public void setMedicalStaff(MedicalStaffDTO medicalStaff) { this.medicalStaff = medicalStaff; }

    public String getVacationReason() { return vacationReason; }

    public void setVacationReason(String vacationReason) {
        this.vacationReason = vacationReason;
    }

    public PharmacyDTO getPharmacy() { return pharmacy; }

    public void setPharmacy(PharmacyDTO pharmacy) { this.pharmacy = pharmacy; }
}
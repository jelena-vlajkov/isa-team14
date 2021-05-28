package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;

import java.util.Date;

public class VacationRequestDTO {
    private Long id;
    private Long medicalStaffId;
    private Date startDate;
    private Date endDate;
    private String vacationReason;
    private MedicalStaffDTO medicalStaff;
    private PharmacyDTO pharmacy;

    public VacationRequestDTO(Long id, Long medicalStaffId, Date startDate
            , Date endDate, String vacationReason, MedicalStaffDTO medicalStaffDTO,PharmacyDTO pharmacyDTO) {
        this.id = id;
        this.medicalStaffId = medicalStaffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vacationReason = vacationReason;
        this.medicalStaff = medicalStaffDTO;
        this.pharmacy = pharmacyDTO;
    }

    public MedicalStaffDTO getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaffDTO medicalStaffDTO) {
        this.medicalStaff = medicalStaffDTO;
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

    public PharmacyDTO getPharmacy() { return pharmacy; }

    public void setPharmacy(PharmacyDTO pharmacy) { this.pharmacy = pharmacy; }
}

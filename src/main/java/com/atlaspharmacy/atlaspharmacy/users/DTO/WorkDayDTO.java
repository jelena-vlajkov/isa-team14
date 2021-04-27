package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

public class WorkDayDTO {
    private Long id;
    private Date date;
    private Date startTime;
    private Date endTime;
    private MedicalStaffDTO medicalStaff;
    private PharmacyDTO pharmacy;

    public WorkDayDTO() { }

    public WorkDayDTO(Long id, Date date, Date startTime, Date endTime, MedicalStaffDTO medicalStaff, PharmacyDTO pharmacy) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.medicalStaff = medicalStaff;
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public MedicalStaffDTO getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaffDTO medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }
}

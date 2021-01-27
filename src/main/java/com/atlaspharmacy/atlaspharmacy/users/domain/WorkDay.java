package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "workdays")
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private int startTime;
    private int endTime;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MedicalStaff medicalStaff;

    public WorkDay() {
    }

    public WorkDay(Date date, int startTime, int endTime, MedicalStaff medicalStaff) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.medicalStaff = medicalStaff;
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public boolean isSameDate(Date date) {
        return this.getDate().getDate() == date.getDate() &&
                this.getDate().getMonth() == date.getMonth() &&
                this.getDate().getYear() == date.getYear();
    }

    public boolean isSameDateAndMedicalStaff(Date date, Long medicalStaffId) {
        return this.getDate().getDate() == date.getDate() &&
                this.getDate().getMonth() == date.getMonth() &&
                this.getDate().getYear() == date.getYear() &&
                this.getMedicalStaff().getId() == medicalStaffId;
    }

    public boolean isMedicalStaff(Long medicalStaffId) {
        return this.getMedicalStaff().getId() == medicalStaffId;
    }

    public boolean isPharmacist() {
        return getMedicalStaff().getRole().equals(Role.Values.Pharmacist);
    }

    public boolean isDermatologist() {
        return getMedicalStaff().getRole().equals(Role.Values.Dermatologist);
    }
}

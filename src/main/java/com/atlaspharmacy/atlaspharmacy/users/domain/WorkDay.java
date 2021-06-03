package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "workdays")
public class WorkDay {
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 1", nullable = false)
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "startTime", column = @Column(name = "workdayStartTime")),
            @AttributeOverride( name = "endTime", column = @Column(name = "workdayEndTime"))
    })
    private Period workDayPeriod;
    @ManyToOne(fetch = FetchType.EAGER)
    private MedicalStaff medicalStaff;
    @OneToOne(fetch = FetchType.EAGER)
    private Pharmacy pharmacy;
    private boolean disabled;

    public WorkDay() {}

    public WorkDay(Long id, Date date, Period workDayPeriod, MedicalStaff medicalStaff, Pharmacy pharmacy) {
        this.id = id;
        this.date = date;
        this.workDayPeriod = workDayPeriod;
        this.medicalStaff = medicalStaff;
        this.pharmacy = pharmacy;
    }
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Period getWorkDayPeriod() {
        return workDayPeriod;
    }

    public void setWorkDayPeriod(Period workDayPeriod) {
        this.workDayPeriod = workDayPeriod;
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
                this.getMedicalStaff().getId().equals(medicalStaffId);
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

    public Pharmacy getPharmacy() { return pharmacy; }

    public boolean isOccupied(Period period) {
       return getWorkDayPeriod().getStartTime().before(period.getEndTime()) &&
                period.getStartTime().after(getWorkDayPeriod().getEndTime());
    }

    public void setPharmacy(Pharmacy pharmacy) { this.pharmacy = pharmacy; }
}

package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.VacationRequestStatus;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacation_requests")
@DiscriminatorValue(value = Role.Values.Patient)
@Proxy(lazy = false)
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date startDate;
    private Date endDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private MedicalStaff medicalStaff;
    private String vacationReason;
    private VacationRequestStatus status;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacy pharmacy;

    public VacationRequest() {
    }

    public VacationRequest(Date startDate, Date endDate, MedicalStaff medicalStaff, String vacationReason,Pharmacy pharmacy) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicalStaff = medicalStaff;
        this.vacationReason = vacationReason;
        this.status = VacationRequestStatus.PENDING;
        this.pharmacy = pharmacy;
    }

    public String getVacationReason() {
        return vacationReason;
    }

    public void setVacationReason(String vacationReason) {
        this.vacationReason = vacationReason;
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

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public VacationRequestStatus getStatus() { return status; }

    public void setStatus(VacationRequestStatus status) { this.status = status; }

    public Pharmacy getPharmacy() { return pharmacy; }

    public void setPharmacy(Pharmacy pharmacy) { this.pharmacy = pharmacy; }
}

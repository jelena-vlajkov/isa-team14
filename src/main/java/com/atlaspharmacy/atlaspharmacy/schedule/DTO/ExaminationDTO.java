package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.persistence.*;

public class ExaminationDTO {
    private Long id;
    private PeriodDTO appointmentPeriod;
    private double cost;
    private boolean isCanceled;
    private PatientDTO patient;
    private PharmacyDTO pharmacy;
    private DermatologistDTO dermatologist;

    public ExaminationDTO(Long id, PeriodDTO appointmentPeriod, double cost, boolean isCanceled, PatientDTO patient, PharmacyDTO pharmacy, DermatologistDTO dermatologist) {
        this.id = id;
        this.appointmentPeriod = appointmentPeriod;
        this.cost = cost;
        this.isCanceled = isCanceled;
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeriodDTO getAppointmentPeriod() {
        return appointmentPeriod;
    }

    public void setAppointmentPeriod(PeriodDTO appointmentPeriod) {
        this.appointmentPeriod = appointmentPeriod;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public DermatologistDTO getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(DermatologistDTO dermatologist) {
        this.dermatologist = dermatologist;
    }
}

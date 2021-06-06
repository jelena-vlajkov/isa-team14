package com.atlaspharmacy.atlaspharmacy.membershipinfo.domain;

import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Proxy(lazy = false)
public class PenaltyMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Patient patient;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private DrugReservation drugReservation;
    private Date givenDate;

    public PenaltyMedication(Patient patient, DrugReservation drugReservation) {
        this.patient = patient;
        this.drugReservation = drugReservation;
    }

    public PenaltyMedication() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DrugReservation getDrugReservation() {
        return drugReservation;
    }

    public void setDrugReservation(DrugReservation drugReservation) {
        this.drugReservation = drugReservation;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }
}

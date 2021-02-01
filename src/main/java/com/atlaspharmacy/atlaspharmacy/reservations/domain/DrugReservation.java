package com.atlaspharmacy.atlaspharmacy.reservations.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "drugreservation")
@Proxy(lazy = false)
public class DrugReservation {
    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Date reservationDate;
    private int uniqueIdentifier;
    private Date expirationDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Medication medication;
    @ManyToOne(fetch =  FetchType.EAGER, cascade = CascadeType.MERGE)
    private Patient patient;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacy pharmacy;
    private boolean issued;
    public DrugReservation() {

    }

    public DrugReservation(Long id, int uniqueIdentificator, Date expirationDate, Medication medication, Patient patient, Pharmacy pharmacy) {
        this.id = id;
        this.reservationDate = new Date();
        this.uniqueIdentifier = uniqueIdentificator;
        this.expirationDate = expirationDate;
        this.medication = medication;
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.issued = false;
    }

    public DrugReservation(int uniqueIdentificator, Date expirationDate, Medication medication, Patient patient, Pharmacy pharmacy) {
        this.reservationDate = new Date();
        this.uniqueIdentifier = uniqueIdentificator;
        this.expirationDate = expirationDate;
        this.medication = medication;
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.issued = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(int uniqueIdentificator) {
        this.uniqueIdentifier = uniqueIdentificator;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public boolean isExpired() {
        System.out.println(getExpirationDate());
        System.out.println(getExpirationDate().before(new Date(getExpirationDate().getTime() + 24 * 60 * 60 * 1000)));
        return getExpirationDate().before(new Date((new Date()).getTime() + 24 * 60 * 60 * 1000));
    }

    public boolean isPharmacy(Long pharmacyId) {
        return getPharmacy().getId() == pharmacyId;
    }
}

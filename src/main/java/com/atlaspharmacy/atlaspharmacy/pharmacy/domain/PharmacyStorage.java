package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
public class PharmacyStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Medication medication;
    private double quantity;

    public PharmacyStorage() {
    }

    public PharmacyStorage(Pharmacy pharmacy, Medication medication, double quantity) {
        this.pharmacy = pharmacy;
        this.medication = medication;
        this.quantity = quantity;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPharmacy(Long pharmacyId) {
        return getPharmacy().getId().equals(pharmacyId);
    }

    public boolean isMediationAndPharmacy(Long pharmacyId, Long medicationId) {
        return getMedication().getId().equals(medicationId) && isPharmacy(pharmacyId);
    }
}

package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;

import javax.persistence.*;

@Entity
@Table(name = "pricelists")
public class Pricelist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Medication medication;

    private Long price;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "startTime", column = @Column(name = "startPeriod")),
            @AttributeOverride( name = "endTime", column = @Column(name = "endPeriod"))
    })
    private Period validPeriod;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacy pharmacy;

    public Pricelist() {
    }

    public Pricelist(Long id, Medication medication, Long price, Period validPeriod, Pharmacy pharmacy) {
        this.id = id;
        this.medication = medication;
        this.price = price;
        this.validPeriod = validPeriod;
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Period getPeriod() {
        return validPeriod;
    }

    public void setPeriod(Period validPeriod) {
        this.validPeriod = validPeriod;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

}

package com.atlaspharmacy.atlaspharmacy.promotions.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;

import javax.persistence.*;


@Entity
@Table(name="promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "startTime", column = @Column(name = "promotionStartTime")),
            @AttributeOverride( name = "endTime", column = @Column(name = "promotionEndTime"))
    })
    private Period activePeriod;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Period getActivePeriod() {
        return activePeriod;
    }

    public void setActivePeriod(Period activePeriod) {
        this.activePeriod = activePeriod;
    }

    public Promotion(){}

    public Promotion(Long id, String description, Period activePeriod, Pharmacy pharmacy) {
        this.id = id;
        this.description = description;
        this.activePeriod = activePeriod;
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}

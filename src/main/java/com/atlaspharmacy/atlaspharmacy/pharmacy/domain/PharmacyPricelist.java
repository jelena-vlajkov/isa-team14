package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pharmacy_pricelist")
public class PharmacyPricelist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacy pharmacy;
    private Date startDate;
    private Date endDate;
    private double cost;
    private String type;


    public PharmacyPricelist() {
    }

    public PharmacyPricelist(Pharmacy pharmacy, Date startDate, Date endDate, double cost, String type) {
        this.pharmacy = pharmacy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isInRange(Date date) {
        if(startDate.before(date) && endDate.after(date)) {
            return true;
        }
        return false;
    }
}

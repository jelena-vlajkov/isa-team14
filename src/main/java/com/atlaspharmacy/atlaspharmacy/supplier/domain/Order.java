package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.enums.MedicationOrderStatus;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Proxy(lazy = false)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dueDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacy pharmacy;
    private int uniqueidentifier;

    private MedicationOrderStatus status;

    public Order(Long id, Date dueDate, Pharmacy pharmacy, int uniqueidentifier) {
        this.id = id;
        this.dueDate = dueDate;
        this.pharmacy = pharmacy;
        this.uniqueidentifier = uniqueidentifier;
        this.status = MedicationOrderStatus.WAITING_FOR_OFFERS;
    }

    public Order() {

    }

    public int getUniqueidentifier() {
        return uniqueidentifier;
    }

    public void setUniqueidentifier(int uniqueidentifier) {
        this.uniqueidentifier = uniqueidentifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Pharmacy getPharmacy() { return pharmacy; }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public MedicationOrderStatus getStatus() { return status; }

    public void setStatus(MedicationOrderStatus status) { this.status = status; }
}

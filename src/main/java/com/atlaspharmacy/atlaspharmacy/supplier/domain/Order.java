package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
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
    private Date editableDue;
    private int uniqueidentifier;

    public Order(Long id, Date dueDate, Pharmacy pharmacy, Date editableDue, int uniqueidentifier) {
        this.id = id;
        this.dueDate = dueDate;
        this.pharmacy = pharmacy;
        this.editableDue = editableDue;
        this.uniqueidentifier = uniqueidentifier;
    }

    public Order() {

    }

    public int getUniqueidentifier() {
        return uniqueidentifier;
    }

    public void setUniqueidentifier(int uniqueidentifier) {
        this.uniqueidentifier = uniqueidentifier;
    }

    public Date getEditableDue() {
        return editableDue;
    }

    public void setEditableDue(Date editableDue) {
        this.editableDue = editableDue;
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

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}

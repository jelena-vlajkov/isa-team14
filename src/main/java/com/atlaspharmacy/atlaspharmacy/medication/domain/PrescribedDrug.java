package com.atlaspharmacy.atlaspharmacy.medication.domain;

import org.hibernate.event.spi.PreCollectionRecreateEvent;

import javax.persistence.*;

@Entity
@Table(name = "prescribed_drugs")
public class PrescribedDrug {
    @Id
    private int id;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Medication prescribedMedication;

    public PrescribedDrug(){}

    public PrescribedDrug(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Medication getMedication() {
        return prescribedMedication;
    }

    public void setMedication(Medication medication) {
        this.prescribedMedication = medication;
    }
}

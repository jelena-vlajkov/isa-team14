package com.atlaspharmacy.atlaspharmacy.medication.domain;

import org.hibernate.event.spi.PreCollectionRecreateEvent;

import javax.persistence.*;

@Entity
@Table(name = "prescribed_drugs")
public class PrescribedDrug {
    @Id
    private Long id;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Medication prescribedMedication;

    public PrescribedDrug(){}

    public PrescribedDrug(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Medication getMedication() {
        return prescribedMedication;
    }

    public void setMedication(Medication medication) {
        this.prescribedMedication = medication;
    }
}

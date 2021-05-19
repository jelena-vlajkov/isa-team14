package com.atlaspharmacy.atlaspharmacy.medication.domain;

import javax.persistence.*;

@Entity
@Table(name = "prescribed_drugs")
public class PrescribedDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private EPrescription eprescription;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Medication prescribedMedication;

    public PrescribedDrug(){}

    public PrescribedDrug(Long id, Long quantity, EPrescription eprescription) {
        this.id = id;
        this.quantity = quantity;
        this.eprescription = eprescription;
    }

    public EPrescription getEprescription() {
        return eprescription;
    }

    public void setEprescription(EPrescription eprescription) {
        this.eprescription = eprescription;
    }

    public Medication getPrescribedMedication() {
        return prescribedMedication;
    }

    public void setPrescribedMedication(Medication prescribedMedication) {
        this.prescribedMedication = prescribedMedication;
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

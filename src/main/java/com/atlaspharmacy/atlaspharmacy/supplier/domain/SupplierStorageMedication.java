package com.atlaspharmacy.atlaspharmacy.supplier.domain;


import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "suppliersmedication")
@Proxy(lazy = false)
public class SupplierStorageMedication {
    @Id
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "medication", column = @Column(name = "medication_id")),
            @AttributeOverride( name = "quantity", column = @Column(name = "quantity")),
    })
    private SupplierMedicationInStorage medications;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Supplier supplier;
    public SupplierStorageMedication() {
    }

    public SupplierStorageMedication(Long id, SupplierMedicationInStorage medications) {
        this.id = id;
        this.medications = medications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SupplierMedicationInStorage getMedications() {
        return medications;
    }

    public void setMedications(SupplierMedicationInStorage medications) {
        this.medications = medications;
    }
}

package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Proxy(lazy = false)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "medication", column = @Column(name = "medication_id")),
            @AttributeOverride( name = "quantity", column = @Column(name = "quantity")),
    })
    private MedicationOrder medicationOrder;

    private Date dueDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    private Supplier supplier;


    public Order(Long id, MedicationOrder medicationOrder, Date dueDate, Supplier supplier) {
        this.id = id;
        this.medicationOrder = medicationOrder;
        this.dueDate = dueDate;
        this.supplier = supplier;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicationOrder getMedicationOrder() {
        return medicationOrder;
    }

    public void setMedicationOrder(MedicationOrder medicationOrder) {
        this.medicationOrder = medicationOrder;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Supplier getSupplier(){
        return this.supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}

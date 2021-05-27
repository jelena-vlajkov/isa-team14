package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "orderedmedication")
@Proxy(lazy = false)
public class MedicationInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "medication", column = @Column(name = "medication_id")),
            @AttributeOverride( name = "quantity", column = @Column(name = "quantity")),
    })
    private OrderedMedication orderedMedication;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Order order;


    public MedicationInOrder(Long id, OrderedMedication orderedMedication, Order order) {
        this.id = id;
        this.orderedMedication = orderedMedication;
        this.order = order;
    }

    public MedicationInOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderedMedication getOrderedMedication() {
        return orderedMedication;
    }

    public void setOrderedMedication(OrderedMedication orderedMedication) {
        this.orderedMedication = orderedMedication;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

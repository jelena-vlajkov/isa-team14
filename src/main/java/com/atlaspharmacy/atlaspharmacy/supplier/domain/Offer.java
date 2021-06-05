package com.atlaspharmacy.atlaspharmacy.supplier.domain;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.enums.OfferStatus;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offers")
@Proxy(lazy = false)
public class Offer {
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 1", nullable = false)
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Order order;
    private int uniqueidentifier;
    private Long price;
    private Date dueDelivery;
    private OfferStatus offerStatus;

    public Offer(Long id, Supplier supplier, Order order, int uniqueidentifier, Long price, Date dueDelivery, Date editableDue) {
        this.id = id;
        this.supplier = supplier;
        this.order = order;
        this.uniqueidentifier = uniqueidentifier;
        this.price = price;
        this.dueDelivery = dueDelivery;
    }

    public Offer() {

    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public int getUniqueidentifier() {
        return uniqueidentifier;
    }

    public void setUniqueidentifier(int uniqueidentifier) {
        this.uniqueidentifier = uniqueidentifier;
    }


    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getDueDelivery() {
        return dueDelivery;
    }

    public void setDueDelivery(Date dueDelivery) {
        this.dueDelivery = dueDelivery;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


}

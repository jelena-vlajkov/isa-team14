package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.enums.OfferStatus;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

public class OfferDTO {
    private Long id;
    private SupplierDTO supplier;
    private OrderDTO order;
    private OfferStatus offerStatus;
    private int uniqueidentifier;
    private Long price;
    private Date dueDelivery;

    public OfferDTO() {
    }

    public OfferDTO(Long id, SupplierDTO supplier, OrderDTO order, OfferStatus offerStatus, int uniqueidentifier, Long price, Date dueDelivery) {
        this.id = id;
        this.supplier = supplier;
        this.order = order;
        this.offerStatus = offerStatus;
        this.uniqueidentifier = uniqueidentifier;
        this.price = price;
        this.dueDelivery = dueDelivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getUniqueidentifier() {
        return uniqueidentifier;
    }

    public void setUniqueidentifier(int uniqueidentifier) {
        this.uniqueidentifier = uniqueidentifier;
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
}

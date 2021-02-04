package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

import java.util.Date;

public class OrderDTO {
    private Long id;
    private OrderedMedication orderedMedication;
    private Date dueDate;
    private SupplierDTO supplier;

    public OrderDTO() {
    }

    public OrderDTO(Long id, OrderedMedication orderedMedication, Date dueDate, SupplierDTO supplier) {
        this.id = id;
        this.orderedMedication = orderedMedication;
        this.dueDate = dueDate;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderedMedication getMedicationOrder() {
        return orderedMedication;
    }

    public void setMedicationOrder(OrderedMedication orderedMedication) {
        this.orderedMedication = orderedMedication;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}

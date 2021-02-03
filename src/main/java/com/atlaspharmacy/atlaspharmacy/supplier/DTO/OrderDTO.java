package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Supplier;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.Date;

public class OrderDTO {
    private Long id;
    private MedicationOrder medicationOrder;
    private Date dueDate;
    private SupplierDTO supplier;

    public OrderDTO() {
    }

    public OrderDTO(Long id, MedicationOrder medicationOrder, Date dueDate, SupplierDTO supplier) {
        this.id = id;
        this.medicationOrder = medicationOrder;
        this.dueDate = dueDate;
        this.supplier = supplier;
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

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}

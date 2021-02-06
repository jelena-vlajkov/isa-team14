package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private List<OrderedMedication> orderedMedications;
    private Date dueDate;
    private Pharmacy pharmacy;




    public OrderDTO() {
    }

    public OrderDTO(Long id, List<OrderedMedication> orderedMedications, Date dueDate, Pharmacy pharmacy) {
        this.id = id;
        this.orderedMedications = orderedMedications;
        this.dueDate = dueDate;
        this.pharmacy = pharmacy;
    }

    public List<OrderedMedication> getOrderedMedications() {
        return orderedMedications;
    }

    public void setOrderedMedications(List<OrderedMedication> orderedMedications) {
        this.orderedMedications = orderedMedications;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}

package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Date dueDate;
    private List<OrderedMedicationDTO> orderedMedications;
    private PharmacyDTO pharmacy;
    private int uniqueidentifier;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Date dueDate, List<OrderedMedicationDTO> orderedMedications,
                    PharmacyDTO pharmacy, int uniqueidentifier) {
        this.id = id;
        this.orderedMedications = orderedMedications;
        this.dueDate = dueDate;
        this.pharmacy = pharmacy;
        this.uniqueidentifier = uniqueidentifier;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<OrderedMedicationDTO> getOrderedMedications() {
        return orderedMedications;
    }

    public void setOrderedMedications(List<OrderedMedicationDTO> orderedMedication) {
        this.orderedMedications = orderedMedication;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getUniqueidentifier() {
        return uniqueidentifier;
    }

    public void setUniqueidentifier(int uniqueidentifier) {
        this.uniqueidentifier = uniqueidentifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

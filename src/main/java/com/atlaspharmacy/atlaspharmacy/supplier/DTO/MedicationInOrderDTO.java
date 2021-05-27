package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

public class MedicationInOrderDTO {
    private Long id;
    private OrderedMedicationDTO orderedMedication;
    private OrderDTO order;

    public MedicationInOrderDTO() {}

    public MedicationInOrderDTO(Long id, OrderedMedicationDTO orderedMedication, OrderDTO order) {
        this.id = id;
        this.orderedMedication = orderedMedication;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderedMedicationDTO getOrderedMedication() {
        return orderedMedication;
    }

    public void setOrderedMedication(OrderedMedicationDTO orderedMedication) {
        this.orderedMedication = orderedMedication;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}

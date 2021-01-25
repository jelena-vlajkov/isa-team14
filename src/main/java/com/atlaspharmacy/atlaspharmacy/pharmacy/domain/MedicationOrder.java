package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

public class MedicationOrder {
    private int quantity;

    public MedicationOrder(int quantity) {
        this.quantity = quantity;
    }
    public MedicationOrder(){}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

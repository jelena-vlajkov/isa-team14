package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;

import java.util.List;

public interface IMedicationInOrderService {
    List<MedicationInOrder> getAllMedicationsByOrder(Long id);
}

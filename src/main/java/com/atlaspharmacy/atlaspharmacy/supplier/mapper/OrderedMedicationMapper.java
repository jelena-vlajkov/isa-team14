package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;

public class OrderedMedicationMapper {
    private OrderedMedicationMapper(){}

    public static OrderedMedication mapToOrderedMedication(OrderedMedicationDTO dto){
        return new OrderedMedication(dto.getMedicationId(),dto.getQuantity());
    }
}

package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;

import java.util.ArrayList;
import java.util.List;

public class OrderedMedicationMapper {
    private OrderedMedicationMapper(){}
    public static OrderedMedication mapDTOtoMedication(OrderedMedicationDTO orderedMedicationDTO){
       OrderedMedication orderedMedication=new OrderedMedication(orderedMedicationDTO.getMedicationId(), orderedMedicationDTO.getQuantity());
       return orderedMedication;

    }
}

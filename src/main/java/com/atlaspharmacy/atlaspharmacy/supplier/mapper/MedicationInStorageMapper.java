package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierMedicationInStorage;

public class MedicationInStorageMapper {
    private MedicationInStorageMapper(){}

    public static OrderedMedicationDTO mapToDTO(SupplierMedicationInStorage supplierMedicationInStorage, Medication medication){
        OrderedMedicationDTO dto = new OrderedMedicationDTO();
        dto.setMedicationId(medication.getId());
        dto.setQuantity(supplierMedicationInStorage.getQuantity());
        return dto;
    }

    public static SupplierMedicationInStorage mapToSupplierMedicationStorage(OrderedMedicationDTO dto){
        SupplierMedicationInStorage supplierMedicationInStorage = new SupplierMedicationInStorage();
        supplierMedicationInStorage.setMedication_id(dto.getMedicationId());
        supplierMedicationInStorage.setQuantity(dto.getQuantity());
        return supplierMedicationInStorage;
    }

}

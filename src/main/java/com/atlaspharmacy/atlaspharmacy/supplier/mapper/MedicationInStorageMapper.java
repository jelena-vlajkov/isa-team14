package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.MedicationInStorageDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierMedicationInStorage;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierStorageMedication;

import java.util.List;

public class MedicationInStorageMapper {
    private MedicationInStorageMapper(){}

    public static MedicationInStorageDTO mapToDTO(SupplierMedicationInStorage supplierMedicationInStorage, Medication medication){
        MedicationInStorageDTO dto = new MedicationInStorageDTO();
        dto.setMedication(MedicationMapper.convertToMedicationDTO(medication));
        dto.setQuantity(supplierMedicationInStorage.getQuantity());
        return dto;
    }

    public static SupplierMedicationInStorage mapToSupplierMedicationStorage(MedicationInStorageDTO dto){
        SupplierMedicationInStorage supplierMedicationInStorage = new SupplierMedicationInStorage();
        supplierMedicationInStorage.setMedication_id(dto.getMedication().getId());
        supplierMedicationInStorage.setQuantity(dto.getQuantity());
        return supplierMedicationInStorage;
    }

}

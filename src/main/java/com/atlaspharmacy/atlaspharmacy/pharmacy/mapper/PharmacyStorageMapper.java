package com.atlaspharmacy.atlaspharmacy.pharmacy.mapper;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.MedicationInStorageDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;

import java.util.ArrayList;
import java.util.List;

public class PharmacyStorageMapper {
    private PharmacyStorageMapper() { }

    public static MedicationInStorageDTO mapToDto(PharmacyStorage pharmacyStorage) {
        return new MedicationInStorageDTO(pharmacyStorage.getMedication(),
                pharmacyStorage.getQuantity(), pharmacyStorage.getPharmacy());
    }

    public static List<MedicationInStorageDTO> maptToListDto(List<PharmacyStorage> pharmacyStorageList) {
        List<MedicationInStorageDTO> dtoList = new ArrayList<>();

        for(PharmacyStorage pharmacyStorage : pharmacyStorageList) {
            dtoList.add(mapToDto(pharmacyStorage));
        }

        return dtoList;
    }
}

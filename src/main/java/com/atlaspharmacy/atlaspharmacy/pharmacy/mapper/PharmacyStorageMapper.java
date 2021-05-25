package com.atlaspharmacy.atlaspharmacy.pharmacy.mapper;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyStorageDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;

import java.util.ArrayList;
import java.util.List;

public class PharmacyStorageMapper {
    private PharmacyStorageMapper() { }

    public static PharmacyStorageDTO mapToDto(PharmacyStorage pharmacyStorage) {
        return new PharmacyStorageDTO(pharmacyStorage.getMedication().getId(),pharmacyStorage.getMedication().getName(),
                pharmacyStorage.getQuantity(), pharmacyStorage.getPharmacy(),pharmacyStorage.getMedication().getCode());
    }

    public static List<PharmacyStorageDTO> maptToListDto(List<PharmacyStorage> pharmacyStorageList) {
        List<PharmacyStorageDTO> dtoList = new ArrayList<>();

        for(PharmacyStorage pharmacyStorage : pharmacyStorageList) {
            dtoList.add(mapToDto(pharmacyStorage));
        }

        return dtoList;
    }
}

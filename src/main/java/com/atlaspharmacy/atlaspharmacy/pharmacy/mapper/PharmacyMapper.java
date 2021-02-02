package com.atlaspharmacy.atlaspharmacy.pharmacy.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.MedicationInStorageDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;

import java.util.ArrayList;
import java.util.List;

public class PharmacyMapper {
    private PharmacyMapper() { }

    public static PharmacyDTO mapPharmacyToDTO(Pharmacy pharmacy){
        return new PharmacyDTO(pharmacy.getId(), pharmacy.getName(), pharmacy.getDescription(), AddressMapper.mapAddressToDTO(pharmacy.getAddress()), pharmacy.getAverageGrade());
    }

    public static Pharmacy mapDTOToPharmacy(PharmacyDTO dto){
        return new Pharmacy(dto.getId(), dto.getName(), dto.getDescription(), AddressMapper.mapAddressDTOToAddress(dto.getAddress()), dto.getAverage_grade());
    }
    public static List<PharmacyDTO> maptToListDto(List<Pharmacy> pharmacies) {
        List<PharmacyDTO> dtos = new ArrayList<>();
        for(Pharmacy p : pharmacies){
            dtos.add(mapPharmacyToDTO(p));
        }
        return dtos;
    }
}

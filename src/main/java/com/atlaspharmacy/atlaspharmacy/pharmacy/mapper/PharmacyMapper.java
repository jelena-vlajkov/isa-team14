package com.atlaspharmacy.atlaspharmacy.pharmacy.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;

import java.util.ArrayList;
import java.util.List;

public class PharmacyMapper {
    private PharmacyMapper() { }

    public static PharmacyDTO mapPharmacyToDTO(Pharmacy pharmacy){
        return new PharmacyDTO(pharmacy.getId(), pharmacy.getName(), pharmacy.getDescription(), pharmacy.getEmail(), pharmacy.getTelephone(), AddressMapper.mapAddressToDTO(pharmacy.getAddress()), pharmacy.getAverageGrade());
    }

    public static Pharmacy mapDTOToPharmacy(PharmacyDTO dto){
        Pharmacy p = new Pharmacy();
        p.setId(dto.getId());
        p.setEmail(dto.getEmail());
        p.setTelephone(dto.getTelephone());
        p.setAverageGrade(dto.getAverageGrade());
        p.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getAddress()));
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());

        return p;
    }
    public static List<PharmacyDTO> maptToListDto(List<Pharmacy> pharmacies) {
        List<PharmacyDTO> dtos = new ArrayList<>();
        for(Pharmacy p : pharmacies){
            dtos.add(mapPharmacyToDTO(p));
        }
        return dtos;
    }
    public static List<Pharmacy> maptDTOSToList(List<PharmacyDTO> dtos) {
        List<Pharmacy> pharmacies = new ArrayList<>();
        for(PharmacyDTO dto : dtos){
            pharmacies.add(mapDTOToPharmacy(dto));
        }
        return pharmacies;
    }
}

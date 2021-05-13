package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import java.util.ArrayList;
import java.util.List;

public class DermatologistMapper {
    private DermatologistMapper(){}
    public static DermatologistDTO mapDermatologistToDTO(Dermatologist dermatologist){
        return new DermatologistDTO(dermatologist.getId(), dermatologist.getName(), dermatologist.getSurname(), dermatologist.getDateOfBirth(),
                dermatologist.getPhoneNumber(), dermatologist.getEmail(), dermatologist.getPassword(), dermatologist.getGender(),
                AddressMapper.mapAddressToDTO(dermatologist.getAddress()) , dermatologist.getRole(), AuthorityMapper.authoritiesToListDTOS(dermatologist.getAuthorities()),
                PharmacyMapper.maptToListDto(dermatologist.getPharmacies()), dermatologist.isFirstTimePassword(),dermatologist.getAverageGrade(),dermatologist.getLicenseNumber());
    }

    public static Dermatologist mapDTOToDermatologist(DermatologistDTO dto){
        Dermatologist d = new Dermatologist();
        d.setId(dto.getId());
        d.setName(dto.getName());
        d.setSurname(dto.getSurname());
        d.setDateOfBirth(dto.getDateOfBirth());
        d.setPhoneNumber(dto.getPhoneNumber());
        d.setEmail(dto.getEmail());
        d.setPassword(dto.getPassword());
        d.setGender(dto.getGender());

        d.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getAddress()));
        d.setAuthorities(AuthorityMapper.authoritiesDTOSToList(dto.getAuthorities()));

        d.setPharmacies(PharmacyMapper.maptDTOSToList(dto.getPharmacies()));
        d.setFirstTimePassword(dto.isFirstTimeChanged());
        d.setAverageGrade(dto.getAverageGrade());
        return d;
    }

    public static List<DermatologistDTO> mapToListDTOS(List<Dermatologist> dermatologists){
        List<DermatologistDTO> dtos = new ArrayList<>();
        for(Dermatologist d : dermatologists){
            dtos.add(mapDermatologistToDTO(d));
        }
        return dtos;
    }
}

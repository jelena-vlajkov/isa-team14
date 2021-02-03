package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;

import java.util.Date;

public class DermatologistMapper {
    private DermatologistMapper(){}
    public static DermatologistDTO mapDermatologistToDTO(Dermatologist dermatologist){
        return new DermatologistDTO(dermatologist.getId(), dermatologist.getName(), dermatologist.getSurname(), dermatologist.getDateOfBirth(),
                dermatologist.getPhoneNumber(), dermatologist.getEmail(), dermatologist.getPassword(), dermatologist.getGender(),
                AddressMapper.mapAddressToDTO(dermatologist.getAddress()) , dermatologist.getRole(), AuthorityMapper.authoritiesToListDTOS(dermatologist.getAuthorities()),
                PharmacyMapper.maptToListDto(dermatologist.getPharmacies()));
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
        return d;
    }
}

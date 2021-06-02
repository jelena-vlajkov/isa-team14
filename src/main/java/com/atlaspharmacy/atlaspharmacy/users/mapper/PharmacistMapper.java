package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import java.util.ArrayList;
import java.util.List;

public class PharmacistMapper {
    private PharmacistMapper(){}

    public static PharmacistDTO mapPharmacistToDTO(Pharmacist pharmacist){
        //vratiti za adresu!!!!
         return new PharmacistDTO(pharmacist.getId(), pharmacist.getName(), pharmacist.getSurname(), pharmacist.getDateOfBirth(),
                pharmacist.getPhoneNumber(), pharmacist.getEmail(), pharmacist.getPassword(), pharmacist.getGender(),
                AddressMapper.mapAddressToDTO(pharmacist.getAddress()) , pharmacist.getRole(), AuthorityMapper.authoritiesToListDTOS(pharmacist.getAuthorities()),
                PharmacyMapper.mapPharmacyToDTO(pharmacist.getPharmacy()), pharmacist.isFirstTimePassword(),AverageGradeMapper.mapToDTO(pharmacist.getAverageGrade()),pharmacist.getLicenseNumber());
    }

    public static Pharmacist mapDTOToPharmacist(PharmacistDTO dto){
        Pharmacist d = new Pharmacist();
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

        d.setPharmacy(PharmacyMapper.mapDTOToPharmacy(dto.getPharmacy()));
        d.setFirstTimePassword(dto.isFirstTimeChanged());
        d.setAverageGrade(AverageGradeMapper.mapToAverageGrade(dto.getAverageGrade()));
        return d;
    }
    public static List<PharmacistDTO> mapToListDTOS(List<Pharmacist> pharmacists){
        List<PharmacistDTO> dtos = new ArrayList<>();
        for(Pharmacist p : pharmacists){
            dtos.add(mapPharmacistToDTO(p));
        }
        return dtos;
    }
}

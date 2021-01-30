package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import java.util.List;

public class PatientMapper {
    private PatientMapper(){}

    public static PatientDTO mapPatientToDTO(Patient p){
        return new PatientDTO(p.getId(), p.getName(), p.getSurname(), p.getDateOfBirth(), p.getPhoneNumber(), p.getEmail(), p.getPassword(), p.getGender(), AddressMapper.mapAddressToDTO(p.getAddress()),p.getRole(),AuthorityMapper.authoritiesToListDTOS(p.getAuthorities()));

    }
    public static Patient mapDTOToPatient(PatientDTO dto){
        Patient p = new Patient();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setSurname(dto.getSurname());
        p.setDateOfBirth(dto.getDateOfBirth());
        p.setPhoneNumber(dto.getPhoneNumber());
        p.setEmail(dto.getEmail());
        p.setPassword(dto.getPassword());
        p.setGender(dto.getGender());

        p.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getAddress()));
        p.setAuthorities(AuthorityMapper.authoritiesDTOSToList(dto.getAuthorities()));

        return p;
    }


}

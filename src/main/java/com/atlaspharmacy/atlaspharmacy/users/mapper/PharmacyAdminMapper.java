package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacyAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;

public class PharmacyAdminMapper {
    private PharmacyAdminMapper(){}

    public static PharmacyAdmin mapDTOToPharmacyAdmin(PharmacyAdminDTO dto){
        PharmacyAdmin p = new PharmacyAdmin();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setSurname(dto.getSurname());
        p.setDateOfBirth(dto.getDateOfBirth());
        p.setPhoneNumber(dto.getPhoneNumber());
        p.setEmail(dto.getEmail());
        p.setPassword(dto.getPassword());
        p.setGender(dto.getGender());
        p.setRole(dto.getRole());
        p.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getAddress()));
        p.setAuthorities(AuthorityMapper.authoritiesDTOSToList(dto.getAuthorities()));
        p.setPharmacy(PharmacyMapper.mapDTOToPharmacy(dto.getPharmacy()));
        p.setFirstTimePassword(dto.isFirstTimeChanged());
        return p;
    }

    public static PharmacyAdminDTO mapToDTO(PharmacyAdmin pharmacyAdmin){
        return new PharmacyAdminDTO(pharmacyAdmin.getId(), pharmacyAdmin.getName(),
                pharmacyAdmin.getSurname(), pharmacyAdmin.getDateOfBirth(), pharmacyAdmin.getPhoneNumber(),
                pharmacyAdmin.getEmail(), pharmacyAdmin.getPassword(), pharmacyAdmin.getGender(),
                AddressMapper.mapAddressToDTO(pharmacyAdmin.getAddress()), pharmacyAdmin.getRole(),
                AuthorityMapper.authoritiesToListDTOS(pharmacyAdmin.getAuthorities()),
                PharmacyMapper.mapPharmacyToDTO(pharmacyAdmin.getPharmacy()), pharmacyAdmin.isFirstTimePassword());

    }

}


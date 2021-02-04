package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;

public class SupplierMapper {
    private SupplierMapper(){}
    public static SupplierDTO mapSupplierToDTO(Supplier a){
        return new SupplierDTO(a.getId(), a.getName(), a.getSurname(), a.getDateOfBirth(),
                a.getPhoneNumber(), a.getEmail(), a.getPassword(), a.getGender(),
                AddressMapper.mapAddressToDTO(a.getAddress()),a.getRole(),
                AuthorityMapper.authoritiesToListDTOS(a.getAuthorities()), a.isFirstTimePassword(), a.getFirmName());

    }
    public static Supplier mapDTOToSupplier(SupplierDTO dto){
        Supplier s = new Supplier();
        s.setId(dto.getId());
        s.setName(dto.getName());
        s.setSurname(dto.getSurname());
        s.setDateOfBirth(dto.getDateOfBirth());
        s.setPhoneNumber(dto.getPhoneNumber());
        s.setEmail(dto.getEmail());
        s.setPassword(dto.getPassword());
        s.setGender(dto.getGender());

        s.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getAddress()));
        s.setAuthorities(AuthorityMapper.authoritiesDTOSToList(dto.getAuthorities()));

        s.setFirstTimePassword(dto.isFirstTimePassword());
        s.setFirmName(dto.getFirmName());
        return s;
    }
}

package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.mapper.AuthorityMapper;

public class SupplierMapper {
    private SupplierMapper(){}
    public static SupplierDTO mapSupplierToDTO(Supplier supplier){
        return new SupplierDTO(supplier.getId(), supplier.getName(), AddressMapper.mapAddressToDTO(supplier.getAddress()), supplier.getPhoneNumber(), supplier.getEmail(),
                supplier.getPassword(), supplier.getRole(), AuthorityMapper.supplierAuthoritiesToListDTOS(supplier.getAuthorities()));
    }
    public static Supplier mapSupplierDTOToSupplier(SupplierDTO dto){
        Supplier s = new Supplier();
        s.setId(dto.getId());
        s.setName(dto.getName());
        s.setPhoneNumber(dto.getPhoneNumber());
        s.setEmail(dto.getEmail());
        s.setPassword(dto.getPassword());
        s.setRole(dto.getRole());
        s.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getHeadquarters()));
        s.setAuthorities(AuthorityMapper.supplierAuthoritiesDTOSToList(dto.getAuthorities()));

        return s;
    }
}

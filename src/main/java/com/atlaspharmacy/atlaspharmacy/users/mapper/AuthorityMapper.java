package com.atlaspharmacy.atlaspharmacy.users.mapper;


import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.SupplierAuthority;

import java.util.ArrayList;
import java.util.List;

public class AuthorityMapper {
    private AuthorityMapper(){}

    public static AuthorityDTO mapAuthorityToDTO(Authority authority) {
        AuthorityDTO dto = new AuthorityDTO(authority.getId(), authority.getName());
        return dto;
    }
    public static Authority mapAuthorityDTOToAuthority(AuthorityDTO authorityDTO) {
        Authority authority = new Authority();
        authority.setId(authorityDTO.getId());
        authority.setName(authorityDTO.getName());
        return authority;
    }
    public static List<AuthorityDTO> authoritiesToListDTOS(List<Authority> authorities) {
        List<AuthorityDTO> dtos = new ArrayList<>();
        for(Authority a : authorities){
            dtos.add(mapAuthorityToDTO(a));
        }
        return dtos;
    }
    public static List<Authority> authoritiesDTOSToList(List<AuthorityDTO> dtos) {
        List<Authority> authorities = new ArrayList<>();
        for(AuthorityDTO a : dtos){
            authorities.add(mapAuthorityDTOToAuthority(a));
        }
        return authorities;
    }

    public static AuthorityDTO mapSupplierAuthorityToDTO(SupplierAuthority authority){
        AuthorityDTO dto = new AuthorityDTO(authority.getId(), authority.getName());
        return dto;
    }

    public static SupplierAuthority mapAuthorityDTOToSupplierAuthority(AuthorityDTO authorityDTO) {
        SupplierAuthority authority = new SupplierAuthority();
        authority.setId(authorityDTO.getId());
        authority.setName(authorityDTO.getName());
        return authority;
    }

    public static List<AuthorityDTO> supplierAuthoritiesToListDTOS(List<SupplierAuthority> authorities) {
        List<AuthorityDTO> dtos = new ArrayList<>();
        for(SupplierAuthority a : authorities){
            dtos.add(mapSupplierAuthorityToDTO(a));
        }
        return dtos;
    }

    public static List<SupplierAuthority> supplierAuthoritiesDTOSToList(List<AuthorityDTO> dtos) {
        List<SupplierAuthority> authorities = new ArrayList<>();
        for(AuthorityDTO a : dtos){
            authorities.add(mapAuthorityDTOToSupplierAuthority(a));
        }
        return authorities;
    }
}

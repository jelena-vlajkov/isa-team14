package com.atlaspharmacy.atlaspharmacy.users.mapper;


import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;

import java.util.ArrayList;
import java.util.List;

public class AuthorityMapper {
    private AuthorityMapper(){}

    public static AuthorityDTO mapAuthorityToDTO(Authority authority) {
        return new AuthorityDTO(authority.getId(), authority.getName());
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


}

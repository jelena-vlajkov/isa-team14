package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.repository.AuthorityRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IAuthorityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AuthorityService implements IAuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> getAllRolesAuthorities(String role) {
        List<Authority> allAuths = authorityRepository.findAll();

        List<Authority> rolesAuthorities = new ArrayList<>();


        for(Authority a : allAuths){
            if(a.getName().equals(role)){
                rolesAuthorities.add(a);
            }
        }

        return rolesAuthorities;
    }
}

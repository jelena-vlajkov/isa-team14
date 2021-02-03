package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.SupplierAuthority;
import com.atlaspharmacy.atlaspharmacy.users.repository.AuthorityRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.SupplierAuthorityRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.ISupplierAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SupplierAuthorityService implements ISupplierAuthorityService {
    private final SupplierAuthorityRepository supplierAuthorityRepository;

    @Autowired
    public SupplierAuthorityService(SupplierAuthorityRepository supplierAuthorityRepository) {
        this.supplierAuthorityRepository = supplierAuthorityRepository;
    }

    @Override
    public List<SupplierAuthority> getAllRolesAuthorities(String role) {
        List<SupplierAuthority> allAuths = supplierAuthorityRepository.findAll();

        List<SupplierAuthority> rolesAuthorities = new ArrayList<>();


        for(SupplierAuthority a : allAuths){
            if(a.getName().equals(role)){
                rolesAuthorities.add(a);
            }
        }

        return rolesAuthorities;
    }
}

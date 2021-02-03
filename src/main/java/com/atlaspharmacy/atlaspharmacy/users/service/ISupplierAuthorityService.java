package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.SupplierAuthority;

import java.util.List;

public interface ISupplierAuthorityService {
    List<SupplierAuthority> getAllRolesAuthorities(String role);

}

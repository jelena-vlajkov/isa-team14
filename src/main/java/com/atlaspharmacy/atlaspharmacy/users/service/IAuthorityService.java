package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> getAllRolesAuthorities(String role);

}

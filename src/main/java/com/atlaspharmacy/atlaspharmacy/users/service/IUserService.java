package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.User;

public interface IUserService {
    User getUserBy(Long id);
    User getPharmacyAdmin(Long pharmacyId);
}

package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository _userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }
}
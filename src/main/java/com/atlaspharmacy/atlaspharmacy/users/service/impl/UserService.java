package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository _userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public User getUserBy(Long id) {
        return _userRepository.getOne(id);
    }
}

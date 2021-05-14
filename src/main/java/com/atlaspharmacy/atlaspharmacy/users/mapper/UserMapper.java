package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;

public class UserMapper {
    public UserMapper() {}

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getDateOfBirth(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), user.getGender(),
                AddressMapper.mapAddressToDTO(user.getAddress()), user.getRole(), AuthorityMapper.authoritiesToListDTOS(user.getAuthorities()), user.isFirstTimePassword());
    }
}

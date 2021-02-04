package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;

import java.util.List;

public class UserMapper {
    private UserMapper() {}

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getDateOfBirth(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), user.getGender(),
                AddressMapper.mapAddressToDTO(user.getAddress()), user.getRole(), AuthorityMapper.authoritiesToListDTOS(user.getAuthorities()));
    }
}

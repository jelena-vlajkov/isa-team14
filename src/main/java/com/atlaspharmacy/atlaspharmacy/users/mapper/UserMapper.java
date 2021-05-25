package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserPreviewDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public UserMapper() {}

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getDateOfBirth(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), user.getGender(),
                AddressMapper.mapAddressToDTO(user.getAddress()), user.getRole(), AuthorityMapper.authoritiesToListDTOS(user.getAuthorities()), user.isFirstTimePassword());
    }

    public static List<UserPreviewDTO> mapUsersToDTOs(List<User> usersForEmployee) {
        List<UserPreviewDTO> retVal = new ArrayList<>();
        for (User u : usersForEmployee) {
            retVal.add(mapOneUser(u));
        }

        return retVal;
    }
    public static UserPreviewDTO mapOneUser(User user) {
        return new UserPreviewDTO(user.getName(), user.getSurname());
    }
}

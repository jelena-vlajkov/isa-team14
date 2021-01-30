package com.atlaspharmacy.atlaspharmacy.generalities.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {

    public AddressMapper() {

    }

    public static AddressDTO mapAddressToDTO(Address address) {

        return new AddressDTO(address.getId(), address.getCoordinates(), address.getCity(),address.getStreet(), address.getState());
    }

    public static Address mapAddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setState(addressDTO.getState());
        address.setCoordinates(addressDTO.getCoordinates());
        address.setCity(addressDTO.getCity());
        return address;
    }

    public static List<AddressDTO> mapAddressListToDTOS(List<Address> addresses) {
        List<AddressDTO> dtos = new ArrayList<>();
        for (Address a : addresses) {
            dtos.add(mapAddressToDTO(a));
        }
        return dtos;
    }

}
package com.atlaspharmacy.atlaspharmacy.generalities.service;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;

public interface IAddressService {
    Address updateAddress(AddressDTO addressDTO);
}

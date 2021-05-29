package com.atlaspharmacy.atlaspharmacy.generalities.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address updateAddress(AddressDTO addressDTO) {
        Address addressToUpdate = addressRepository.findById(addressDTO.getId()).get();
        if(addressToUpdate != null){
            addressToUpdate.setState(addressDTO.getState());
            addressToUpdate.setCity(addressDTO.getCity());
            addressToUpdate.setStreet(addressDTO.getStreet());
            addressToUpdate.setCoordinates(addressDTO.getCoordinates());
            addressRepository.save(addressToUpdate);
        }
        return addressToUpdate;
    }
}

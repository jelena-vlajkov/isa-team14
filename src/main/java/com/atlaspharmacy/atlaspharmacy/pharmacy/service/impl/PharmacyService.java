package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions.InvalidPharmacyData;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.IPharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository pharmacyRepository;
    private final AddressRepository addressRepository;
    final private static String EXCEPTION = "Exception in Pharmacy Service Implementation method:";
    final private static String DOES_NOT_EXIST = "Pharmacy with Id does not exist";

    @Autowired
    public PharmacyService(IPharmacyRepository pharmacyRepository, AddressRepository addressRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Pharmacy getById(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getById(id).orElse(null);
        if(pharmacy == null){
            throw  new NoSuchElementException(EXCEPTION + " findById" + DOES_NOT_EXIST);
        }

        return  pharmacy;
    }

    @Override
    public Pharmacy registerPharmacy(PharmacyDTO pharmacyDTO) throws InvalidPharmacyData, ParseException {

        Address a = AddressMapper.mapAddressDTOToAddress(pharmacyDTO.getAddress());

        Pharmacy p = PharmacyMapper.mapDTOToPharmacy(pharmacyDTO);
        p.setAddress(a);

        addressRepository.save(a);
        pharmacyRepository.save(p);
        return p;
    }

    @Override
    public List<PharmacyDTO> getAllPharmacies(){
        List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
        List<PharmacyDTO> dtos = new ArrayList<>();
        if(pharmacies.size()!=0){
            for(Pharmacy p : pharmacies){
                dtos.add(PharmacyMapper.mapPharmacyToDTO(p));
            }
        }

        return dtos;
    }


}

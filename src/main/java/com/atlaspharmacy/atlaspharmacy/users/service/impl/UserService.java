package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final AddressRepository addressRepository;
    @Autowired
    public UserService(UserRepository userRepository, AuthorityService authorityService, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.addressRepository = addressRepository;
    }

    @Override
    public User getUserBy(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Patient registerPatient(PatientDTO patientDTO) throws InvalidPatientData {
        String role = "ROLE_PATIENT";
        Address a = AddressMapper.mapAddressDTOToAddress(patientDTO.getAddress());
        addressRepository.save(a);
        List<Authority> auths = authorityService.getAllRolesAuthorities(role);
        Patient patient = PatientMapper.mapDTOToPatient(patientDTO);

        patient.setAuthorities(auths);
        patient.setAddress(a);
        userRepository.save(patient);
        return patient;
    }

    public User getPharmacyAdmin(Long pharmacyId) {
        return null;
    }
}


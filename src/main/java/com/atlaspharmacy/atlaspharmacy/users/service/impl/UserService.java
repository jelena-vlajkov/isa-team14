package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.State;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.CoordinatesMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.CityRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.CoordinatesRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.StateRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AuthorityService authorityService;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final CoordinatesRepository coordinatesRepository;
    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, AuthorityService authorityService, StateRepository stateRepository, CityRepository cityRepository, CoordinatesRepository coordinatesRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.authorityService = authorityService;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public User getUserBy(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Patient registerPatient(PatientDTO patientDTO) throws InvalidPatientData {
        String role = "ROLE_PATIENT";
        Address a = AddressMapper.mapAddressDTOToAddress(patientDTO.getAddress());
        City c = AddressMapper.mapDTOtoCity(patientDTO.getAddress().getCity());

        State s = AddressMapper.mapDTOtoState(patientDTO.getAddress().getCity().getState());
        c.setState(s);
        a.setCity(c);
        Coordinates coords = AddressMapper.mapCoordinatesDTOToCoordinates(patientDTO.getAddress().getCoordinates());
        a.setCoordinates(coords);
        coordinatesRepository.save(coords);
        stateRepository.save(s);
        cityRepository.save(c);
        addressRepository.save(a);
        List<Authority> auths = authorityService.getAllRolesAuthorities(role);
        Patient patient = PatientMapper.mapDTOToPatient(patientDTO);
        patient.setAuthorities(auths);
        patient.setAddress(a);
        userRepository.save(patient);
        return patient;
    }
}

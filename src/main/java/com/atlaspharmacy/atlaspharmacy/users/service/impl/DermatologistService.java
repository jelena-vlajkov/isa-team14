package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository dermatologistRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final AppointmentService appointmentService;
    private final IPharmacyService pharmacyService;

    @Autowired
    public DermatologistService(DermatologistRepository _dermatologistRepository, UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder passwordEncoder, AuthorityService authorityService, AppointmentService appointmentService, PharmacyService pharmacyService) {
        this.dermatologistRepository = _dermatologistRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.appointmentService = appointmentService;
        this.pharmacyService = pharmacyService;
    }

    @Override
    public List<Dermatologist> findAllByPharmacy(Long id) {
        List<Dermatologist> allDermatologists= dermatologistRepository.findAll();
        List<Dermatologist> dermatologistsByPharmacy= new ArrayList<>();
        for (Dermatologist dermatologist: allDermatologists) {
            if (dermatologist.getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getId().equals(id)))
            {
                dermatologistsByPharmacy.add(dermatologist);
            }
        }
        return dermatologistsByPharmacy;
    }

    @Override
    public Dermatologist registerDermatologist(DermatologistDTO dto) throws InvalidEmail {
        if(userRepository.findByEmail(dto.getEmail())==null && !pharmacyService.isPharamcyRegistered(dto.getEmail())){
            String role ="ROLE_DERMATOLOGIST";
            String password = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(dto.getAddress());
            addressRepository.save(a);

            Dermatologist dermatologist = DermatologistMapper.mapDTOToDermatologist(dto);
            dermatologist.setAuthorities(authorityService.getAllRolesAuthorities(role));
            dermatologist.setAddress(a);
            dermatologist.setPharmacies(PharmacyMapper.maptDTOSToList(dto.getPharmacies()));
            userRepository.save(dermatologist);
            return dermatologist;
        }
        throw new InvalidEmail();
    }
    public List<Dermatologist> distinctDermatologistsToComplain(List<Dermatologist> list){
        List<Dermatologist> derms = new ArrayList<>();
        Map<Long, Dermatologist> map = new HashMap<>();
        for (Dermatologist d : list) {
            Long key = d.getId();
            if (!map.containsKey(key)) {
                map.put(key, d);
            }
        }
        Collection<Dermatologist> distinct = map.values();
        for(Dermatologist d : distinct){
            derms.add(d);
        }
        return derms;
    }
    @Override
    public List<Dermatologist> getAllDermatologistsToComplain(Long id){
        List<Examination> examinations = appointmentService.getFinishedPatientsExaminations(id);
        List<Dermatologist> dermatologistsToComplain = new ArrayList<>();
        for(Examination e : examinations){
            dermatologistsToComplain.add(dermatologistRepository.findById(e.getDermatologist().getId()).get());
        }
        return distinctDermatologistsToComplain(dermatologistsToComplain);
    }


}

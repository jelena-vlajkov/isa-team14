package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository dermatologistRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;


    @Autowired
    public DermatologistService(DermatologistRepository _dermatologistRepository, UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.dermatologistRepository = _dermatologistRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    @Override
    public List<Dermatologist> findAllByPharmacy(Long id) {
        List<Dermatologist> dermatologists= dermatologistRepository.findAll();

        List<Dermatologist> dermatologistsByPharmacy= new ArrayList<>();
        for (Dermatologist dermatologist: dermatologists) {
            if (dermatologist.getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getId()==id))
            {
                dermatologistsByPharmacy.add(dermatologist);
            }
        }
        return dermatologistsByPharmacy;
    }

    @Override
    public Dermatologist registerDermatologist(DermatologistDTO dto) throws InvalidEmail {
        if(userRepository.findByEmail(dto.getEmail())==null){
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
}

package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacyAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacyAdminMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.IPharmacyAdminRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {
    private final AuthorityService authorityService;
    private final IPharmacyAdminRepository _pharmacyAdminRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyAdminService(AuthorityService authorityService, IPharmacyAdminRepository _iPharmacyAdminRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AddressRepository addressRepository, PharmacyRepository pharmacyRepository) {
        this.authorityService = authorityService;
        this._pharmacyAdminRepository = _iPharmacyAdminRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Pharmacy getPharmacyByPharmacyAdmin(Long id) {
        PharmacyAdmin pharmacyAdmin= _pharmacyAdminRepository.findAll()
                                       .stream()
                                       .filter(admin -> admin.getId()==id).findFirst()
                                       .orElse(null);
        return pharmacyAdmin.getPharmacy();
    }

    @Override
    public PharmacyAdmin getById(Long id) {
        return _pharmacyAdminRepository.findById(id).orElse(null);
    }

    @Override
    public PharmacyAdmin registerPharmacyAdmin(PharmacyAdminDTO pharmacyAdminDTO) throws InvalidEmail, ParseException {
        if(userRepository.findByEmail(pharmacyAdminDTO.getEmail())==null){
            String role ="ROLE_PHARMACYADMIN";
            String password = passwordEncoder.encode(pharmacyAdminDTO.getPassword());
            pharmacyAdminDTO.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(pharmacyAdminDTO.getAddress());
            addressRepository.save(a);
            PharmacyAdmin pharmacyAdmin = PharmacyAdminMapper.mapDTOToPharmacyAdmin(pharmacyAdminDTO);

            pharmacyAdmin.setAuthorities(authorityService.getAllRolesAuthorities(role));

            pharmacyAdmin.setAddress(a);

            userRepository.save(pharmacyAdmin);
            return pharmacyAdmin;
        }
        throw new InvalidEmail();
    }
}

package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.service.IAddressService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacyAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacyAdminMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.IPharmacyAdminRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IAuthorityService;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {
    private final IAuthorityService authorityService;
    private final IPharmacyService pharmacyService;
    private final IAddressService addressService;
    private final IPharmacyAdminRepository pharmacyAdminRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final PharmacyRepository pharmacyRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public PharmacyAdminService(AuthorityService authorityService, IPharmacyAdminRepository _iPharmacyAdminRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AddressRepository addressRepository, PharmacyRepository pharmacyRepository, PharmacyService pharmacyService, IAddressService addressService, AuthenticationManager authenticationManager) {
        this.authorityService = authorityService;
        this.pharmacyAdminRepository = _iPharmacyAdminRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacyService = pharmacyService;
        this.addressService = addressService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public PharmacyDTO getPharmacyByPharmacyAdmin(Long id) {
        PharmacyAdmin pharmacyAdmin= pharmacyAdminRepository.findById(id).orElse(null);

        return PharmacyMapper.mapPharmacyToDTO(pharmacyAdmin.getPharmacy());
    }

    @Override
    public PharmacyAdmin getById(Long id) {
        return pharmacyAdminRepository.findById(id).get();
    }

    @Override
    public PharmacyAdmin registerPharmacyAdmin(PharmacyAdminDTO pharmacyAdminDTO) throws Exception {
        if(userRepository.findUserByEmail(pharmacyAdminDTO.getEmail())==null && !pharmacyService.isPharamcyRegistered(pharmacyAdminDTO.getEmail())){
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

    @Override
    @PharmacyAdminAuthorization
    public PharmacyAdmin updatePharmacyAdmin(PharmacyAdminDTO pharmacyAdminDTO) {
        PharmacyAdmin pharmacyAdminToUpdate= pharmacyAdminRepository.findById(pharmacyAdminDTO.getId()).get();
        pharmacyAdminToUpdate.setDateOfBirth(pharmacyAdminDTO.getDateOfBirth());
        pharmacyAdminToUpdate.setEmail(pharmacyAdminDTO.getEmail());
        pharmacyAdminToUpdate.setGender(pharmacyAdminDTO.getGender());
        pharmacyAdminToUpdate.setName(pharmacyAdminDTO.getName());
        pharmacyAdminToUpdate.setSurname(pharmacyAdminDTO.getSurname());
        pharmacyAdminToUpdate.setPhoneNumber(pharmacyAdminDTO.getPhoneNumber());
        Address address =pharmacyAdminToUpdate.getAddress();
        address.setCity(pharmacyAdminDTO.getAddress().getCity());
        address.setCoordinates(pharmacyAdminDTO.getAddress().getCoordinates());
        address.setState(pharmacyAdminDTO.getAddress().getState());
        address.setStreet(pharmacyAdminDTO.getAddress().getStreet());
        addressRepository.save(address);
        pharmacyAdminRepository.save(pharmacyAdminToUpdate);
        return pharmacyAdminToUpdate;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) throws InvalidPassword, InvalidEmail, ParseException {
        // Ocitavamo trenutno ulogovanog korisnika
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (authenticationManager != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
            PharmacyAdmin pharmacyAdmin =findByEmail(email);

            pharmacyAdmin.setPassword(passwordEncoder.encode(newPassword));
            pharmacyAdmin.setFirstTimePassword(true);
            pharmacyAdminRepository.save(pharmacyAdmin);
            return true;

        }
        return false;
     }

    @Override
    public PharmacyAdmin findByEmail(String email){
        List<PharmacyAdmin> pharmacyAdmins = pharmacyAdminRepository.findAll();
        for(PharmacyAdmin p :pharmacyAdmins ){
            if(p.getEmail().equals(email)){
                return  p;
            }
        }
        return null;
    }
}

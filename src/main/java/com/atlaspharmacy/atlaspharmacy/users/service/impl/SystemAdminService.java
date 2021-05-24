package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PasswordChangerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SystemAdminMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.SystemAdminRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.ISystemAdminService;
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
public class SystemAdminService implements ISystemAdminService {
    private final AuthorityService authorityService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final SystemAdminRepository systemAdminRepository;
    private final AuthenticationManager authenticationManager;
    private final IPharmacyService pharmacyService;
    @Autowired
    public SystemAdminService(AuthorityService authorityService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AddressRepository addressRepository, SystemAdminRepository systemAdminRepository, AuthenticationManager authenticationManager, PharmacyService pharmacyService){
        this.authorityService = authorityService;
        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.systemAdminRepository = systemAdminRepository;
        this.authenticationManager = authenticationManager;
        this.pharmacyService = pharmacyService;
    }
    @Override
    public SystemAdmin registerSysAdmin(SystemAdminDTO systemAdminDTO) throws Exception {
        if(userRepository.findByEmail(systemAdminDTO.getSysEmail())==null && !pharmacyService.isPharamcyRegistered(systemAdminDTO.getSysEmail())){
            String role ="ROLE_SYSADMIN";
            String password = passwordEncoder.encode(systemAdminDTO.getSysPassword());
            systemAdminDTO.setSysPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(systemAdminDTO.getSysAddress());
            addressRepository.save(a);
            SystemAdmin sysAdmin = SystemAdminMapper.mapDTOToSystemAdmin(systemAdminDTO);
            sysAdmin.setAuthorities(authorityService.getAllRolesAuthorities(role));
            sysAdmin.setFirstTimePassword(false);
            sysAdmin.setAddress(a);
            userRepository.save(sysAdmin);
            return sysAdmin;
        }
        throw new InvalidEmail();
    }

    @Override
    public SystemAdminDTO getById(Long id) throws Exception {
        if(systemAdminRepository.findById(id).isPresent()){
            SystemAdminDTO dto = SystemAdminMapper.mapSystemAdminToDTO(systemAdminRepository.findById(id).get());
            return dto;
        }
        return null;
    }
    @Override
    public SystemAdmin findByEmail(String email){
        List<SystemAdmin> admins = systemAdminRepository.findAll();
        for(SystemAdmin a : admins){
            if(a.getEmail().equals(email)){
                return  a;
            }
        }
        return null;
    }



    @Override
    public SystemAdmin updateSystemAdmin(SystemAdminDTO systemAdminDTO) throws InvalidEmail, ParseException {
        SystemAdmin s = findByEmail(systemAdminDTO.getSysEmail());
        if(s==null){
            throw new InvalidEmail();
        }
        Address a = addressRepository.findById(s.getAddress().getId()).get();
        a.setStreet(systemAdminDTO.getSysAddress().getStreet());
        a.setCity(systemAdminDTO.getSysAddress().getCity());
        a.setState(systemAdminDTO.getSysAddress().getState());
        a.setCoordinates(systemAdminDTO.getSysAddress().getCoordinates());
        //addressRepository.save(a);
        SystemAdmin newAdmin = SystemAdminMapper.mapDTOToSystemAdmin(systemAdminDTO);
        newAdmin.setId(s.getId());
        newAdmin.setAddress(a);
        return systemAdminRepository.save(newAdmin);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {

        // Ocitavamo trenutno ulogovanog korisnika
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (authenticationManager != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
            SystemAdmin systemAdmin = findByEmail(email);

            // pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
            // ne zelimo da u bazi cuvamo lozinke u plain text formatu
            systemAdmin.setFirstTimePassword(true);
            systemAdmin.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(systemAdmin);
            return true;
        } else {
            return false;
        }



    }
    @Override
    public SystemAdmin updateSystemAdminPassword(PasswordChangerDTO passwordChangerDTO) throws InvalidPassword {
        SystemAdmin s = systemAdminRepository.findById(passwordChangerDTO.getUser_id()).get();
        String oldpass = passwordEncoder.encode(passwordChangerDTO.getOldpassword());
        String oldsyspass = s.getPassword();
        if(oldsyspass.equals(oldpass)){
            s.setPassword(passwordEncoder.encode(passwordChangerDTO.newpassword));
            systemAdminRepository.save(s);
            return s;
        }
        throw new InvalidPassword();
    }
}

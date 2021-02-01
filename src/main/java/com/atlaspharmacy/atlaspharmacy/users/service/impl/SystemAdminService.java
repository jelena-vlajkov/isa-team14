package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SystemAdminMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.ISystemAdminService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SystemAdminService implements ISystemAdminService {
    private final AuthorityService authorityService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Autowired
    public SystemAdminService(AuthorityService authorityService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AddressRepository addressRepository){
        this.authorityService = authorityService;
        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
    }
    @Override
    public SystemAdmin registerSysAdmin(SystemAdminDTO systemAdminDTO) throws InvalidEmail {
        if(userRepository.findByEmail(systemAdminDTO.getSysEmail())==null){
            String role ="ROLE_SYSADMIN";
            String password = passwordEncoder.encode(systemAdminDTO.getSysPassword());
            systemAdminDTO.setSysPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(systemAdminDTO.getSysAddress());
            addressRepository.save(a);
            SystemAdmin sysAdmin = SystemAdminMapper.mapDTOToSystemAdmin(systemAdminDTO);
            sysAdmin.setAuthorities(authorityService.getAllRolesAuthorities(role));
            sysAdmin.setVerificationCode(RandomString.make(64));
            sysAdmin.setEnabled(true);
            sysAdmin.setAddress(a);
            sysAdmin.setFirstPasswordChanged(false);
            userRepository.save(sysAdmin);
            return sysAdmin;
        }
        throw new InvalidEmail();
    }

}

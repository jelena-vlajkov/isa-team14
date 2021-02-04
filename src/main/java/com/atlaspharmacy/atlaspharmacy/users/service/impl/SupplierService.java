package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.SupplierRepository;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {
    private final AuthorityService _authAuthorityService;
    private final SupplierRepository _supplierRepository;
    private final AddressRepository _addressRepository;
    private final UserRepository _userUserRepository;
    private final BCryptPasswordEncoder _passwordEncoder;

    @Autowired
    public SupplierService(AuthorityService authAuthorityService, SupplierRepository supplierRepository, AddressRepository addressRepository, UserRepository userUserRepository, BCryptPasswordEncoder passwordEncoder) {
        _authAuthorityService = authAuthorityService;
        _supplierRepository = supplierRepository;
        _addressRepository = addressRepository;
        _userUserRepository = userUserRepository;
        _passwordEncoder = passwordEncoder;
    }


    @Override
    public Supplier registerSupplier(SupplierDTO supplierDTO) throws InvalidEmail{
        if(_userUserRepository.findByEmail(supplierDTO.getEmail())==null){
            String role ="ROLE_SUPPLIER";
            String password = _passwordEncoder.encode(supplierDTO.getPassword());
            supplierDTO.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(supplierDTO.getAddress());
            _addressRepository.save(a);
            Supplier supplier = SupplierMapper.mapDTOToSupplier(supplierDTO);

            supplier.setAuthorities(_authAuthorityService.getAllRolesAuthorities(role));

            supplier.setAddress(a);

            _supplierRepository.save(supplier);

            return supplier;
        }
        throw new InvalidEmail();
    }
}

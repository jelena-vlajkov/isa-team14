package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.SupplierMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.SupplierRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.ISupplierService;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.AuthorityService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.SupplierAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository _supplierRepository;
    private final AddressRepository _addressRepository;
    private final UserRepository _userUserRepository;
    private final BCryptPasswordEncoder _passwordEncoder;
    private final SupplierAuthorityService _supplierAuthorityService;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, AddressRepository addressRepository, UserRepository userUserRepository, BCryptPasswordEncoder passwordEncoder, SupplierAuthorityService authorityService) {
        _supplierRepository = supplierRepository;
        _addressRepository = addressRepository;
        _userUserRepository = userUserRepository;
        _passwordEncoder = passwordEncoder;
        _supplierAuthorityService = authorityService;
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return _supplierRepository.findById(id).orElse(null);
    }

    @Override
    public Supplier registerSupplier(SupplierDTO supplierDTO) throws InvalidEmail{
        if(_userUserRepository.findByEmail(supplierDTO.getEmail())==null){
            String role ="ROLE_SUPPLIER";
            String password = _passwordEncoder.encode(supplierDTO.getPassword());
            supplierDTO.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(supplierDTO.getHeadquarters());
            _addressRepository.save(a);
            Supplier supplier = SupplierMapper.mapSupplierDTOToSupplier(supplierDTO);

            supplier.setAuthorities(_supplierAuthorityService.getAllRolesAuthorities(role));

            supplier.setAddress(a);

            _supplierRepository.save(supplier);

            return supplier;
        }
        throw new InvalidEmail();
    }

}

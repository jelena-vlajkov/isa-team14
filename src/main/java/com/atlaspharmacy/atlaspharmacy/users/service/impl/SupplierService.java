package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.SupplierRepository;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.ISupplierService;
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
public class SupplierService implements ISupplierService {
    private final AuthorityService _authAuthorityService;
    private final SupplierRepository _supplierRepository;
    private final AddressRepository _addressRepository;
    private final UserRepository _userUserRepository;
    private final BCryptPasswordEncoder _passwordEncoder;
    //private final AuthenticationManager _authenticationManager;
    private final IPharmacyService _pharmacyService;
    @Autowired
    public SupplierService(AuthorityService authAuthorityService, SupplierRepository supplierRepository, AddressRepository addressRepository, UserRepository userUserRepository, BCryptPasswordEncoder passwordEncoder, PharmacyService pharmacyService ) {
        _authAuthorityService = authAuthorityService;
        _supplierRepository = supplierRepository;
        _addressRepository = addressRepository;
        _userUserRepository = userUserRepository;
        _passwordEncoder = passwordEncoder;
        //_authenticationManager = authenticationManager;
        _pharmacyService = pharmacyService;
    }


    @Override
    public Supplier registerSupplier(SupplierDTO supplierDTO) throws Exception {
        if(_userUserRepository.findByEmail(supplierDTO.getEmail())==null && !_pharmacyService.isPharamcyRegistered(supplierDTO.getEmail())){
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

    @Override
    public SupplierDTO getById(Long id) {
        if(_supplierRepository.findById(id).isPresent()){
            SupplierDTO dto = SupplierMapper.mapSupplierToDTO(_supplierRepository.findById(id).get());
            return dto;
        }
        return null;
    }

    @Override
    public Supplier findByEmail(String email){
        List<Supplier> suppliers = _supplierRepository.findAll();
        for(Supplier s : suppliers){
            if(s.getEmail().equals(email)){
                return  s;
            }
        }
        return null;
    }

    @Override
    public Supplier updateSupplier(SupplierDTO supplierDTO) throws InvalidEmail, ParseException {
        Supplier s = findByEmail(supplierDTO.getEmail());
        if(s == null){
            throw new InvalidEmail();
        }
        Address a = _addressRepository.findById(s.getAddress().getId()).get();
        a.setStreet(supplierDTO.getAddress().getStreet());
        a.setCity(supplierDTO.getAddress().getCity());
        a.setState(supplierDTO.getAddress().getState());
        a.setCoordinates(supplierDTO.getAddress().getCoordinates());
        Supplier newSupplier = SupplierMapper.mapDTOToSupplier(supplierDTO);
        newSupplier.setAddress(a);
        newSupplier.setId(s.getId());
        return _supplierRepository.save(newSupplier);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) throws InvalidPassword, InvalidEmail, ParseException {
        // Ocitavamo trenutno ulogovanog korisnika
       // Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        //String email = currentUser.getName();
/*
        if (_authenticationManager != null) {
            _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
            Supplier supplier = findByEmail(email);

            supplier.setPassword(_passwordEncoder.encode(newPassword));
            supplier.setFirstTimePassword(true);
            _supplierRepository.save(supplier);
            return true;

        }*/
        return false;



    }
}

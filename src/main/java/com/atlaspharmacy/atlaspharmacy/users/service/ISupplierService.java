package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PasswordChangerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

public interface ISupplierService {
    Supplier registerSupplier(SupplierDTO supplierDTO) throws Exception;
    SupplierDTO getById(Long id);
    Supplier findByEmail(String email);
    Supplier updateSupplier(SupplierDTO supplierDTO) throws InvalidEmail, ParseException;
    boolean changePassword(String oldPassword, String newPassword) throws InvalidPassword,InvalidEmail, ParseException;
}

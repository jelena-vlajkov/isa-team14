package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PasswordChangerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;

import java.text.ParseException;

public interface ISystemAdminService {
    SystemAdmin registerSysAdmin(SystemAdminDTO systemAdminDTO) throws Exception;
    SystemAdminDTO getById(Long id) throws Exception;
    SystemAdmin updateSystemAdmin(SystemAdminDTO systemAdminDTO) throws InvalidEmail, ParseException;
    SystemAdmin findByEmail(String email);
    SystemAdmin updateSystemAdminPassword(PasswordChangerDTO passwordChangerDTO) throws InvalidPassword;
    boolean changePassword(String oldPassword, String newPassword);
}

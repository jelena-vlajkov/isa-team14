package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;

public interface ISystemAdminService {
    SystemAdmin registerSysAdmin(SystemAdminDTO systemAdminDTO) throws Exception;
}

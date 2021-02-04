package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;

public class SystemAdminMapper {
    public SystemAdminMapper() {
    }
    public static SystemAdminDTO mapSystemAdminToDTO(SystemAdmin a){
        return new SystemAdminDTO(a.getId(), a.getName(), a.getSurname(), a.getDateOfBirth(),
                a.getPhoneNumber(), a.getEmail(), a.getPassword(), a.getGender(),
                AddressMapper.mapAddressToDTO(a.getAddress()),a.getRole(),
                AuthorityMapper.authoritiesToListDTOS(a.getAuthorities()), a.isFirstTimePassword());

    }
    public static SystemAdmin mapDTOToSystemAdmin(SystemAdminDTO dto){
        SystemAdmin a = new SystemAdmin();
        a.setId(dto.getId());
        a.setName(dto.getSysName());
        a.setSurname(dto.getSysSurname());
        a.setDateOfBirth(dto.getSysDateOfBirth());
        a.setPhoneNumber(dto.getSysPhoneNumber());
        a.setEmail(dto.getSysEmail());
        a.setPassword(dto.getSysPassword());
        a.setGender(dto.getSysGender());
        a.setFirstTimePassword(dto.isFirstTimePassword());
        a.setAddress(AddressMapper.mapAddressDTOToAddress(dto.getSysAddress()));
        a.setAuthorities(AuthorityMapper.authoritiesDTOSToList(dto.getSysAuthorities()));

        return a;
    }
}

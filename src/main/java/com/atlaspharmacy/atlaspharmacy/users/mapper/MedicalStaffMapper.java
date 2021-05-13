package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.MedicalStaffDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;

import java.util.Date;

public class MedicalStaffMapper {
    private MedicalStaffMapper() {}

    public static MedicalStaffDTO MapMedicalStaffToDTO(MedicalStaff medicalStaff){
        return new MedicalStaffDTO(medicalStaff.getId(),medicalStaff.getName(),medicalStaff.getSurname()
                                    ,medicalStaff.getDateOfBirth(),medicalStaff.getPhoneNumber(),medicalStaff.getEmail()
                                    ,medicalStaff.getPassword(),medicalStaff.getGender(), AddressMapper.mapAddressToDTO(medicalStaff.getAddress()), medicalStaff.getRole()
                                    ,AuthorityMapper.authoritiesToListDTOS(medicalStaff.getAuthorities()), medicalStaff.isFirstTimePassword(),medicalStaff.getLicenseNumber());
    }

    public static MedicalStaff MapDTOToMedicalStaff(MedicalStaffDTO medicalStaffDTO){
        return new MedicalStaff (medicalStaffDTO.getName(),medicalStaffDTO.getSurname(),medicalStaffDTO.getDateOfBirth(),
                medicalStaffDTO.getPhoneNumber(),medicalStaffDTO.getEmail(),medicalStaffDTO.getPassword(),
                medicalStaffDTO.getGender(),AddressMapper.mapAddressDTOToAddress(medicalStaffDTO.getAddress()),medicalStaffDTO.getRole(),
                AuthorityMapper.authoritiesDTOSToList(medicalStaffDTO.getAuthorities()),medicalStaffDTO.isFirstTimeChanged(), medicalStaffDTO.getLicenceNumber());
    }
}

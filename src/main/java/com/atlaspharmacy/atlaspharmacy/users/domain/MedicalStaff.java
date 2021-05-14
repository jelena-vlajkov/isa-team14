package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.MedicalStaffDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.mapper.AuthorityMapper;
import org.hibernate.annotations.Proxy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medicalstaff")
@DiscriminatorValue(value = Role.Values.MedicalStaff)
@Proxy(lazy = false)
public class MedicalStaff extends User {
    private String licenseNumber;

    public  MedicalStaff() {}
    public MedicalStaff(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public MedicalStaff(String name, String surname, Date dateOfBirth,
                           String phoneNumber, String email, String password,
                           Gender gender, Address address, String role,
                           List<Authority> authorities, boolean firstTimeChanged,String licenseNumber){
            super(name,surname,address, dateOfBirth, phoneNumber, gender, role, authorities);
            this.licenseNumber=licenseNumber;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}

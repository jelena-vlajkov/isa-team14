package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import org.hibernate.annotations.Proxy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
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
}

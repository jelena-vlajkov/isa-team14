package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medicalstaff")
@DiscriminatorValue(value = Role.Values.MedicalStaff)
public class MedicalStaff extends User {
    private String licenseNumber;

    public  MedicalStaff() {}
    public MedicalStaff(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}

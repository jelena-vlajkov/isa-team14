package com.atlaspharmacy.atlaspharmacy.generalities.model;

import com.atlaspharmacy.atlaspharmacy.users.model.Users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medicalstaff")
public class MedicalStaff extends Users {
    private String licenseNumber;

    public  MedicalStaff() {}
    public MedicalStaff(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}

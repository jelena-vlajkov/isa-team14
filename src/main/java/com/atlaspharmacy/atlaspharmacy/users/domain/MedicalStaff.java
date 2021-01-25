package com.atlaspharmacy.atlaspharmacy.users.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medicalstaff")
public class MedicalStaff extends User {
    private String licenseNumber;

    public  MedicalStaff() {}
    public MedicalStaff(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}

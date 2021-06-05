package com.atlaspharmacy.atlaspharmacy.grade.domain;

import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import javax.persistence.*;

@Entity
@Table(name = "pharmacist_grade")
@DiscriminatorValue(GradeType.Values.PharmacistGrade)
public class PharmacistGrade extends Grade{

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;

    public PharmacistGrade(int grade, Patient patient, Pharmacist pharmacist) {
        super(grade, patient);
        this.pharmacist = pharmacist;
    }

    public PharmacistGrade(){}

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}

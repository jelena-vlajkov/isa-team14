package com.atlaspharmacy.atlaspharmacy.grade.domain;

import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.persistence.*;

@Entity
@Table(name = "pharmacy_grade")
@DiscriminatorValue(GradeType.Values.PharmacyGrade)
public class PharmacyGrade extends Grade{

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    public PharmacyGrade(int grade, Patient patient, Pharmacy pharmacy) {
        super(grade, patient);
        this.pharmacy = pharmacy;
    }

    public PharmacyGrade() {
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}


package com.atlaspharmacy.atlaspharmacy.grade.domain;

import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;

import javax.persistence.*;

@Entity
@Table(name = "dermatologist_grade")
@DiscriminatorValue(GradeType.Values.DermatologistGrade)
public class DermatologistGrade extends Grade{

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;

    public DermatologistGrade() {}

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}

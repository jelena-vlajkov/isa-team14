package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.*;
@Entity
@Table(name = "dermatologists")
@DiscriminatorValue(value = Role.Values.Dermatologist)
public class Dermatologist extends MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Dermatologist(Long id) {
        this.id = id;
    }

    public Dermatologist() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

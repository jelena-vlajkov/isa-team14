package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "patients")
@DiscriminatorValue(value = Role.Values.Patient)
public class Patient extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Patient(Long id) {
        this.id = id;
    }

    public Patient() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

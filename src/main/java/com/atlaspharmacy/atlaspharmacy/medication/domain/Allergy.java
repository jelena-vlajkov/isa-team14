package com.atlaspharmacy.atlaspharmacy.medication.domain;

import javax.persistence.*;

@Entity
@Table(name = "allergies")
public class Allergy {
    @Id
    private int id;
    private String name;

    public Allergy() {
    }

    public Allergy(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

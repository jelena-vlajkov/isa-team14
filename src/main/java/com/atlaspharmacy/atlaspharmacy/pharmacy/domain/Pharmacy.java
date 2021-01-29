package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pharamcies")
public class Pharmacy {
    @Id
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Pharmacy(){}
    public Pharmacy(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}

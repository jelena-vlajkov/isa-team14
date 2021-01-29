package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "pharamcies")
public class Pharmacy {
    @Id
    private Long id;
    private String name;
    private String description;

    public Pharmacy(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Pharmacy(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}

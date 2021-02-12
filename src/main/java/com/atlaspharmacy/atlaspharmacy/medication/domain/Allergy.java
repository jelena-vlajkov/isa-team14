package com.atlaspharmacy.atlaspharmacy.medication.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Proxy;

@Proxy(lazy = false)
@Entity
@Table(name = "allergies")
public class Allergy {
    @Id
    private Long id;
    private String name;

    public Allergy() {
    }

    public Allergy(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

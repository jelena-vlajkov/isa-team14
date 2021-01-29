package com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class City {
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    private int id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private State state;

    public City() {}
    public City(String name, State state) {
        this.name = name;
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

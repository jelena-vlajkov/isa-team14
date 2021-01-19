package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State {
    @Id
    private int id;
    private String name;

    public State() {}
    public State(String name) {
        this.name = name;
    }
}

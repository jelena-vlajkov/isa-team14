package com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects;

import javax.persistence.Embeddable;

@Embeddable

public class State {
    private String name;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

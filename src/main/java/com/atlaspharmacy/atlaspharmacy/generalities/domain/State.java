package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
@Proxy(lazy = false)
public class State {
    @Id
    private Long id;
    private String name;

    public State() {}
    public State(String name) {
        this.name = name;
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
}

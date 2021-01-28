package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Proxy(lazy = false)
public class City {
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private State state;

    public City() {}
    public City(String name, State state) {
        this.name = name;
        this.state = state;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

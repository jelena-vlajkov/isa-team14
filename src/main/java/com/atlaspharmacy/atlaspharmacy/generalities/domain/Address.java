package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
@Proxy(lazy = false)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "city")),
    })
    private City city;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "latitude", column = @Column(name = "latitude")),
            @AttributeOverride( name = "longitude", column = @Column(name = "longitude")),
    })
    private Coordinates coordinates;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "state")),
    })
    private State state;
    public Address() {}
    public Address(String street, City city, State state, Coordinates coordinates) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

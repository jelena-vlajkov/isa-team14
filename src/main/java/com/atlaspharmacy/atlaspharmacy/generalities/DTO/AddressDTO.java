package com.atlaspharmacy.atlaspharmacy.generalities.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;

public class AddressDTO {
    private Long id;
    private Coordinates coordinates;
    private State state;
    private City city;
    private String street;

    public AddressDTO() {
    }

    public AddressDTO(Long id, Coordinates coordinates, City city, String street, State state) {
        this.id = id;
        this.coordinates = coordinates;
        this.city = city;
        this.street = street;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

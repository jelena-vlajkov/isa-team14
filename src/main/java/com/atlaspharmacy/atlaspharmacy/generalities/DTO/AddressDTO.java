package com.atlaspharmacy.atlaspharmacy.generalities.DTO;

public class AddressDTO {
    private Long id;
    private CoordinatesDTO coordinates;
    private CityDTO city;
    private String street;

    public AddressDTO() {
    }

    public AddressDTO(Long id, CoordinatesDTO coordinates, CityDTO city, String street) {
        this.id = id;
        this.coordinates = coordinates;
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoordinatesDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDTO coordinates) {
        this.coordinates = coordinates;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

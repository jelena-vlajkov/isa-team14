package com.atlaspharmacy.atlaspharmacy.generalities.DTO;

public class CityDTO {
    private Long id;
    private String name;
    private StateDTO state;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, StateDTO state) {
        this.id = id;
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

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }
}

package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class UserPreviewDTO {
    private String name;
    private String surname;

    public UserPreviewDTO() {
    }

    public UserPreviewDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

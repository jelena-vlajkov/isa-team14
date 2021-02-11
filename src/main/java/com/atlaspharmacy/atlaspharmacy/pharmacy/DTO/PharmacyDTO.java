package com.atlaspharmacy.atlaspharmacy.pharmacy.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;

public class PharmacyDTO {
    private Long id;
    private String name;
    private String description;
    private String email;
    private Long telephone;
    private AddressDTO address;

    private Double average_grade;

    public PharmacyDTO() {
    }

    public PharmacyDTO(Long id, String name, String description, String email, Long telephone, AddressDTO address, Double average_grade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.average_grade = average_grade;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Double getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(Double average_grade) {
        this.average_grade = average_grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }
}

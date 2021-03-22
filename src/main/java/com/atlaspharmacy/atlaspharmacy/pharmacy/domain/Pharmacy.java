package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;


import javax.persistence.*;

@Entity
@Table(name = "pharmacies")
public class Pharmacy  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double average_grade;
    private String email;
    private Long telephone;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    
    public Pharmacy(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
    public Pharmacy(){}
    public Pharmacy(Long id, String name, String description, String email, Long telephone,Address address,Double average_grade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
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

    public Double getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(Double average_grade) {
        this.average_grade = average_grade;
    }
}


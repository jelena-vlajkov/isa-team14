package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;


import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "pharmacies")
public class Pharmacy  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double average_grade;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    
    public Pharmacy(Long id) {
        this.id = id;
    }

    public Pharmacy(Long id, String name, String description, Address address, Double average_grade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.average_grade = average_grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getAverageGrade() { return average_grade; }

    public void setAverageGrade(Double average_grade) {
        this.average_grade = average_grade;
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

    public Pharmacy(Long id, String name, String description, Address address, Double average_grade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.average_grade = average_grade;
    }
}


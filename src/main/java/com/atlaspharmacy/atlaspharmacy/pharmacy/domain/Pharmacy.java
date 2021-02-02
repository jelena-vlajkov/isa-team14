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
    private Long id;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    private Double average_grade;

    
    public Pharmacy(Long id) {
        this.id = id;
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
    public Pharmacy(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}

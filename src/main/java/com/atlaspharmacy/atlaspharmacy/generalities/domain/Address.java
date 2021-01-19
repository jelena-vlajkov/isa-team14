package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    private int id;
    private String street;
    private int number;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private City city;

    public Address() {}
    public Address(String street, int number, City city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }



}

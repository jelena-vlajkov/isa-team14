package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Proxy(lazy = false)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private int number;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private City city;

    public Address() {}
    public Address(String street, int number, City city) {
        this.street = street;
        this.number = number;
        this.city = city;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

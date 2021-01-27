package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharamcy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address  {
    @Id
    @Column(name = "id")
    private int id;
    private String street;
    private int number;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private City city;

    @OneToOne(mappedBy = "address")

    private Pharamcy pharmacy;

    public Address() {}
    public Address(String street, int number, City city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    private int getNumber(){return number;}
    private void setNumber(int number){this.number=number;}

    private String getStreet(){return street;}
    private void setStreet(String street){this.street=street;}

    private int getId(){return id;}
    private void setId(int id){this.id=id;}

    private City getCity(){return city;}
    private void setCity(City city){this.city=city;}

    private Pharamcy getPharmacy(){return pharmacy;}
    private void setPharmacy(Pharamcy pharmacy){this.pharmacy=pharmacy;}





}

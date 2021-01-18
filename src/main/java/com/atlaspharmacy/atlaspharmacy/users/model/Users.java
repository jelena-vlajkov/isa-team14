package com.atlaspharmacy.atlaspharmacy.users.model;

import com.atlaspharmacy.atlaspharmacy.generalities.model.Address;
import com.atlaspharmacy.atlaspharmacy.users.model.enums.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public abstract class Users {
    @Id
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String phoneNumber;
    private Gender gender;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;


    public Users() {}
    public Users(String name, String surname, Date dateOfBirth, String phoneNumber, Gender gender)
    {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}

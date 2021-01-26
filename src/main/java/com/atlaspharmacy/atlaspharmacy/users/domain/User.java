package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.medication.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "role", discriminatorType=DiscriminatorType.STRING)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String phoneNumber;
    private Gender gender;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    @Column(insertable = false, updatable = false)
    private Role role;

    public User() {}
    public User(String name, String surname, Date dateOfBirth, String phoneNumber, Gender gender, Role role)
    {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

}

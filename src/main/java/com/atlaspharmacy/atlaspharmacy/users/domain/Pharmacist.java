package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "pharmacists")
@DiscriminatorValue(value = Role.Values.Pharmacist)
@Proxy(lazy = false)
public class Pharmacist extends MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    public Pharmacist(Long id) {
        this.id = id;
    }

    public Pharmacist() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

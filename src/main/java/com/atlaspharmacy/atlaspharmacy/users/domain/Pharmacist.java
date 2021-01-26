package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "pharmacists")
@DiscriminatorValue(value = Role.Values.Pharmacist)
public class Pharmacist extends User {

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

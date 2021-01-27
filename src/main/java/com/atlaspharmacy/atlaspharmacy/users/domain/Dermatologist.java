package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
@Entity
@Table(name = "dermatologists")
@DiscriminatorValue(value = Role.Values.Dermatologist)
@Proxy(lazy = false)
public class Dermatologist extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Dermatologist(Long id) {
        this.id = id;
    }

    public Dermatologist() {

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

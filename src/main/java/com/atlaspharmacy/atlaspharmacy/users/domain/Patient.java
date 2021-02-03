package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "patients")
@DiscriminatorValue(value = Role.Values.Patient)
@Proxy(lazy = false)
public class Patient extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean enabled;
    private String verificationCode;



    public Patient(Long id, boolean enabled, String verification_code) {
        this.id = id;
        this.enabled = enabled;
        this.verificationCode = verification_code;
    }

    public Patient() {
    }

    public boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verification_code) {
        this.verificationCode = verification_code;
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

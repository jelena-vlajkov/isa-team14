package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import org.hibernate.annotations.Proxy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sysadmins")
@Proxy(lazy = false)
public class SystemAdmin extends User{
    @Id
    private Long id;
    private boolean enabled;
    private boolean firstPasswordChanged;
    private String verificationCode;

    public SystemAdmin(Long id, boolean enabled, String verification_code, boolean firstPasswordChanged) {
        this.id = id;
        this.enabled = enabled;
        this.verificationCode = verification_code;
        this.firstPasswordChanged = firstPasswordChanged;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFirstPasswordChanged() {
        return firstPasswordChanged;
    }

    public void setFirstPasswordChanged(boolean firstPasswordChanged) {
        this.firstPasswordChanged = firstPasswordChanged;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public SystemAdmin() {
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

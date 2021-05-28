package com.atlaspharmacy.atlaspharmacy.users.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "sysadmins")
@DiscriminatorValue(value = Role.Values.SysAdmin)
public class SystemAdmin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public SystemAdmin(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

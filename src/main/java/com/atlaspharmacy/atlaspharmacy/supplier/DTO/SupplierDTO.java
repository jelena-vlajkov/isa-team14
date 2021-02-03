package com.atlaspharmacy.atlaspharmacy.supplier.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

public class SupplierDTO {
    private Long id;
    private String name;
    private AddressDTO headquarters;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private List<AuthorityDTO> authorities;

    public SupplierDTO() {
    }

    public SupplierDTO(Long id, String name, AddressDTO headquarters, String phoneNumber, String email, String password,
                       String role, List<AuthorityDTO> authorities) {
        this.id = id;
        this.name = name;
        this.headquarters = headquarters;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(AddressDTO headquarters) {
        this.headquarters = headquarters;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }
}

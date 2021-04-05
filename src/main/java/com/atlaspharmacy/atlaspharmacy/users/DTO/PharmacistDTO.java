package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;

import java.util.Date;
import java.util.List;

public class PharmacistDTO {
    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
    private Gender gender;
    private AddressDTO address;
    private String role;
    private List<AuthorityDTO> authorities;
    private PharmacyDTO pharmacy;
    private boolean firstTimeChanged;


    public PharmacistDTO() {
    }

    public PharmacistDTO(Long id, String name, String surname, Date dateOfBirth,
                         String phoneNumber, String email, String password, Gender gender,
                         AddressDTO address, String role, List<AuthorityDTO> authorities,
                         PharmacyDTO pharmacy, boolean firstTimePassword) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.authorities = authorities;
        this.pharmacy = pharmacy;
        this.firstTimeChanged = firstTimePassword;
    }

    public boolean isFirstTimeChanged() {
        return firstTimeChanged;
    }

    public void setFirstTimeChanged(boolean firstTimeChanged) {
        this.firstTimeChanged = firstTimeChanged;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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

    public PharmacyDTO getPharmacy() { return pharmacy; }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }


}

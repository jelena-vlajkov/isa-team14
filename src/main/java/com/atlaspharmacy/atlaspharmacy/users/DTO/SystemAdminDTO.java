package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;

import java.util.Date;
import java.util.List;

public class SystemAdminDTO {
    private Long id;
    private String sysName;
    private String sysSurname;
    private Date sysDateOfBirth;
    private String sysPhoneNumber;
    private String sysEmail;
    private String sysPassword;
    private Gender sysGender;
    private AddressDTO sysAddress;
    private String sysRole;
    private List<AuthorityDTO> sysAuthorities;
    private boolean firstTimeChanged;
    public SystemAdminDTO() {
    }

    public SystemAdminDTO(Long id, String sysName, String sysSurname, Date sysDateOfBirth,
                          String sysPhoneNumber, String sysEmail, String sysPassword,
                          Gender sysGender, AddressDTO sysAddress, String sysRole,
                          List<AuthorityDTO> sysAuthorities, boolean firstTimeChanged) {
        this.id = id;
        this.sysName = sysName;
        this.sysSurname = sysSurname;
        this.sysDateOfBirth = sysDateOfBirth;
        this.sysPhoneNumber = sysPhoneNumber;
        this.sysEmail = sysEmail;
        this.sysPassword = sysPassword;
        this.sysGender = sysGender;
        this.sysAddress = sysAddress;
        this.sysRole = sysRole;
        this.sysAuthorities = sysAuthorities;
        this.firstTimeChanged = firstTimeChanged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysSurname() {
        return sysSurname;
    }

    public void setSysSurname(String sysSurname) {
        this.sysSurname = sysSurname;
    }

    public Date getSysDateOfBirth() {
        return sysDateOfBirth;
    }

    public void setSysDateOfBirth(Date sysDateOfBirth) {
        this.sysDateOfBirth = sysDateOfBirth;
    }

    public String getSysPhoneNumber() {
        return sysPhoneNumber;
    }

    public void setSysPhoneNumber(String sysPhoneNumber) {
        this.sysPhoneNumber = sysPhoneNumber;
    }

    public String getSysEmail() {
        return sysEmail;
    }

    public void setSysEmail(String sysEmail) {
        this.sysEmail = sysEmail;
    }

    public String getSysPassword() {
        return sysPassword;
    }

    public void setSysPassword(String sysPassword) {
        this.sysPassword = sysPassword;
    }

    public Gender getSysGender() {
        return sysGender;
    }

    public void setSysGender(Gender sysGender) {
        this.sysGender = sysGender;
    }

    public AddressDTO getSysAddress() {
        return sysAddress;
    }

    public void setSysAddress(AddressDTO sysAddress) {
        this.sysAddress = sysAddress;
    }

    public String getSysRole() {
        return sysRole;
    }

    public void setSysRole(String sysRole) {
        this.sysRole = sysRole;
    }

    public List<AuthorityDTO> getSysAuthorities() {
        return sysAuthorities;
    }

    public void setSysAuthorities(List<AuthorityDTO> sysAuthorities) {
        this.sysAuthorities = sysAuthorities;
    }

    public boolean isFirstTimeChanged() {
        return firstTimeChanged;
    }

    public void setFirstTimeChanged(boolean firstTimeChanged) {
        this.firstTimeChanged = firstTimeChanged;
    }
}


package com.atlaspharmacy.atlaspharmacy.medication.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "e_prescriptions")
public class EPrescription {
    @Id
    private Long id;
    private Date date;
    //fali user

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrescribedDrug> prescribedDrugList;

    public EPrescription() {
    }

    public EPrescription(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public List<PrescribedDrug> getPrescribedDrugList() {
        return prescribedDrugList;
    }

    public void setPrescribedDrugList(List<PrescribedDrug> prescribedDrugList) {
        this.prescribedDrugList = prescribedDrugList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

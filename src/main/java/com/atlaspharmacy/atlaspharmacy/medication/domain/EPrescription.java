package com.atlaspharmacy.atlaspharmacy.medication.domain;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EPrescription {
    private int id;
    private Date date;
    //fali user
    List<PrescribedDrug> prescribedDrugList = new ArrayList<PrescribedDrug>();
    public EPrescription() {
    }

    public EPrescription(int id, Date date, List<PrescribedDrug> drugs) {
        this.id = id;
        this.date = date;
        this.prescribedDrugList = drugs;
    }

    public List<PrescribedDrug> getPrescribedDrugList() {
        return prescribedDrugList;
    }

    public void setPrescribedDrugList(List<PrescribedDrug> prescribedDrugList) {
        this.prescribedDrugList = prescribedDrugList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

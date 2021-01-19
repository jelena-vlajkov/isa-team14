package com.atlaspharmacy.atlaspharmacy.medication.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugForm;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugKind;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugType;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.TypeOfPrescribing;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medications")
public class Medication {
    @Id
    private int id;
    private String name;
    private DrugForm drugForm;
    private DrugType drugType;
    private String producer;
    private TypeOfPrescribing typeOfPrescribing;
    private String additionalNotes;
    private String contraindications;
    private int dailyDose;
    private DrugKind drugKind;

    public Medication(){}
    public Medication(int id, String name, DrugForm drugForm, DrugType drugType, String producer, TypeOfPrescribing typeOfPrescribing, String additionalNotes, String contraindications, int dailyDose, DrugKind drugKind) {
        this.id = id;
        this.name = name;
        this.drugForm = drugForm;
        this.drugType = drugType;
        this.producer = producer;
        this.typeOfPrescribing = typeOfPrescribing;
        this.additionalNotes = additionalNotes;
        this.contraindications = contraindications;
        this.dailyDose = dailyDose;
        this.drugKind = drugKind;
    }
    public Medication(Medication medication){
        this.id = medication.id;
        this.name = medication.name;
        this.drugForm = medication.drugForm;
        this.drugType = medication.drugType;
        this.producer = medication.producer;
        this.typeOfPrescribing = medication.typeOfPrescribing;
        this.additionalNotes = medication.additionalNotes;
        this.contraindications = medication.contraindications;
        this.dailyDose = medication.dailyDose;
        this.drugKind = medication.drugKind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrugForm getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(DrugForm drugForm) {
        this.drugForm = drugForm;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public void setDrugType(DrugType drugType) {
        this.drugType = drugType;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public TypeOfPrescribing getTypeOfPrescribing() {
        return typeOfPrescribing;
    }

    public void setTypeOfPrescribing(TypeOfPrescribing typeOfPrescribing) {
        this.typeOfPrescribing = typeOfPrescribing;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public int getDailyDose() {
        return dailyDose;
    }

    public void setDailyDose(int dailyDose) {
        this.dailyDose = dailyDose;
    }

    public DrugKind getDrugKind() {
        return drugKind;
    }

    public void setDrugKind(DrugKind drugKind) {
        this.drugKind = drugKind;
    }
}

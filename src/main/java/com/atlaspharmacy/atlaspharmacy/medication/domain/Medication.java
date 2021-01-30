package com.atlaspharmacy.atlaspharmacy.medication.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugForm;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugKind;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugType;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.TypeOfPrescribing;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private DrugForm drugForm;
    private DrugType drugType;
    private String producer;
    private TypeOfPrescribing typeOfPrescribing;
    private String additionalNotes;
    private String contraindications;
    private Long dailyDose;
    private DrugKind drugKind;
    private Long code;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "substitute_medications",
            joinColumns = @JoinColumn(name = "original_id"),
            inverseJoinColumns = @JoinColumn(name = "substitute_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medication> substituteMedication;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "medication_ingredients",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Ingredient> ingredients;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "reservedMedication")
//    private List<DrugReservation> reservations;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prescribedMedication")
//    private List<PrescribedDrug> prescribedDrugs;

    public Medication(){}
    public Medication(Long id, String name, DrugForm drugForm, DrugType drugType, String producer, TypeOfPrescribing typeOfPrescribing, String additionalNotes, String contraindications, Long dailyDose, DrugKind drugKind, Long code) {
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
        this.code = code;
        this.substituteMedication = new ArrayList<>();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<Medication> getSubstituteMedication() {
        return substituteMedication;
    }

    public void setSubstituteMedication(List<Medication> substituteMedication) {
        this.substituteMedication = substituteMedication;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public Long getDailyDose() {
        return dailyDose;
    }

    public void setDailyDose(Long dailyDose) {
        this.dailyDose = dailyDose;
    }

    public DrugKind getDrugKind() {
        return drugKind;
    }

    public void setDrugKind(DrugKind drugKind) {
        this.drugKind = drugKind;
    }
}

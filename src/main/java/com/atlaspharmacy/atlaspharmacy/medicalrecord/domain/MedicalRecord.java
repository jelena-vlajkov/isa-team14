package com.atlaspharmacy.atlaspharmacy.medicalrecord.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name = "medical_record")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "medical_record_allergies",
            joinColumns = @JoinColumn(name = "medical_record_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Allergy> allergies;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "medical_record_ingredients",
            joinColumns = @JoinColumn(name = "medical_record_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Ingredient> ingredients;


    public MedicalRecord() {
    }

    public MedicalRecord(Patient patient, List<Allergy> allergies) {
        this.patient = patient;
        this.allergies = allergies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public boolean isPatient(Long patientId) {
        return getPatient().getId().equals(patientId);
    }


    public List<Ingredient> getIngredients() {return ingredients; }

    public void setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients; }
}

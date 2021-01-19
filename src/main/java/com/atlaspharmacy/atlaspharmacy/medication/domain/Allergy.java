package com.atlaspharmacy.atlaspharmacy.medication.domain;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allergies")
public class Allergy {
    @Id
    private int id;
    @ManyToOne
    private Ingredient ingredient;


    public Allergy() {
    }

    public Allergy(int id, Ingredient ingredient) {
        this.id = id;
        this.ingredient = ingredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

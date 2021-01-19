package com.atlaspharmacy.atlaspharmacy.medication.domain;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    private int id;
    private String name;

    public Ingredient(){}

    public Ingredient(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Ingredient(Ingredient ingredient){
        this.id = ingredient.id;
        this.name = ingredient.name;
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
}

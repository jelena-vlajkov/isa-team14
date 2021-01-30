package com.atlaspharmacy.atlaspharmacy.medication.domain;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Proxy(lazy = false)
@Entity
@Table(name = "ingredients")
public class Ingredient{
    @Id
    private Long id;
    private String name;
//moguce da ovako treba o.O
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "alergies_to_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> allergies;


    public Ingredient(){}

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
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

}

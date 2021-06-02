package com.atlaspharmacy.atlaspharmacy.medication.domain;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Proxy(lazy = false)
@Entity
@Table(name = "ingredients")
public class Ingredient{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "alergies_to_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
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

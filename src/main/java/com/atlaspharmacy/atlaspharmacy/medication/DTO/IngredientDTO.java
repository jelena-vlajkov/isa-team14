package com.atlaspharmacy.atlaspharmacy.medication.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientDTO {
    private Long id;
    private String name;
    private List<AllergyDTO> allergies;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<AllergyDTO> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<AllergyDTO> allergies) {
        this.allergies = allergies;
    }

    public static IngredientDTO convertToIngredientDTO(Ingredient i){
        IngredientDTO dto = new IngredientDTO(
                i.getId(),
                i.getName()
        );
        dto.setAllergies(new ArrayList<>());

        return dto;
    }
    /*
    public static void convertToIngrtedient(Ingredient i, IngredientDTO dto){
        i.setId(dto.getId());
        i.setName(dto.getName());
    }*/
}

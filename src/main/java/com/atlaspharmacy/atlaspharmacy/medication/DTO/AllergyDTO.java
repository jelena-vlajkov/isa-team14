package com.atlaspharmacy.atlaspharmacy.medication.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;

import java.util.ArrayList;

public class AllergyDTO {
    private Long id;
    private String name;

    public AllergyDTO() {
    }

    public AllergyDTO(Long id, String name) {
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

    public static AllergyDTO convertToAllergyDTO(Allergy a){
        return new AllergyDTO(a.getId(), a.getName());
    }

    public static void convertToAllergy(Allergy a, AllergyDTO dto){
        a.setId(dto.getId());
        a.setName(dto.getName());
    }


}

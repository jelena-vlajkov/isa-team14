package com.atlaspharmacy.atlaspharmacy.medication.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import java.util.ArrayList;
import java.util.List;

public class IngredientMapper {

        public IngredientMapper(){}

        public  static IngredientDTO convertToIngredientDto(Ingredient ingredient){
            IngredientDTO ingredientDTO = new IngredientDTO(
                    ingredient.getId(),
                    ingredient.getName()
            );

            ingredientDTO.setAllergies(new ArrayList<>());
            for (Allergy a : ingredient.getAllergies()){
                ingredientDTO.getAllergies().add(a.getId());
            }

            return  ingredientDTO;

        }

    public static void convertToIngredient(Ingredient m, IngredientDTO mdto) {
        m.setId(mdto.getId());
        m.setName(mdto.getName());
    }


    public static List<IngredientDTO> convertToDTOS(List<Ingredient> ingredients){
        List<IngredientDTO> dtos = new ArrayList<>();
        for(Ingredient i : ingredients){
            IngredientDTO dto = convertToIngredientDto(i);
            dtos.add(dto);
        }

        return dtos;
    }
}

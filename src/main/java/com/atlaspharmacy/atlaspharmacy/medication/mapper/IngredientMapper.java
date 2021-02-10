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
                    ingredient.getName());
            ingredientDTO.setAllergies(AllergyMapper.convertToDTOS(ingredient.getAllergies()));

            return  ingredientDTO;

        }

    public static void convertToIngredient(Ingredient m, IngredientDTO mdto) {
        m.setId(mdto.getId());
        m.setName(mdto.getName());
        m.setAllergies(AllergyMapper.convertToList(mdto.getAllergies()));
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

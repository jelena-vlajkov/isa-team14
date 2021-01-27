package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import java.util.List;

public interface IIngredientService {
    IngredientDTO findById(Long id);
    List<IngredientDTO> findAll();
    void saveIngredient(Ingredient ingredient, IngredientDTO ingredientDTO) throws Exception;
}

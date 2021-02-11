package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IIngredientService {
    IngredientDTO findById(Long id);
    List<IngredientDTO> findAll();
    Ingredient saveIngredient(IngredientDTO ingredientDTO);
    List<IngredientDTO> getIngredientsById(@RequestBody List<Long> ids);
}

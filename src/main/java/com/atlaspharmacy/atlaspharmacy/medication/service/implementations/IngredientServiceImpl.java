package com.atlaspharmacy.atlaspharmacy.medication.service.implementations;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.IngredientMapper;
import com.atlaspharmacy.atlaspharmacy.medication.repository.IIngredientRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientServiceImpl implements IIngredientService {

    @Autowired
    IIngredientRepository _ingredientRepository;

    final private static String EXCEPTION = "Exception in Ingredient Service Implementation method:";
    final private static String DOES_NOT_EXIST = "Ingredient with Id does not exist";
    final private static String FAIL = "execution failed";

    @Override
    public IngredientDTO findById(Long id) {
        Ingredient ingredient = _ingredientRepository.findById(id).orElse(null);
        if(ingredient == null){
            throw  new NoSuchElementException(EXCEPTION + " findById" + DOES_NOT_EXIST);
        }

        return  IngredientDTO.convertToIngredientDTO(ingredient);
    }

    @Override
    public List<IngredientDTO> findAll() {
        List<Ingredient> ingredients = _ingredientRepository.findAll();
        return IngredientMapper.convertToDTOS(ingredients);
    }

    @Override
    public Ingredient saveIngredient(IngredientDTO ingredientDTO){
        for(IngredientDTO ing : findAll()){
            if(ing.getName().equalsIgnoreCase(ingredientDTO.getName())){
                return null;
            }
        }
        Ingredient i = new Ingredient();
        IngredientMapper.convertToIngredient(i, ingredientDTO);
        return _ingredientRepository.save(i);
    }


    @Override
    public List<IngredientDTO> getIngredientsById(List<Long> ids) {
        List<IngredientDTO> dtos = new ArrayList<>();
        for(Long id : ids){
            dtos.add(IngredientMapper.convertToIngredientDto(_ingredientRepository.findIngredientById(id)));
        }
        return dtos;
    }


}

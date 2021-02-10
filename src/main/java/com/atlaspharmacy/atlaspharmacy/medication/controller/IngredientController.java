package com.atlaspharmacy.atlaspharmacy.medication.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.MedicationAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/ingredients")
public class IngredientController {
    @Autowired
    private IIngredientService _ingredientService;

    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIngredients(){

        List<IngredientDTO> ingredientDTOS = _ingredientService.findAll();

        return new ResponseEntity<>(ingredientDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/getByIds",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IngredientDTO> getIngredientsById(@RequestBody List<Long> ids){

        return _ingredientService.getIngredientsById(ids);
    }

    @GetMapping(value="/getById",produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDTO getIngredientById(@RequestParam("id") Long id){

        return _ingredientService.findById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @MedicationAuthorization
    public ResponseEntity<?> addIngredient(@RequestBody IngredientDTO ingredientDTO){
//        try {
           if( _ingredientService.saveIngredient(ingredientDTO)!=null){
               return new ResponseEntity<>(HttpStatus.OK);

           }
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }
}

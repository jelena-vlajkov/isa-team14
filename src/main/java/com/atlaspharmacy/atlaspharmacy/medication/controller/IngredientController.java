package com.atlaspharmacy.atlaspharmacy.medication.controller;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/ingredients")
public class IngredientController {
    @Autowired
    private IIngredientService _ingredientService;

    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
///    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getIngredients(){

        List<IngredientDTO> ingredientDTOS = _ingredientService.findAll();

        return new ResponseEntity<>(ingredientDTOS, HttpStatus.OK);
    }

}

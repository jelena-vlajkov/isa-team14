package com.atlaspharmacy.atlaspharmacy.medication.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.MedicationAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.service.IAllergyService;
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
@RequestMapping(value = "/allergies")
public class AllergyController {

    @Autowired
    private IAllergyService _allergyService;

    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAllergies(){

        List<AllergyDTO> allergyDTOS = _allergyService.findAll();

        return new ResponseEntity<>(allergyDTOS, HttpStatus.OK);
    }


}

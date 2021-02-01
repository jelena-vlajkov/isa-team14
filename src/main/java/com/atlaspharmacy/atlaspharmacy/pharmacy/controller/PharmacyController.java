package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/pharmacy",produces = MediaType.APPLICATION_JSON_VALUE)

public class PharmacyController {
    @Autowired
    private IPharmacyService _pharmacyService;

    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @GetMapping(value="/findById",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(Long id){

        Pharmacy pharmacy=_pharmacyService.getById(id);
        return new ResponseEntity<>(pharmacy, HttpStatus.OK);
    }
}

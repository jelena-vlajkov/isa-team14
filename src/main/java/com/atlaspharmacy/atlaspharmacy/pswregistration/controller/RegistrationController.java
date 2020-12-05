package com.atlaspharmacy.atlaspharmacy.pswregistration.controller;
import com.atlaspharmacy.atlaspharmacy.pswregistration.model.Hospital;
import com.atlaspharmacy.atlaspharmacy.pswregistration.service.IHospitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {

    @Autowired
    private IHospitalService hospitalService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/pswapi/{api}")
    ResponseEntity<String> getByAPIKey(@PathVariable String api)
    {
        Hospital hospital = hospitalService.get(api);
        return hospital == null ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            ResponseEntity.ok(hospital.getName());
    }

    @PostMapping("/pswapi")
    ResponseEntity<String> register(@RequestBody Hospital hospital)
    {
        return hospitalService.add(hospital) ?
            ResponseEntity.ok(hospital.getApiKey()) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
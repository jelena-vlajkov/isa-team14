package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions.InvalidPharmacyData;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/pharmacy",produces = MediaType.APPLICATION_JSON_VALUE)

public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @GetMapping(value="/findById",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(Long id){

        Pharmacy pharmacy=pharmacyService.getById(id);
        return new ResponseEntity<>(pharmacy, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void registerPharmacy(@RequestBody PharmacyDTO pharmacyDTO) throws InvalidPharmacyData, ParseException{
         pharmacyService.registerPharmacy(pharmacyDTO);
    }

    @ExceptionHandler(InvalidPharmacyData.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPharmacyData handleException(InvalidPharmacyData e) {
        return new InvalidPharmacyData();
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ParseException handleException(ParseException e) {
        return new ParseException("Error while parsing values", 0);
    }

}

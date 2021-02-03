package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacyAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin( origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value="/pharmacyAdmin")
public class PharmacyAdminController {
    private IPharmacyAdminService _pharmacyAdminService;

    @Autowired
    public PharmacyAdminController(IPharmacyAdminService _pharmacyAdminService) {
        this._pharmacyAdminService = _pharmacyAdminService;
    }

    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getPharmacyByAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPharmacyByPharmacyAdmin(@RequestParam("id") Long id) throws ParseException {
        return new ResponseEntity<>(_pharmacyAdminService.getPharmacyByPharmacyAdmin(id),HttpStatus.OK);
    }

    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws ParseException {
        return new ResponseEntity<>(_pharmacyAdminService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerPharmacyAdmin(@RequestBody PharmacyAdminDTO pharmacyAdminDTO) throws InvalidEmail , ParseException{
        try {
            pharmacyAdminDTO.setRole("PharmacyAdmin");
            PharmacyAdmin pha = _pharmacyAdminService.registerPharmacyAdmin(pharmacyAdminDTO);

        } catch (InvalidEmail email) {
            email.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(InvalidEmail.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidEmail handleException(InvalidEmail e) {
        return new InvalidEmail();
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ParseException handleException(ParseException e) {
        return new ParseException("Error while parsing values", 0);
    }


}


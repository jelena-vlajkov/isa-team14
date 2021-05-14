package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PasswordChangerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacyAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value="/pharmacyAdmin")
public class PharmacyAdminController {
    private IPharmacyAdminService pharmacyAdminService;

    @Autowired
    public PharmacyAdminController(IPharmacyAdminService pharmacyAdminService) {
        this.pharmacyAdminService = pharmacyAdminService;
    }

    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getPharmacyByAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPharmacyByPharmacyAdmin(@RequestParam("id") Long id) throws ParseException {
        return new ResponseEntity<>(pharmacyAdminService.getPharmacyByPharmacyAdmin(id),HttpStatus.OK);
    }

    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws ParseException {
        return new ResponseEntity<>(pharmacyAdminService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> registerPharmacyAdmin(@RequestBody PharmacyAdminDTO pharmacyAdminDTO) throws InvalidEmail , ParseException{
        try {
            pharmacyAdminDTO.setRole("PharmacyAdmin");
            PharmacyAdmin pha = pharmacyAdminService.registerPharmacyAdmin(pharmacyAdminDTO);

        } catch (InvalidEmail email) {
            email.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/changepassword", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangerDTO passwordChangerDTO) throws InvalidEmail, ParseException, InvalidPassword {
        if (pharmacyAdminService.changePassword(passwordChangerDTO.getOldpassword(), passwordChangerDTO.getNewpassword())) {
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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


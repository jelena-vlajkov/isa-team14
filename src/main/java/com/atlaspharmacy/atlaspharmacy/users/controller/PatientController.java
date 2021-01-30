package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin

@RequestMapping(value = "/patient")
public class PatientController {
    private final IUserService userService;

    @Autowired
    public PatientController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerPatient(@RequestBody PatientDTO patient) throws ParseException, InvalidPatientData {
//        patient.setRole("Patient");
//        return userService.registerPatient(patient);
        try {
            patient.setRole("Patient");
            userService.registerPatient(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @ExceptionHandler(InvalidPatientData.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPatientData handleException(InvalidPatientData e) {
        return new InvalidPatientData();
    }

}

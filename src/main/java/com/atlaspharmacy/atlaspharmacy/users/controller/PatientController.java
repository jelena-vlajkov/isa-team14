package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.PatientService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.VerificationTokenService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;
    private final EmailService emailService;


    @Autowired
    public PatientController(PatientService patientService, EmailService emailService) {
        this.patientService = patientService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerPatient(@RequestBody PatientDTO patientDTO) throws ParseException, InvalidPatientData {
        patientDTO.setRole("Patient");
        try {
            Patient p = patientService.registerPatient(patientDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }




    @RequestMapping(value="/activation", method = RequestMethod.GET)
    @ResponseBody
    public String activation(@RequestParam(value = "user_id") Long user_id, @RequestParam(value = "token") String token) {
        patientService.enablePatient(user_id);
        return "OK";
    }

    @ExceptionHandler(InvalidPatientData.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPatientData handleException(InvalidPatientData e) {
        return new InvalidPatientData();
    }

}

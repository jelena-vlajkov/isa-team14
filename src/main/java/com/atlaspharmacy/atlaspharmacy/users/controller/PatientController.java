package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.UserMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.PatientService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.VerificationTokenService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    //http://localhost:8088/patient/activation?user_id=2&token=tWLuPeSQbxJ15LiVFi4XJOjmYtxCZzZE2htXbgOw6nlCJ2mzrBn1H4BxcFdBqUvM
    @RequestMapping(value="/activation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> activation(@RequestBody String token) {
        Patient p = patientService.enablePatient(token);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }


    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws ParseException {
        return new ResponseEntity<>(patientService.findById(id), HttpStatus.OK);
    }

    /*
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/getLoggedPatient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('DERMATOLOGIST') || hasRole('PATIENT')")
    public @ResponseBody
    Patient getLoggedUser() throws ParseException {
        //iz fronta ne salje token wtf
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((Patient)user).getEmail();
        return patientService.getByMail(mail);

    }*/


    @CrossOrigin( origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/editPatient", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<?> editPatient(@RequestBody PatientDTO patientDTO) throws ParseException, InvalidPatientData {

        try {
            patientService.editPatient(patientDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value="/getLoggedPatient", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientDTO getLoggedInUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((Patient)user).getEmail();
        return PatientMapper.mapPatientToDTO(patientService.getByMail(mail));

    }



    @ExceptionHandler(InvalidPatientData.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPatientData handleException(InvalidPatientData e) {
        return new InvalidPatientData();
    }

}

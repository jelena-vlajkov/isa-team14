package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.VerificationToken;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.service.IPatientService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.PatientService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@RestController
@CrossOrigin

@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;
    private final VerificationTokenService verificationTokenService;
    @Autowired
    public PatientController(PatientService patientService, VerificationTokenService verificationTokenService) {
        this.patientService = patientService;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerPatient(@RequestBody PatientDTO patient) throws ParseException, InvalidPatientData {
//        patient.setRole("Patient");
//        return userService.registerPatient(patient);
        try {
            patient.setRole("Patient");
            patientService.registerPatient(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model){
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if(verificationToken == null){
            model.addAttribute("message", "Your verification token is invalid.");
        }else{
            Patient patient = verificationToken.getPatient();
            if(!patient.getEnabled()){
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                if(verificationToken.getExpiryDate().before(currentTime)){
                    model.addAttribute("message", "Your verification token has expired");

                }else{
                    patient.setEnabled(true);
                    patientService.save(patient);
                    model.addAttribute("message","Your account has been activated!");
                }
            }else{
                model.addAttribute("message", "Your account is active already");
            }
        }
        return "activation";
    }

    @ExceptionHandler(InvalidPatientData.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPatientData handleException(InvalidPatientData e) {
        return new InvalidPatientData();
    }

}

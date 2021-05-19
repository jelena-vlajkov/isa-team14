package com.atlaspharmacy.atlaspharmacy.medication.controller;


import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.EPrescriptionDTO;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.EPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/ePrescription")
public class EPrescriptionController {

    @Autowired
    private EPrescriptionService ePrescriptionService;

    @GetMapping(value="/getPatientEPrescriptions",produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<EPrescriptionDTO> getPatientEPrescriptions(@RequestParam("patientId") Long patientId) {
        return ePrescriptionService.getAllEPrescritpions(patientId);
    }
}

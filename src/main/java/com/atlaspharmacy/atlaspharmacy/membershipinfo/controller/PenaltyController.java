package com.atlaspharmacy.atlaspharmacy.membershipinfo.controller;


import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PenaltyAuthorization;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PenaltyDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper.PenaltyMapper;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IPenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "penalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class PenaltyController {

    private final IPenaltyService penaltyService;

    @Autowired
    public PenaltyController(IPenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @PostMapping(value = "addPenalty")
    @PenaltyAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseEntity<?> savePenalty(@RequestBody PenaltyDTO penaltyDTO) throws Exception {
        penaltyService.savePenalty(PenaltyMapper.mapPenaltyFromDto(penaltyDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "getForPatient")
    @PenaltyAuthorization
    List<Penalty> getByPatient(@RequestParam("id") Long patientId) {
        return penaltyService.getByPatient(patientId);
    }

    @GetMapping(value = "/getNumberOfPatientPenalties")
    @PatientAuthorization
    int getNumberOfPatientPenalties(@RequestParam("patientId") Long patientId) throws Exception {
        return penaltyService.getNumberOfPatientPenaltiesForThisMonth(patientId);
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Exception handleException(Exception e) {
        return new Exception(e.getMessage());
    }


}

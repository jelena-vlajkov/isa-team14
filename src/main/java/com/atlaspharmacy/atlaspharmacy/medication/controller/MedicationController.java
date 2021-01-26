package com.atlaspharmacy.atlaspharmacy.medication.controller;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    @Autowired
    private IMedicationService _medicationService;


    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
///    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllMedications(){

        List<MedicationDTO> medicationDTOS = _medicationService.findAll();

        return new ResponseEntity<>(medicationDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> addMedicationToSystem(@RequestBody MedicationDTO medicationDTO){

        try {
            _medicationService.createMedication(medicationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

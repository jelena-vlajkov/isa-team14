package com.atlaspharmacy.atlaspharmacy.medication.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.service.IEPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.EPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/prescription",produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescriptionController {
    private final IEPrescriptionService prescriptionService;

    @Autowired
    PrescriptionController(IEPrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/savePrescription", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    public @ResponseBody
    ResponseEntity<?> saveReservation(@RequestBody CreateDrugReservationDTO dto) throws Exception {
        prescriptionService.saveNewPrescription(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Exception handleException(Exception e) {
        return e;
    }
}

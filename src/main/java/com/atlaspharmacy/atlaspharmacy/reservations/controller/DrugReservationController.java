package com.atlaspharmacy.atlaspharmacy.reservations.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.DrugReservationAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.IssueReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.PatientDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.reservations.mapper.DrugReservationMapper;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugReservationController {
    private  final IDrugReservationService drugReservationService;
    private static final String dueDateExpiring = "Could not find wanted reservation. Possibly invalid unique identifier.";

    @Autowired
    public DrugReservationController(IDrugReservationService drugReservationService) {
        this.drugReservationService = drugReservationService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/saveReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    public @ResponseBody
    ResponseEntity<?> saveReservation(@RequestBody CreateDrugReservationDTO dto) throws Exception {
        drugReservationService.reserveDrug(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getReservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @DrugReservationAuthorization
    public @ResponseBody
    List<DrugReservationDTO> getReservationsByPharmacy(@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        return drugReservationService.findAllReservation(pharmacyId);
    }

    @PostMapping(value = "/issueReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    @DrugReservationAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    boolean issueResevation(@RequestBody IssueReservationDTO dto) throws Exception {
        return drugReservationService.issueDrugReservation(dto.getUniqueIdentifier(), dto.getMedicalStaffId());
    }


    @GetMapping(value = "/getReservationByIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    DrugReservationDTO getReservationByIdentifier(@RequestParam("uniqueIdentifier") int uniqueIdentifier, @RequestParam("medicalStaffId") Long medicallStaffId) throws Exception {
        return DrugReservationMapper.mapDrugReservationToDTO(drugReservationService.findDrugReservation(uniqueIdentifier, medicallStaffId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/patientDrugReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    ResponseEntity<?> patientDrugReservation(@RequestBody CreateDrugReservationDTO dto) throws Exception {
        drugReservationService.patientDrugReservation(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getDrugReservationForPatient", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    List<PatientDrugReservationDTO> getDrugReservationForPatient(@RequestParam("patientId") Long patientId) throws Exception {
        return drugReservationService.getDrugReservationForPatient(patientId);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/cancelDrugReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    ResponseEntity<String> cancelDrugReservation(@RequestBody Long reservationId) throws  ParseException {
        if (drugReservationService.cancelDrugReservation(reservationId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/isDrugReserved")
    boolean isDrugReserved(@RequestParam("medicationId") Long medicationId,@RequestParam("pharmacyId") Long pharmacyId) throws Exception {
        return drugReservationService.isDrugReserved(medicationId,pharmacyId);
    }


        @ExceptionHandler(DueDateSoonException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    DueDateSoonException handleException(DueDateSoonException e) {
        return new DueDateSoonException(dueDateExpiring);
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ParseException handleException(ParseException e) {
        return new ParseException("Error while parsing values", 0);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Exception handleException(Exception e) {
        return e;
    }

}

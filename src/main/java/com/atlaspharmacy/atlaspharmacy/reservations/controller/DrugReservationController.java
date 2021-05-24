package com.atlaspharmacy.atlaspharmacy.reservations.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.DrugReservationAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
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
        return DrugReservationMapper.mapDrugReservationToListDTO(drugReservationService.findAllReservation(1L));
    }

    @GetMapping(value = "/issueReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    @DrugReservationAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    boolean issueResevation(HttpServletRequest request,
                                          HttpServletResponse response) throws DueDateSoonException, IOException, MessagingException {
        return drugReservationService.issueDrugReservation(Integer.parseInt(request.getParameter("uniqueIdentifier")));
    }

    @GetMapping(value = "/getReservationByIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    DrugReservationDTO getReservationByIdentifier(@RequestParam("uniqueIdentifier") int uniqueIdentifier) throws Exception {
        return DrugReservationMapper.mapDrugReservationToDTO(drugReservationService.findDrugReservation(uniqueIdentifier));
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

package com.atlaspharmacy.atlaspharmacy.reservations.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.DrugReservationAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.reservations.mapper.DrugReservationMapper;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugReservationController {
    private final IDrugReservationService drugReservationService;
    private final String dueDateExpiring = "Could not find wanted reservation. Possibly invalid unique identifier.";

    @Autowired
    public DrugReservationController(IDrugReservationService drugReservationService) {
        this.drugReservationService = drugReservationService;
    }

    @RequestMapping(value = "/getReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @DrugReservationAuthorization
    public @ResponseBody
    List<DrugReservationDTO> getReservationsByPharmacy(@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        return DrugReservationMapper.mapDrugReservationToListDTO(drugReservationService.findAllReservation(1L));
    }

    @RequestMapping(value = "/issueReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @DrugReservationAuthorization
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    boolean issueResevation(HttpServletRequest request,
                                          HttpServletResponse response) throws DueDateSoonException {
        return drugReservationService.issueDrugReservation(Integer.parseInt(request.getParameter("uniqueIdentifier")));
    }

    @RequestMapping(value = "/getReservationByIdentifier", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @DrugReservationAuthorization
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DrugReservationDTO getReservationByIdentifier(HttpServletRequest request,
                            HttpServletResponse response) throws DueDateSoonException {
        return DrugReservationMapper.mapDrugReservationToDTO(drugReservationService.findDrugReservation(Integer.parseInt(request.getParameter("uniqueIdentifier"))));
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

}

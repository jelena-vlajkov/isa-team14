package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.service.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/vacationRequest")
public class VacationRequestController {

    private final IVacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(IVacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    @PostMapping(value = "/saveVacationRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> saveNewVacationRequest(@RequestBody VacationRequestDTO vacationRequestDTO) throws Exception {
        vacationRequestService.saveVacationRequest(vacationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Exception handleException(Exception e) {
        return e;
    }

}

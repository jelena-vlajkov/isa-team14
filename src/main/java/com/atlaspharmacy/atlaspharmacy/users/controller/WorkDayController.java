package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.service.IWorkDayService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value = "/workDay")
public class WorkDayController {
    private final IWorkDayService workDayService;

    @Autowired
    public WorkDayController(IWorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @PostMapping(value = "/addWorkDay", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addWorkDay(@RequestBody WorkDayDTO workDayDTO) throws InvalidEmail, ParseException {
        try {
            workDayService.addWorkday(workDayDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addWorkDayForDermatologist", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addWorkDayForDermatologist(@RequestBody WorkDayDTO workDayDTO) throws InvalidEmail, ParseException {
        boolean successfull;
        try {
            successfull=workDayService.addWorkdayForDermatologist(workDayDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(successfull) {return new ResponseEntity<>(HttpStatus.OK);}
        else {return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
    }
}

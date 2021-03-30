package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value="/dermatologist")
public class DermatologistController {

    private final IDermatologistService _dermatologistService;

    @Autowired
    public DermatologistController(IDermatologistService _dermatologistService) {
        this._dermatologistService = _dermatologistService;
    }

    @GetMapping(value = "/getByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Dermatologist> getByPharmacy(@RequestParam("id") Long id) throws ParseException {
        return _dermatologistService.findAllByPharmacy(id);
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> registerDermatologist(@RequestBody DermatologistDTO dermatologistDTO) throws InvalidEmail , ParseException{
        try {
            dermatologistDTO.setRole("Dermatologist");
            Dermatologist d = _dermatologistService.registerDermatologist(dermatologistDTO);

        } catch (InvalidEmail email) {
            email.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getDermatologistToComplain", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DermatologistDTO> getDermatologistToComplain(@RequestParam("id") Long id) throws ParseException {
        return DermatologistMapper.mapToListDTOS(_dermatologistService.getAllDermatologistsToComplain(id));
    }


    @ExceptionHandler(InvalidEmail.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidEmail handleException(InvalidEmail e) {
        return new InvalidEmail();
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ParseException handleException(ParseException e) {
        return new ParseException("Error while parsing values", 0);
    }

    @GetMapping(value = "/searchDermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DermatologistDTO> searchDermatologists(@RequestParam("searchInput") String searchInput){
        return DermatologistMapper.mapToListDTOS(_dermatologistService.searchDermatologists(searchInput));
    }
}

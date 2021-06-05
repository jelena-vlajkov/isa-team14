package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/dermatologist")
public class DermatologistController {

    private final IDermatologistService dermatologistService;

    @Autowired
    public DermatologistController(IDermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
    }

    @GetMapping(value = "/getByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Dermatologist> getByPharmacy(@RequestParam("id") Long id) throws ParseException {
        return dermatologistService.findAllByPharmacy(id);
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> registerDermatologist(@RequestBody DermatologistDTO dermatologistDTO) throws InvalidEmail , ParseException{
        try {
            dermatologistDTO.setRole("Dermatologist");
            Dermatologist d = dermatologistService.registerDermatologist(dermatologistDTO);

        } catch (InvalidEmail email) {
            email.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getDermatologistToComplain", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DermatologistDTO> getDermatologistToComplain(@RequestParam("id") Long id) throws ParseException {
        return DermatologistMapper.mapToListDTOS(dermatologistService.getAllDermatologistsToComplain(id));
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DermatologistDTO> getAll(){
        return DermatologistMapper.mapToListDTOS(dermatologistService.getAll());
    }

    @PostMapping(value = "/addDermatologistToPharmacy")
    @PharmacyAdminAuthorization
    public ResponseEntity<?> addDermatologistToPharmacy(@RequestParam("pharmacyId") Long pharmacyId,
                                                     @RequestParam("dermatologistId") Long dermatologistId){
        try {
            dermatologistService.addDermatologistToPharmacy(dermatologistId,pharmacyId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/deleteDermatologistFromPharmacy")
    @PharmacyAdminAuthorization
    public ResponseEntity<?> deleteDermatologistFromPharmacy(@RequestParam("pharmacyId") Long pharmacyId,
                                                     @RequestParam("dermatologistId") Long dermatologistId){
        boolean successful;
        try {
            successful=dermatologistService.deleteDermatologistFromPharmacy(dermatologistId,pharmacyId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //ako vrati true,dermatolog nema zakazano nista i moze se obrisati,ako ne,ne sme se obrisatiiii,moze ovako?
        if(successful){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }


    @GetMapping(value = "/searchDermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<DermatologistDTO> searchDermatologists(@RequestParam("pharmacyId")Long pharmacyId,@RequestParam("searchInput") String searchInput){
        return DermatologistMapper.mapToListDTOS(dermatologistService.searchDermatologists(pharmacyId,searchInput));
    }
    @GetMapping(value = "/getDermatologistsNotInPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<DermatologistDTO> getDermatologistsNotInPharmacy(@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        return dermatologistService.getDermatologistsNotInPharmacy(pharmacyId);
    }

    @PostMapping(value = "/filterDermatologistsByGrade", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<DermatologistDTO> filterDermatologistsByGrade(@RequestBody List<DermatologistDTO> dermatologists,@RequestParam("grade") int grade) throws ParseException {
        return dermatologistService.filterDermatologistsByGrade(dermatologists,grade);
    }

    @PostMapping(value = "/filterDermatologistsByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<DermatologistDTO> filterDermatologistsByPharmacy(@RequestBody List<DermatologistDTO> dermatologists,@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        return dermatologistService.filterDermatologistsByPharmacy(dermatologists,pharmacyId);
    }

    @GetMapping(value = "/findDermatologistsForPatientGrading", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<DermatologistDTO> findForPatientGrading(@RequestParam("patientId") Long patientId) {
        return dermatologistService.findForPatientGrading(patientId);
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


}

package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions.InvalidPharmacyData;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.mapper.AppointmentMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/pharmacy",produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

//    @CrossOrigin( origins = "*", allowedHeaders = "*")
//    @GetMapping(value="/findById",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getById(Long id){
//
//        Pharmacy pharmacy=pharmacyService.getById(id);
//        return new ResponseEntity<>(pharmacy, HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('SYSADMIN')")
    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void registerPharmacy(@RequestBody PharmacyDTO pharmacyDTO) throws InvalidPharmacyData, ParseException{
         pharmacyService.registerPharmacy(pharmacyDTO);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<PharmacyDTO> findAll(){
        return pharmacyService.getAllPharmacies();
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacyDTO> getByName(@RequestParam("name") String name) throws ParseException{
        return pharmacyService.findByName(name);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByAddress", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacyDTO> getByAddress(@RequestParam("address") String address) throws ParseException{
        return pharmacyService.findByAddress(address);
    }

    @GetMapping(value = "/getPharmacyToComplain", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<PharmacyDTO> getPharmacyToComplain(@RequestParam("id") Long id) throws ParseException {
        return PharmacyMapper.maptToListDto(pharmacyService.getPharmaciesToComplain(id));
    }


    @GetMapping(value = "/getByMedication", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacyDTO> getByMedication(@RequestParam("code") Long code) throws ParseException {
        return pharmacyService.getPharmaciesByMedication(code);
    }
    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PharmacyDTO getById(@RequestParam("id") Long id) throws ParseException {
        return pharmacyService.getById(id);
    }
    @GetMapping(value = "/getSubscribed", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<PharmacyDTO> getSubscribed(@RequestParam("id") Long id) throws ParseException {
        return pharmacyService.getSubscribed(id);
    }

    @ExceptionHandler(InvalidPharmacyData.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPharmacyData handleException(InvalidPharmacyData e) {
        return new InvalidPharmacyData();
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ParseException handleException(ParseException e) {
        return new ParseException("Error while parsing values", 0);
    }

    @PostMapping(value = "/editPharmacy", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void editPharmacy(@RequestBody PharmacyDTO pharmacyDTO) throws InvalidPharmacyData, ParseException{
        pharmacyService.editPharmacy(pharmacyDTO);
    }
}

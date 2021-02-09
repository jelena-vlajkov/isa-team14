package com.atlaspharmacy.atlaspharmacy.medication.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.MedicationAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication",produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicationController {

    @Autowired
    private IMedicationService _medicationService;


    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMedications(){

        List<MedicationDTO> medicationDTOS = _medicationService.findAll();

        return new ResponseEntity<>(medicationDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @MedicationAuthorization
    public ResponseEntity<?> addMedicationToSystem(@RequestBody MedicationDTO medicationDTO){

        try {
            _medicationService.createMedication(medicationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MedicationDTO> getByName(@RequestParam("name") String name) throws ParseException {
        return _medicationService.findByName(name);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByType", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MedicationDTO> getByType(@RequestParam("type") Long type) throws ParseException {
        return _medicationService.findByType(type);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByForm", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MedicationDTO> getByForm(@RequestParam("form") Long form) throws ParseException {
        return _medicationService.findByForm(form);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByKind", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MedicationDTO> getByKind(@RequestParam("kind") Long kind) throws ParseException {
        return _medicationService.findByKind(kind);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByPrescribing", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MedicationDTO> getByPrescribing(@RequestParam("prescribing") Long prescribing) throws ParseException {
        return _medicationService.findByPrescribing(prescribing);
    }
}

package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.StorageAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyStorageDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyStorageMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pharmacyStorage", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyStorageController {
    private final IPharmacyStorageService pharmacyStorageService;

    @Autowired
    public PharmacyStorageController(IPharmacyStorageService pharmacyStorageService) {
        this.pharmacyStorageService = pharmacyStorageService;
    }


    @GetMapping(value = "/getMedicationsInPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @StorageAuthorization
    public @ResponseBody
    List<PharmacyStorageDTO> getMedicationsInPharmacy(@RequestParam("ph") Long pharmacyId) {
        return PharmacyStorageMapper.maptToListDto(pharmacyStorageService.getMedicationsByPharmacy(pharmacyId));
    }

    @GetMapping(value = "/getMedicationQuantity", produces = MediaType.APPLICATION_JSON_VALUE)
    @StorageAuthorization
    public @ResponseBody
    PharmacyStorageDTO getAllPharmacyStorage(@RequestParam("m") Long medicationId, @RequestParam("ph") Long pharmacyId)  {
        return PharmacyStorageMapper.mapToDto(pharmacyStorageService.getMedicationInPharmacy(medicationId, pharmacyId));
    }

    @PostMapping(value = "/deleteMedicationFromStorage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMedicationFromPharmacy(@RequestParam("medicationId") Long medicationId,
                                                          @RequestParam("pharmacyId") Long pharmacyId){
        try {
            pharmacyStorageService.deleteMedicationFromPharmacyStorage(medicationId,pharmacyId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/editMedicationAmount",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMedicationFromPharmacy(@RequestParam("medicationId") Long medicationId,
                                                          @RequestParam("pharmacyId") Long pharmacyId,
                                                          @RequestParam("amount") int amount){
        try {
            pharmacyStorageService.editMedicationAmount(medicationId,pharmacyId,amount);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addMedicationToStorage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMedicationToPharmacy(@RequestParam("medicationId") Long medicationId,
                                                     @RequestParam("pharmacyId") Long pharmacyId,
                                                     @RequestParam("amount") int amount){
        try {
            pharmacyStorageService.addMedicationToPharmacy(medicationId,pharmacyId,amount);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getMedicationsNotInPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MedicationDTO> getMedicationsNotInPharmacy(@RequestParam("ph") Long pharmacyId) {
        return pharmacyStorageService.getMedicationsNotInPharmacy(pharmacyId);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Exception handleException(Exception e) {
        return new Exception("Oh no, internal server error!" + e.getMessage());
    }

}

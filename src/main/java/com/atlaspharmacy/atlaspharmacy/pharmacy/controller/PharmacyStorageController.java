package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.StorageAuthorization;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.MedicationInStorageDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyStorageMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    List<MedicationInStorageDTO> getAllPharmacyStorage(@RequestParam("ph") Long pharmacyId) {
        return PharmacyStorageMapper.maptToListDto(pharmacyStorageService.getMedicationsByPharmacy(pharmacyId));
    }

    @GetMapping(value = "/getMedicationQuantity", produces = MediaType.APPLICATION_JSON_VALUE)
    @StorageAuthorization
    public @ResponseBody
    MedicationInStorageDTO getAllPharmacyStorage(@RequestParam("m") Long medicationId, @RequestParam("ph") Long pharmacyId)  {
        return PharmacyStorageMapper.mapToDto(pharmacyStorageService.getMedicationInPharmacy(medicationId, pharmacyId));
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Exception handleException(Exception e) {
        return new Exception("Oh no, internal server error!" + e.getMessage());
    }

}

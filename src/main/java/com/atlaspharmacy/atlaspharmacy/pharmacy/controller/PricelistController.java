package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PricelistMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pricelist")
public class PricelistController {
    private final IPricelistService pricelistService;

    public PricelistController(IPricelistService pricelistService) {
        this.pricelistService = pricelistService;
    }

    @GetMapping(value = "/getByMedication", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PricelistDTO> getByMedication(@RequestParam("code") Long code) throws ParseException {
        return pricelistService.getPricelistsByMedication(code);
    }

    @GetMapping(value = "/getByMedicationAndPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PricelistDTO getByMedicationAndPharmacy(@RequestParam("code") Long code,@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        if(pricelistService.getPricelistForMedicationAndPharmacy(code,pharmacyId)!=null){
            return PricelistMapper.mapPricelistToDTO(pricelistService.getPricelistForMedicationAndPharmacy(code,pharmacyId));
        }
        else{
            return null;
        }

    }

    @PostMapping(value = "/editPricelistEntity", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editPricelist(@RequestBody List<PricelistDTO> pricelistDTO) {
        try {
            pricelistService.editPricelist(pricelistDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addPricelistEntity", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPricelistEntity(@RequestBody PricelistDTO pricelistDTO) {
        try {
            pricelistService.addMedicationToPricelist(pricelistDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/deletePricelistEntity", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePricelistEntity(@RequestBody Long pricelistId) {
        try {
            pricelistService.deletePricelistEntity(pricelistId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

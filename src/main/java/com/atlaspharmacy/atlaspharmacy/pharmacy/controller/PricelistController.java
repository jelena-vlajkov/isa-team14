package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PricelistMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyPricelistService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
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
    private final IPharmacyPricelistService pharmacyPricelistService;

    public PricelistController(IPricelistService pricelistService, IPharmacyPricelistService pharmacyPricelistService) {
        this.pricelistService = pricelistService;
        this.pharmacyPricelistService = pharmacyPricelistService;
    }

    @GetMapping(value = "/getByMedication", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PricelistDTO> getByMedication(@RequestParam("code") Long code) throws ParseException {
        return pricelistService.getPricelistsByMedication(code);
    }


    @GetMapping(value = "/counselingCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    public double getPrice() throws Exception {
        return pharmacyPricelistService.counselingCost(100L);
    }

    @GetMapping(value = "/examinationCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    public double getExaminationCost() throws Exception {
        return pharmacyPricelistService.examinationCost(100L);
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
    @PharmacyAdminAuthorization
    public ResponseEntity<?> editPricelist(@RequestBody PricelistDTO pricelistDTO) {
        try {
            pricelistService.editPricelist(pricelistDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addPricelistEntity", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public boolean addPricelistEntity(@RequestBody PricelistDTO pricelistDTO) {
        return pricelistService.addMedicationToPricelist(pricelistDTO);
    }

    @PostMapping(value = "/deletePricelistEntity", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public ResponseEntity<?> deletePricelistEntity(@RequestBody Long pricelistId) {
        try {
            pricelistService.deletePricelistEntity(pricelistId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<PricelistDTO> getAllByPharmacy(@RequestParam("pharmacyId") Long pharmacyId)  {
        return PricelistMapper.mapToDTOS(pricelistService.getPricelistsByPharmacy(pharmacyId));
    }

}

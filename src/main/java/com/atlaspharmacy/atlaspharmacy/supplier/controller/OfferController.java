package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.OrderAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SupplierAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.exceptions.InsuficientFundsException;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OfferMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOfferService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {
    private final IOfferService offerService;
    @Autowired
    public OfferController(IOfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(value = "/findSuppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    @OrderAuthorization
    public @ResponseBody
    List<OfferDTO> getAllOffersBySuppllier(@RequestParam("id") Long id){
        return offerService.getOffersBySupplier(id);
    }

    @GetMapping(value = "/getByStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @OrderAuthorization
    public @ResponseBody
    List<OfferDTO> getAllOffersBySuppllierWithStatus(@RequestParam("id") Long id, @RequestParam("status") Long status){
        return offerService.getUsersOffersByStatus(status, id);
    }



    @PostMapping(value = "/update", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public ResponseEntity<?> updateOffer(@RequestBody OfferDTO offerDTO){

        try {
            offerService.editOffer(offerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public ResponseEntity<?> giveOffer(@RequestBody OfferDTO offerDTO) throws InsuficientFundsException, DueDateSoonException {

        try {
            offerService.giveOffer(offerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

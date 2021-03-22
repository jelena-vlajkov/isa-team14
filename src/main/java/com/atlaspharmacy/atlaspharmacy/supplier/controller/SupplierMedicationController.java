package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.SupplierAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.NewMedicationToStorageDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierStorageMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.exceptions.InsuficientFundsException;
import com.atlaspharmacy.atlaspharmacy.supplier.service.ISupplierStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/suppliersmedications", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierMedicationController {
    private final ISupplierStorageService supplierStorageService;

    @Autowired
    public SupplierMedicationController(ISupplierStorageService supplierStorageService) {
        this.supplierStorageService = supplierStorageService;
    }


    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public @ResponseBody
    SupplierStorageMedicationDTO getSupplierStorage(@RequestParam("id") Long id){
        return supplierStorageService.getSuppliersStorage(id);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public ResponseEntity<?> addDrug(@RequestBody NewMedicationToStorageDTO medicationToStorageDTO) throws InsuficientFundsException, DueDateSoonException {

            if( supplierStorageService.addNewMedicationToStorage(medicationToStorageDTO)!=null){
                return new ResponseEntity<>(HttpStatus.OK);

            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @SupplierAuthorization
    public ResponseEntity<?> updateDrugQuantity(@RequestBody NewMedicationToStorageDTO medicationToStorageDTO) throws InsuficientFundsException, DueDateSoonException {

        if( supplierStorageService.updateQuantity(medicationToStorageDTO)!=null){
            return new ResponseEntity<>(HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }

}

package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.OrderAuthorization;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.MedicationInOrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IMedicationInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/medicationInOrder", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicationInOrderController {
    private final IMedicationInOrderService medicationInOrderService;

    @Autowired
    public MedicationInOrderController(IMedicationInOrderService medicationInOrderService) {
        this.medicationInOrderService = medicationInOrderService;
    }

    @PostMapping(value = "/addMedicationInOrder", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMedicationInOrder(@RequestBody List<MedicationInOrderDTO> dtos) {
        try {
            medicationInOrderService.addMedicationInOrder(dtos);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.atlaspharmacy.atlaspharmacy.reservations.controller;

import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.reservations.service.impl.DrugReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrugReservationController {
    private final IDrugReservationService drugReservationService;

    @Autowired
    public DrugReservationController(IDrugReservationService drugReservationService) {
        this.drugReservationService = drugReservationService;
    }



}

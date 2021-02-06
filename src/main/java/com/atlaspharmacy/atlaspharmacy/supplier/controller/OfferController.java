package com.atlaspharmacy.atlaspharmacy.supplier.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.SupplierAuthorization;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OfferMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @SupplierAuthorization
    public @ResponseBody
    List<OfferDTO> getAllOffersBySuppllier(@RequestParam("id") Long id){
        return OfferMapper.mapToListDTOS(offerService.getOffersBySupplier(id));
    }
}

package com.atlaspharmacy.atlaspharmacy.pharmacy.controller;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/pricelist",produces = MediaType.APPLICATION_JSON_VALUE)
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

}

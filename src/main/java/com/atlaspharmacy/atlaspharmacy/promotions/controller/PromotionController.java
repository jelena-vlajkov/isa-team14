package com.atlaspharmacy.atlaspharmacy.promotions.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PricelistMapper;
import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.mapper.PromotionMapper;
import com.atlaspharmacy.atlaspharmacy.promotions.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "promotion")
public class PromotionController {
    private final IPromotionService _promotionService;

    @Autowired
    public PromotionController(IPromotionService promotionService) {
        _promotionService = promotionService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public ResponseEntity<?> addPromotion(@RequestBody PromotionDTO promotionDTO) throws IOException, MessagingException {
        _promotionService.addPromotion(promotionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getPromotionsByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PromotionDTO> getPromotionsByPharmacy(@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        return PromotionMapper.MapToListDTOS(_promotionService.getPromotionsByPharmacy(pharmacyId));
    }
}

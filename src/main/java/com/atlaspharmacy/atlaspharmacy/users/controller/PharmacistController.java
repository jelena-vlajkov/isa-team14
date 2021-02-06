package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacistMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value="/pharmacist")
public class PharmacistController {
    private final IPharmacistService pharmacistService;

    @Autowired
    public PharmacistController(IPharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping(value = "/getPharmacistToComplain", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> getPharmacitsToComplain(@RequestParam("id") Long id) throws ParseException {
        return PharmacistMapper.mapToListDTOS(pharmacistService.getAllPharmacistsToComplain(id));
    }

}

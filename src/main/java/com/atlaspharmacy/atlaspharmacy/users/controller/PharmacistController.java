package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/pharmacists")
public class PharmacistController {

    private IPharmacistService _pharmacistService;

    @Autowired
    public PharmacistController(IPharmacistService _pharmacistService) {
        this._pharmacistService =_pharmacistService;
    }

    @GetMapping(value = "/getByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Pharmacist> getByPharmacy(@RequestParam("id") Long id) throws ParseException {
        return _pharmacistService.findByPharmacy(id);
    }
}

package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class PharmacyAdminController {
    private IPharmacyAdminService _pharmacyAdminService;

    @Autowired
    public PharmacyAdminController(IPharmacyAdminService _pharmacyAdminService) {
        this._pharmacyAdminService = _pharmacyAdminService;
    }


    @GetMapping(value = "/getPharmacyByAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Pharmacy getPharmacyByPharmacyAdmin(@RequestParam("id") Long id) throws ParseException {
        return _pharmacyAdminService.getPharmacyByPharmacyAdmin(id);
    }
}


package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
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

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/pharmacist")
public class PharmacistController {
    private final IPharmacistService pharmacistService;

    @Autowired
    public PharmacistController(IPharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping(value = "/getPharmacistToComplain", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<PharmacistDTO> getPharmacitsToComplain(@RequestParam("id") Long id) throws ParseException {
        return PharmacistMapper.mapToListDTOS(pharmacistService.getAllPharmacistsToComplain(id));
    }


    @GetMapping(value = "/getByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> getByPharmacy(@RequestParam("id") Long id) throws ParseException {
        return  PharmacistMapper.mapToListDTOS(pharmacistService.findByPharmacy(id));
    }


    @GetMapping(value = "/searchPharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> searchPharmacists(@RequestParam("searchInput") String searchInput){
        return PharmacistMapper.mapToListDTOS(pharmacistService.searchPharmacists(searchInput));
    }
    
    @GetMapping(value = "/searchPharmacistsByPharmacyAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> searchPharmacyByPharmacyAdmin(@RequestParam("searchInput") String searchInput,Long pharmacyId){
        return PharmacistMapper.mapToListDTOS(pharmacistService.searchPharmacistsByPharmacyAdmin(searchInput,pharmacyId));
    }

    @GetMapping(value="/filterPharmacistsByPharmacy",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> filterPharmacistsByPharmacy(@RequestParam("pharmacists") List<PharmacistDTO> pharmacists,
                                                    @RequestParam("pharmacyId") String pharmacyId) {
        return pharmacistService.filterPharmacistsByPharmacy(pharmacists,pharmacyId);
    }

    @GetMapping(value="/filterPharmacistsByGrade",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> filterPharmacistsByGrade(@RequestParam("pharmacists") List<PharmacistDTO> pharmacists,
                                                    @RequestParam("grade") Double grade) {
        return pharmacistService.filterPharmacistsByGrade(pharmacists,grade);
    }
}

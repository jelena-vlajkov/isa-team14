package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacistMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<PharmacistDTO> getByPharmacy(@RequestParam("id") Long id) throws ParseException {
        return  PharmacistMapper.mapToListDTOS(pharmacistService.findByPharmacy(id));
    }

    
    @GetMapping(value = "/searchPharmacistsByPharmacyAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> searchPharmacyByPharmacyAdmin(@RequestParam("searchInput") String searchInput,Long pharmacyId){
        return PharmacistMapper.mapToListDTOS(pharmacistService.searchPharmacistsByPharmacyAdmin(searchInput,pharmacyId));
    }

    @PostMapping(value = "/filterPharmacistsByGrade", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<PharmacistDTO> filterPharmacistsByGrade(@RequestBody List<PharmacistDTO> pharmacists, @RequestParam("grade") int grade) throws ParseException {
        return pharmacistService.filterPharmacistsByGrade(pharmacists,grade);
    }

    @PostMapping(value = "/filterPharmacistsByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<PharmacistDTO> filterPharmacistByPharmacy(@RequestBody List<PharmacistDTO> pharmacists,@RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
        return pharmacistService.filterPharmacistsByPharmacy(pharmacists,pharmacyId);
    }

    @GetMapping(value = "/searchPharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<PharmacistDTO> searchPharmacists(@RequestParam("pharmacyId")Long pharmacyId,@RequestParam("searchInput") String searchInput){
        return PharmacistMapper.mapToListDTOS(pharmacistService.searchPharmacists(pharmacyId,searchInput));
    }

    @PostMapping(value = "/registerPharmacist",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public PharmacistDTO registerPharmacist(@RequestBody PharmacistDTO pharmacistDTO) throws Exception {
        return PharmacistMapper.mapPharmacistToDTO(pharmacistService.registerPharmacist(pharmacistDTO));
    }

    @GetMapping(value = "/getById",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PharmacistDTO getById(@RequestParam("pharmacistId") Long pharmacistId) throws Exception {
        return PharmacistMapper.mapPharmacistToDTO(pharmacistService.findById(pharmacistId));
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PharmacistDTO> getAll(){
        return PharmacistMapper.mapToListDTOS(pharmacistService.getAll());
    }

    @PostMapping(value = "/deletePharmacist")
    @PharmacyAdminAuthorization
    public ResponseEntity<?> deletePharmacistFromPharmacy(@RequestParam("pharmacistId") Long pharmacistId,@RequestParam("pharmacyId") Long pharmacyId){
        boolean successful;
        try {
            successful=pharmacistService.deletePharmacist(pharmacistId,pharmacyId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //ako vrati true,dermatolog nema zakazano nista i moze se obrisati,ako ne,ne sme se obrisatiiii,moze ovako?
        if(successful){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @GetMapping(value = "/findByRangeAndPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<PharmacistDTO> findByRangeAndPharmacy(@RequestParam("pharmacyId") Long pharmacyId, @RequestParam("start") String date,
                                               @RequestParam("end") String end) throws Exception {
        Date start = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(date);
        Date endRange = new SimpleDateFormat("dd.MM.yyyy. HH:mm").parse(end);

        return pharmacistService.findByRangeAndPharmacy(start, endRange, pharmacyId);
    }

    @GetMapping(value = "/findPharmacistsForPatientGrading", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public @ResponseBody
    List<PharmacistDTO> findForPatientGrading(@RequestParam("patientId") Long patientId) {
        return pharmacistService.findForPatientGrading(patientId);
    }
}

package com.atlaspharmacy.atlaspharmacy.membershipinfo.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.MedicationAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.ComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.DermatologistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacyComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IComplaintService;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl.ComplaintService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/complaint",produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {
    private final IComplaintService complaintService;
    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<?> addComplaint(@RequestBody ComplaintDTO complaintDTO){
        complaintService.sendComplaint(complaintDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value="/getUnanswered",produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> getAllUnansweredComplaints(){

        List<ComplaintDTO> dtos = complaintService.getAllUnasweredComplaints();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value="/getUnansweredPharmacy",produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> getAllUnansweredPharmacyComplaints(){

        List<PharmacyComplaintDTO> dtos = complaintService.getPharmacyComplaints();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value="/getUnansweredDermatologist",produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> getAllUnansweredPharmacistComplaints(){

        List<DermatologistComplaintDTO> dtos = complaintService.getDermatologistComplaints();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value="/getUnansweredPharmacist",produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> getAllUnansweredDermatologistComplaints(){

        List<PharmacistComplaintDTO> dtos = complaintService.getPharmacistComplaints();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}

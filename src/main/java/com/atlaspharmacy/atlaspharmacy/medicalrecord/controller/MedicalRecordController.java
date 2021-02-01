package com.atlaspharmacy.atlaspharmacy.medicalrecord.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.MedicalRecordAuthorization;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.mapper.MedicalRecordMapper;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PenaltyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "medicalRecord", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {

    private final IMedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(IMedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    @MedicalRecordAuthorization
    public @ResponseBody
    MedicalRecordDTO getMedicalRecord(@RequestBody Long patientId) {
        return MedicalRecordMapper.mapToDto(medicalRecordService.getByPatientId(patientId));

    }

}

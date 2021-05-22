package com.atlaspharmacy.atlaspharmacy.medicalrecord.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.MedicalRecordAuthorization;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicalRecordDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicationToRecommendDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.mapper.MedicalRecordMapper;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.IMedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl.MedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PenaltyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    MedicalRecordDTO getMedicalRecord(@RequestParam Long patientId) {
        return MedicalRecordMapper.mapToDto(medicalRecordService.getByPatientId(patientId));

    }

    @PostMapping(value = "/addPatientIngredient", consumes = MediaType.APPLICATION_JSON_VALUE)
    @MedicalRecordAuthorization
    public ResponseEntity<?> addPatientIngredients(@RequestBody MedicalRecordDTO dto){

        try {
            medicalRecordService.addPatientIngredients(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/recommendMedicationByPatient", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @EmployeeAuthorization
    public List<MedicationToRecommendDTO> getMedicationsByPatient(@RequestParam Long patientId, @RequestParam Long pharmacyId) {
        return medicalRecordService.recommendMedicationForPatient(patientId, pharmacyId);
    }

    @GetMapping(value = "/getPatientIngredients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @MedicalRecordAuthorization
    public  ResponseEntity<List<Ingredient>>  getPatientIngredient(@PathVariable Long id) {
        return new ResponseEntity<>( medicalRecordService.getPatientIngredient(id), HttpStatus.OK);
    }

}

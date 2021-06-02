package com.atlaspharmacy.atlaspharmacy.grade.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.MedicationGrade;
import com.atlaspharmacy.atlaspharmacy.grade.mapper.GradeMapper;
import com.atlaspharmacy.atlaspharmacy.grade.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/grade", produces = MediaType.APPLICATION_JSON_VALUE)
public class GradeController {
    private final IGradeService gradeService;

    @Autowired
    GradeController(IGradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping(value = "/getAllGradesByPatient", produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public List<GradeDTO> getGradesByPatient(@RequestParam("patientId") Long patientId){
        List<Grade> grades = gradeService.findByPatient(patientId);
        if (grades.isEmpty()) {
            return new ArrayList<>();
        }

        List<GradeDTO> gradeDTOs = new ArrayList<>();
        for (Grade grade : grades) {
            gradeDTOs.add(GradeMapper.gradeToDto(grade));
        }
        return gradeDTOs;
    }

    @PostMapping(value = "/newMedicationGrade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<GradeDTO> newMedicationGrade(@RequestBody GradeDTO gradeDto) {

        Grade grade  = gradeService.newMedicationGrade(gradeDto);
        if (grade == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GradeMapper.gradeToDto(grade), HttpStatus.OK);
    }

    @PostMapping(value = "/newPharmacyGrade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<GradeDTO> newPharmacyGrade(@RequestBody GradeDTO gradeDto) {

        Grade grade  = gradeService.newPharmacyGrade(gradeDto);
        if (grade == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GradeMapper.gradeToDto(grade), HttpStatus.OK);
    }

    @PostMapping(value = "/newPharmacistGrade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<GradeDTO> newPharmacistGrade(@RequestBody GradeDTO gradeDto) {

        Grade grade  = gradeService.newPharmacistGrade(gradeDto);
        if (grade == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GradeMapper.gradeToDto(grade), HttpStatus.OK);
    }

    @PostMapping(value = "/updateGivenGrade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<GradeDTO> updateGivenGrade(@RequestBody GradeDTO gradeDTO) {

        Grade grade  = gradeService.updateGivenGrade(gradeDTO.getId(), gradeDTO.getGrade());
        if (grade == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GradeMapper.gradeToDto(grade), HttpStatus.OK);
    }


}

package com.atlaspharmacy.atlaspharmacy.grade.service.impl;

import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.MedicationGrade;
import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;
import com.atlaspharmacy.atlaspharmacy.grade.mapper.GradeMapper;
import com.atlaspharmacy.atlaspharmacy.grade.repository.GradeRepository;
import com.atlaspharmacy.atlaspharmacy.grade.service.IGradeService;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.MedicationServiceImpl;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService implements IGradeService {

    private final GradeRepository gradeRepository;
    private final MedicationServiceImpl medicationService;
    private final PatientService patientService;
    private final MedicationRepository medicationRepository;

    public GradeService(GradeRepository gradeRepository, MedicationServiceImpl medicationService, PatientService patientService, MedicationRepository medicationRepository) {
        this.gradeRepository = gradeRepository;
        this.medicationService = medicationService;
        this.patientService = patientService;
        this.medicationRepository = medicationRepository;
    }


    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade findById(Long gradeId) {
        return gradeRepository.findById(gradeId).get();
    }

    @Override
    public List<Grade> findByPatient(Long patientId) {
        return gradeRepository.findByPatient(patientId);
    }

    @Override
    public Grade newMedicationGrade(GradeDTO dto) {

        MedicationGrade medicationGrade = (MedicationGrade) GradeMapper.dtoToGrade(dto);

        if (isPatientGradeMedication(dto) == false) {
            Medication medication = medicationService.getById(dto.getMedicationId());
            medicationGrade.setPatient(patientService.findById(dto.getPatientId()));
            medicationGrade.setMedication(medication);
            medicationGrade.setType(GradeType.Values.MedicationGrade);

            setNewMedicationAverageGrade(dto.getGrade(), medicationGrade.getMedication().getId());
            return gradeRepository.save(medicationGrade);


        }

        return null;
    }

    @Override
    public Grade updateGivenGrade(Long gradeId, int newGrade) {
        Grade grade = gradeRepository.findById(gradeId).get();

        if (grade == null) {
            return  null;
        }

        if (grade.getType().equals(GradeType.Values.MedicationGrade)) {
            MedicationGrade mg = (MedicationGrade) grade;
            setNewMedicationAverageGrade(newGrade, mg.getMedication().getId());
        }

        grade.setGrade(newGrade);
        return gradeRepository.save(grade);

    }

    public boolean isPatientGradeMedication(GradeDTO dto) {

        List<Grade> allGrades = findAll();

        for (Grade g : allGrades) {
            if(g.getType().equals(dto.getGradeType()) && g.getType().equals(GradeType.Values.MedicationGrade) ) {
                MedicationGrade medicationGrade = (MedicationGrade) g;
                if(medicationGrade.getMedication().getId().equals(dto.getMedicationId()) &&
                        medicationGrade.getPatient().getId().equals(dto.getPatientId())) {
                    return true;
                }
            }
        }

        return false;
    }

    private void setNewMedicationAverageGrade(int grade, Long medicationId) {
        int gradeSum = 0;
        int grades = 0;

        List<Grade> allMedGrades = findAll().stream().filter(m -> m.getType().equals(GradeType.Values.MedicationGrade)).collect(Collectors.toList());

        for (Grade g : allMedGrades) {
            MedicationGrade mg = (MedicationGrade) g;
            if(mg.getMedication().getId().equals(medicationId)) {
                gradeSum += mg.getGrade();
                grades ++;
            }
        }

        Medication medication = medicationService.getById(medicationId);

        if(medication.getGrade() == 0) {
            medication.setGrade(1.0);
        }

        double averageGrade = (double) (grade + gradeSum) / (grades + 1);
        medication.setGrade(averageGrade);

        medicationRepository.save(medication);


    }


}

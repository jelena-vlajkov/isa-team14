package com.atlaspharmacy.atlaspharmacy.grade.service.impl;

import com.atlaspharmacy.atlaspharmacy.grade.DTO.GradeDTO;
import com.atlaspharmacy.atlaspharmacy.grade.domain.*;
import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;
import com.atlaspharmacy.atlaspharmacy.grade.mapper.GradeMapper;
import com.atlaspharmacy.atlaspharmacy.grade.repository.GradeRepository;
import com.atlaspharmacy.atlaspharmacy.grade.service.IGradeService;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.MedicationServiceImpl;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
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
    private final PharmacyService pharmacyService;
    private final PharmacyRepository pharmacyRepository;
    private final PharmacistRepository pharmacistRepository;
    private final DermatologistRepository dermatologistRepository;

    public GradeService(GradeRepository gradeRepository, MedicationServiceImpl medicationService, PatientService patientService, MedicationRepository medicationRepository, PharmacyService pharmacyService, PharmacyRepository pharmacyRepository, PharmacistRepository pharmacistRepository, DermatologistRepository dermatologistRepository) {
        this.gradeRepository = gradeRepository;
        this.medicationService = medicationService;
        this.patientService = patientService;
        this.medicationRepository = medicationRepository;
        this.pharmacyService = pharmacyService;
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.dermatologistRepository = dermatologistRepository;
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

            setNewMedicationAverageGrade(false, dto.getGrade(), medicationGrade.getMedication().getId(), dto.getId());
            return gradeRepository.save(medicationGrade);


        }

        return null;
    }

    @Override
    public Grade newPharmacyGrade(GradeDTO dto) {
        PharmacyGrade pharmacyGrade = (PharmacyGrade) GradeMapper.dtoToGrade(dto);

        if(isPatientGradePharmacy(dto) == false) {
            Pharmacy pharmacy = pharmacyService.getById(dto.getPharmacyId());
            pharmacyGrade.setPatient(patientService.findById(dto.getPatientId()));
            pharmacyGrade.setPharmacy(pharmacy);
            pharmacyGrade.setType(GradeType.Values.PharmacyGrade);

            Pharmacy p  = setNewPharmacyAverageGrade(false, dto.getGrade(), pharmacyGrade.getPharmacy().getId(), dto.getId());
            return gradeRepository.save(pharmacyGrade);
        }

        return null;
    }

    @Override
    public Grade newPharmacistGrade(GradeDTO dto) {
        PharmacistGrade pharmacistGrade = (PharmacistGrade) GradeMapper.dtoToGrade(dto);
        if(!isPratienGradePharmacist(dto)) {
            Pharmacist pharmacist = pharmacistRepository.findById(dto.getPharmacistId()).get();
            pharmacistGrade.setPatient(patientService.findById(dto.getPatientId()));
            pharmacistGrade.setPharmacist(pharmacist);
            pharmacistGrade.setType(GradeType.Values.PharmacistGrade);

            setNewPharmacistAverageGrade(false, dto.getGrade(), pharmacistGrade.getPharmacist().getId(), dto.getId());
            return gradeRepository.save(pharmacistGrade);
        }
        return null;

    }

    @Override
    public Grade newDermatologistGrade(GradeDTO dto) {
        DermatologistGrade dermatologistGrade = (DermatologistGrade) GradeMapper.dtoToGrade(dto);
        if(!isPatientGradeDermatologist(dto)) {
            Dermatologist dermatologist = dermatologistRepository.findById(dto.getDermatologistId()).get();
            dermatologistGrade.setPatient(patientService.findById(dto.getPatientId()));
            dermatologistGrade.setDermatologist(dermatologist);
            dermatologistGrade.setType(GradeType.Values.DermatologistGrade);

            setNewDermatologistGrad(false, dto.getGrade(), dermatologistGrade.getDermatologist().getId(), dto.getId());
            return gradeRepository.save(dermatologistGrade);

        }

        return null;
    }

    @Override
    public Grade updateGivenGrade(Long gradeId, int newGrade) {
        Grade grade = gradeRepository.findById(gradeId).get();
        grade.setGrade(newGrade);
        gradeRepository.save(grade);

        if (grade == null) {
            return  null;
        }

        if (grade.getType().equals(GradeType.Values.MedicationGrade)) {
            MedicationGrade mg = (MedicationGrade) grade;
            setNewMedicationAverageGrade(true, newGrade, mg.getMedication().getId(), gradeId);
        }

        if (grade.getType().equals(GradeType.Values.PharmacyGrade)) {
            PharmacyGrade mg = (PharmacyGrade) grade;
            Pharmacy p  = setNewPharmacyAverageGrade(true, newGrade, mg.getPharmacy().getId(), gradeId);
        }

        if (grade.getType().equals(GradeType.Values.PharmacistGrade)) {
            PharmacistGrade pg = (PharmacistGrade) grade;
            setNewPharmacistAverageGrade(true, newGrade, pg.getPharmacist().getId(), gradeId);
        }

        if (grade.getType().equals(GradeType.Values.DermatologistGrade)) {
            DermatologistGrade dg = (DermatologistGrade) grade;
            setNewDermatologistGrad(true, newGrade, dg.getDermatologist().getId(), gradeId);
        }

        return grade;

    }

    public boolean isPatientGradeMedication(GradeDTO dto) {

        List<Grade> allGrades = findAll();

        for (Grade g : allGrades) {
            if(g.getType().equals(dto.getGradeType()) && g.getType().equals(GradeType.Values.MedicationGrade)) {
                MedicationGrade medicationGrade = (MedicationGrade) g;
                if(medicationGrade.getMedication().getId().equals(dto.getMedicationId()) &&
                        medicationGrade.getPatient().getId().equals(dto.getPatientId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPatientGradePharmacy(GradeDTO dto) {
        List<Grade> allGrades = findAll();
        for (Grade g : allGrades) {
            if(g.getType().equals(dto.getGradeType()) && g.getType().equals(GradeType.Values.PharmacyGrade)) {
                PharmacyGrade pharmacyGrade = (PharmacyGrade) g;
                if(pharmacyGrade.getPharmacy().getId().equals(dto.getPharmacyId()) &&
                pharmacyGrade.getPatient().getId().equals(dto.getPatientId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPratienGradePharmacist(GradeDTO dto) {
        List<Grade> allGradesPharmacist = findAll();
        for (Grade g : allGradesPharmacist) {
            if(g.getType().equals(dto.getGradeType()) && g.getType().equals(GradeType.Values.PharmacistGrade)) {
                PharmacistGrade pharmacistGrade = (PharmacistGrade) g;
                if(pharmacistGrade.getPharmacist().getId().equals(dto.getPharmacistId()) &&
                pharmacistGrade.getPatient().getId().equals(dto.getPatientId())) {
                    return  true;
                }
            }
        }
        return false;
    }

    public boolean isPatientGradeDermatologist(GradeDTO dto) {
        List<Grade> allGradesPharmacist = findAll();
        for (Grade g : allGradesPharmacist) {
            if(g.getType().equals(dto.getGradeType()) && g.getType().equals(GradeType.Values.DermatologistGrade)) {
                DermatologistGrade dermatologistGrade = (DermatologistGrade) g;
                if(dermatologistGrade.getDermatologist().getId().equals(dto.getDermatologistId()) &&
                        dermatologistGrade.getPatient().getId().equals(dto.getPatientId())) {
                    return  true;
                }
            }
        }
        return false;
    }

    private void setNewMedicationAverageGrade(boolean update, int grade, Long medicationId, Long oldGradeId) {
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

        double averageGrade;

        if (!update) {
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }else{
            Grade oldGrade = gradeRepository.findById(oldGradeId).get();
            gradeSum -= oldGrade.getGrade();
            grades = grades - 1;
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }


        medication.setGrade(averageGrade);

        medicationRepository.save(medication);

    }

    private Pharmacy  setNewPharmacyAverageGrade(boolean update, int grade, Long pharmacyId, Long oldGradeId) {
        int gradeSum = 0;
        int grades = 0;
        List<Grade> allPharmGrades = findAll().stream().filter(m -> m.getType().equals(GradeType.Values.PharmacyGrade)).collect(Collectors.toList());

        for (Grade g : allPharmGrades) {
            PharmacyGrade mg = (PharmacyGrade) g;
            if(mg.getPharmacy().getId().equals(pharmacyId)) {
                gradeSum += mg.getGrade();
                grades ++;
            }
        }

        Pharmacy pharmacy = pharmacyService.getById(pharmacyId);


        if(pharmacy.getAverageGrade() == 0) {
            pharmacy.setAverageGrade(1.0);
        }

        double averageGrade;
        if (!update) {

            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }else{
            Grade odlPharmacyGrade = gradeRepository.findById(oldGradeId).get();
            gradeSum -= odlPharmacyGrade.getGrade();
            grades = grades - 1;
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }

        pharmacy.setAverageGrade(averageGrade);

        pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    private void setNewPharmacistAverageGrade(boolean update, int grade, Long pharmacistId, Long oldGradeId) {
        int gradeSum = 0;
        int grades = 0;
        List<Grade> allPharmGrades = findAll().stream().filter(m -> m.getType().equals(GradeType.Values.PharmacistGrade)).collect(Collectors.toList());

        for (Grade g : allPharmGrades) {
            PharmacistGrade mg = (PharmacistGrade) g;
            if(mg.getPharmacist().getId().equals(pharmacistId)) {
                gradeSum += mg.getGrade();
                grades ++;
            }
        }

        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).get();

        if (pharmacist.getAverageGrade() == 0) {
            pharmacist.setAverageGrade(1.0);
        }

        double averageGrade;
        if (!update) {
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }else{
            Grade odlPharmacyGrade = gradeRepository.findById(oldGradeId).get();
            gradeSum -= odlPharmacyGrade.getGrade();
            grades = grades - 1;
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }

        pharmacist.setAverageGrade(averageGrade);

        pharmacistRepository.save(pharmacist);

    }

    public void setNewDermatologistGrad(boolean update, int grade, Long dermatologisId, Long oldGradeId) {
        int gradeSum = 0;
        int grades = 0;
        List<Grade> allPharmGrades = findAll().stream().filter(m -> m.getType().equals(GradeType.Values.DermatologistGrade)).collect(Collectors.toList());

        for (Grade g : allPharmGrades) {
            DermatologistGrade mg = (DermatologistGrade) g;
            if(mg.getDermatologist().getId().equals(dermatologisId)) {
                gradeSum += mg.getGrade();
                grades ++;
            }
        }

        Dermatologist dermatologist = dermatologistRepository.findById(dermatologisId).get();

        if (dermatologist.getAverageGrade() == 0) {
            dermatologist.setAverageGrade(1.0);
        }

        double averageGrade;
        if (!update) {
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }else{
            Grade odlPharmacyGrade = gradeRepository.findById(oldGradeId).get();
            gradeSum -= odlPharmacyGrade.getGrade();
            grades = grades - 1;
            averageGrade = (double) (grade + gradeSum) / (grades + 1);
        }

        dermatologist.setAverageGrade(averageGrade);

        dermatologistRepository.save(dermatologist);
    }

    @Override
    public void deleteGrade(Long deleteId, String type) {

        if(type.equals(GradeType.Values.PharmacyGrade)) {
            List<Grade> allPharmGrades = findAll().stream().
                    filter(m -> m.getType().equals(GradeType.Values.PharmacyGrade)).collect(Collectors.toList());

            for (Grade g : allPharmGrades) {
                PharmacyGrade grade = (PharmacyGrade) g;
                if(grade.getPharmacy().getId().equals(deleteId)) {
                    gradeRepository.deleteById(grade.getId());
                }
             }

        }

        if(type.equals(GradeType.Values.PharmacistGrade)) {
            List<Grade> allPharmGrades = findAll().stream().
                    filter(m -> m.getType().equals(GradeType.Values.PharmacistGrade)).collect(Collectors.toList());

            for (Grade g : allPharmGrades) {
                PharmacistGrade grade = (PharmacistGrade) g;
                if(grade.getPharmacist().getId().equals(deleteId)) {
                    gradeRepository.deleteById(grade.getId());
                }
            }

        }

        if(type.equals(GradeType.Values.DermatologistGrade)) {
            List<Grade> allPharmGrades = findAll().stream().
                    filter(m -> m.getType().equals(GradeType.Values.DermatologistGrade)).collect(Collectors.toList());

            for (Grade g : allPharmGrades) {
                DermatologistGrade grade = (DermatologistGrade) g;
                if(grade.getDermatologist().getId().equals(deleteId)) {
                    gradeRepository.deleteById(grade.getId());
                }
            }

        }

        if(type.equals(GradeType.Values.MedicationGrade)) {
            List<Grade> allPharmGrades = findAll().stream().
                    filter(m -> m.getType().equals(GradeType.Values.MedicationGrade)).collect(Collectors.toList());

            for (Grade g : allPharmGrades) {
                MedicationGrade grade = (MedicationGrade) g;
                if(grade.getMedication().getId().equals(deleteId)) {
                    gradeRepository.deleteById(grade.getId());
                }
            }

        }
    }



}

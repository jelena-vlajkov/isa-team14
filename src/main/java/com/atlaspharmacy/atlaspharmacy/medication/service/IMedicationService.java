package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import java.text.ParseException;
import java.util.List;

public interface IMedicationService {
    MedicationDTO findById(Long id);
    List<MedicationDTO> findAll();
    List<MedicationDTO> findAllPatientsMedications(Long patientId);
    void createMedication(MedicationDTO medicationDTO) throws Exception;
    Medication modifyMedication(Long id, MedicationDTO medicationDTO) throws Exception;
    void deleteMedication(Long id, MedicationDTO medicationDTO) throws Exception;
    void saveMedication(MedicationDTO medicationDTO) throws Exception;
    void addMedicationToPharmacy(MedicationDTO medicationDTO, Long pharmacyID) throws Exception;
    boolean medicationExistsInPharmacy(Long drugID, Long pharmacyID);
    void addMedicationToPharmacy(Medication medication, Long pharmacyID, Integer amount) throws Exception;
    List<MedicationDTO> findAllMedicationsNotInPharmacy(Long pharmacyID) throws Exception;
    List<IngredientDTO> findMedicationsIngredients(Medication medication) throws Exception;
    List<MedicationDTO> findByName(String name) throws ParseException;
    List<MedicationDTO> findByType(Long type) throws ParseException;
    List<MedicationDTO> findByForm(Long form) throws ParseException;
    List<MedicationDTO> findByKind(Long kind) throws ParseException;
    List<MedicationDTO> findByPrescribing(Long prescribing) throws ParseException;
    Medication getById(Long medicationId);
    List<MedicationDTO> findForPatientGrading(Long patientId);

}

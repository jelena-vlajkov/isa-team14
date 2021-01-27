package com.atlaspharmacy.atlaspharmacy.medication.service.implementations;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.repository.IMedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicationServiceImpl implements IMedicationService {

    @Autowired
    private IMedicationRepository medicationRepository;

    final private static String EXCEPTION = "Exception in Medication Service Implementation method:";
    final private static String DOES_NOT_EXIST = "Medication with Id does not exist";
    final private static String FAIL = "execution failed";


    @Override
    public MedicationDTO findById(Long id) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        if(medication == null){
            throw  new NoSuchElementException(EXCEPTION + " findById" + DOES_NOT_EXIST);
        }

        return  MedicationDTO.convertToMedicationDTO(medication);
    }

    @Override
    public List<MedicationDTO> findAll() {
        List<Medication> medications = medicationRepository.findAll();

        return convertToDTOS(medications);
    }

    @Override
    public List<MedicationDTO> findAllPatientsMedications(Long patientId) {
        return null;
    }

    @Override
    public void createMedication(MedicationDTO medicationDTO) throws Exception {
        Medication medication = new Medication();
        try {
            this.saveMedication(medication,medicationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new Exception(EXCEPTION + "createNewMedication " + FAIL);
        }
    }

    @Override
    public void modifyMedication(Long id, MedicationDTO medicationDTO) throws Exception {
        Medication medication = medicationRepository.findById(id).orElse(null);

        if(medication == null){
            throw new NoSuchElementException(EXCEPTION + "modifyMedication" + FAIL);
        }

        try {
            this.saveMedication(medication, medicationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(EXCEPTION + "modifyMedication " + FAIL);
        }
    }
    //ovo mora ovde za brisanja
    @Transactional
    @Override
    public void deleteMedication(Long id, MedicationDTO medicationDTO) throws Exception {

    }



    @Override
    public boolean medicationExistsInPharmacy(Long drugID, Long pharmacyID) {
        return false;
    }

    @Override
    public void addMedicationToPharmacy(Medication medication, Long pharmacyID, Integer amount) throws Exception {

    }

    @Override
    public void addMedicationToPharmacy(MedicationDTO medicationDTO, Long pharmacyID) throws Exception {

    }

    @Override
    public List<MedicationDTO> findAllMedicationsNotInPharmacy(Long pharmacyID) throws Exception {
        return null;
    }

    @Override
    public void saveMedication(Medication medication, MedicationDTO medicationDTO) throws Exception {
        MedicationDTO.convertToMedication(medication,medicationDTO);
        for(Long id : medicationDTO.getSubstituteMedication()){
            try{
                medication.setSubstituteMedication(new ArrayList<>());
                medication.getSubstituteMedication().add(medicationRepository.findById(id).orElse(null));
            }catch (Exception e){
                e.printStackTrace();
                throw new Exception(EXCEPTION + "saveMedication" + DOES_NOT_EXIST);
            }
        }
        try{
            medicationRepository.save(medication);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(EXCEPTION + "saveMedication " + FAIL);
        }
    }


    private List<MedicationDTO> convertToDTOS(List<Medication> medications){
        List<MedicationDTO> dtos = new ArrayList<>();
        Long amount = null;
        for(Medication m : medications){
            MedicationDTO dto = MedicationDTO.convertToMedicationDTO(m);
            dtos.add(dto);
        }

        return dtos;
    }




}

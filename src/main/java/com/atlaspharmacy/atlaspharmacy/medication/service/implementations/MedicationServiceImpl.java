package com.atlaspharmacy.atlaspharmacy.medication.service.implementations;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.repository.IIngredientRepository;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements IMedicationService {

    private final MedicationRepository _medicationRepository;
    private final IIngredientRepository _ingredientRepository;

    final private static String EXCEPTION = "Exception in Medication Service Implementation method:";
    final private static String DOES_NOT_EXIST = "Medication with Id does not exist";
    final private static String FAIL = "execution failed";

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository, IIngredientRepository ingredientRepository){
        this._medicationRepository = medicationRepository;
        this._ingredientRepository = ingredientRepository;
    }

    @Override
    public MedicationDTO findById(Long id) {
        Medication medication = _medicationRepository.findById(id).orElse(null);
        if(medication == null){
            throw  new NoSuchElementException(EXCEPTION + " findById" + DOES_NOT_EXIST);
        }
        return MedicationMapper.convertToMedicationDTO(medication);
       // return  MedicationDTO.convertToMedicationDTO(medication);
    }
    public Medication getById(Long id){
        return _medicationRepository.findById(id).get();
    }
    @Override
    public List<MedicationDTO> findAll() {
        List<Medication> medications = _medicationRepository.findAll();

        return MedicationMapper.convertToDTOS(medications);
    }

    @Override
    public List<MedicationDTO> findAllPatientsMedications(Long patientId) {
        return null;
    }

    @Override
    public void createMedication(MedicationDTO medicationDTO) throws Exception {
        if (medicationExistsInSystem(medicationDTO)) {
            throw new Exception("Medication already exists in system!");

        }else{
            try {
                this.saveMedication(medicationDTO);
            } catch (Exception e) {
                e.printStackTrace();
                throw  new Exception(EXCEPTION + "createNewMedication " + FAIL);
            }
        }

    }
    private boolean medicationExistsInSystem(MedicationDTO medicationDto){
        for(Medication m : _medicationRepository.findAll()){
            if (checkSameMedication(m, medicationDto)) {
                return true;
            }
        }

        return false;
    }
    private boolean checkSameMedication(Medication m , MedicationDTO dto){
        return m.getName().equalsIgnoreCase(dto.getName()) && m.getDrugType().equals(dto.getDrugType()) && m.getDrugForm().equals(dto.getDrugForm())
                && m.getDrugKind().equals(dto.getDrugKind()) && m.getProducer().equalsIgnoreCase(dto.getProducer()) && m.getTypeOfPrescribing().equals(dto.getTypeOfPrescribing())
                && m.getDailyDose().equals(dto.getDailyDose()) && m.getDosage().equals(dto.getDosage());
    }
    @Override
    public void modifyMedication(Long id, MedicationDTO medicationDTO) throws Exception {
        Medication medication = _medicationRepository.findById(id).orElse(null);

        if(medication == null){
            throw new NoSuchElementException(EXCEPTION + "modifyMedication" + FAIL);
        }

        try {
            this.saveMedication(medicationDTO);
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
    public List<IngredientDTO> findMedicationsIngredients(Medication medication) throws Exception {
        return null;
    }

    @Override
    public List<MedicationDTO> findByName(String name) throws ParseException {
        List<Medication> medications = (List<Medication>) _medicationRepository.findAll();
        List<MedicationDTO> dtos = new ArrayList<>();
        if(medications.size()!=0){
            for(Medication p : medications){
                dtos.add(MedicationMapper.convertToMedicationDTO(p));
            }
        }
        if(name.trim().equals("")){
            return dtos;
        }
        return  dtos.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationDTO> findByType(Long type) throws ParseException {
        List<Medication> medications = _medicationRepository.findAll();
        List<MedicationDTO> filter = new ArrayList<>();
        for(Medication m : medications){

            if(m.getDrugType().ordinal() == type){
                filter.add(MedicationMapper.convertToMedicationDTO(m));
            }
        }
        return filter;
    }

    @Override
    public List<MedicationDTO> findByForm(Long form) throws ParseException {
        List<Medication> medications = _medicationRepository.findAll();
        List<MedicationDTO> filter = new ArrayList<>();
        for(Medication m : medications){

            if(m.getDrugForm().ordinal() == form){
                filter.add(MedicationMapper.convertToMedicationDTO(m));
            }
        }
        return filter;
    }
    @Override
    public List<MedicationDTO> findByKind(Long kind) throws ParseException {
        List<Medication> medications = _medicationRepository.findAll();
        List<MedicationDTO> filter = new ArrayList<>();
        for(Medication m : medications){

            if(m.getDrugKind().ordinal() == kind){
                filter.add(MedicationMapper.convertToMedicationDTO(m));
            }
        }
        return filter;
    }

    @Override
    public List<MedicationDTO> findByPrescribing(Long prescribing) throws ParseException {
        List<Medication> medications = _medicationRepository.findAll();
        List<MedicationDTO> filter = new ArrayList<>();
        for(Medication m : medications){

            if(m.getTypeOfPrescribing().ordinal() == prescribing){
                filter.add(MedicationMapper.convertToMedicationDTO(m));
            }
        }
        return filter;
    }

    @Override
    public void saveMedication(MedicationDTO medicationDTO) throws Exception {
        Medication medication =MedicationMapper.convertToMedication(medicationDTO);
        medication.setSubstituteMedication(new ArrayList<>());
        if(medicationDTO.getSubstituteMedication()!=null){

            for(MedicationDTO dto : medicationDTO.getSubstituteMedication()){
                try{
                    medication.getSubstituteMedication().add(_medicationRepository.findById(dto.getId()).orElse(null));
                }catch (Exception e){
                    e.printStackTrace();
                    throw new Exception(EXCEPTION + "medication with id: " + dto.getId().toString() + DOES_NOT_EXIST);
                }
            }
        }

        medication.setIngredients(new ArrayList<>());
        for(IngredientDTO dto: medicationDTO.getIngredients()){
            try{
                medication.getIngredients().add(_ingredientRepository.findById(dto.getId()).orElse(null));
            }catch (Exception e){
                e.printStackTrace();
                throw new Exception(EXCEPTION + "ingredient with id: " + dto.getId().toString() + DOES_NOT_EXIST);
            }
        }
        try{
            _medicationRepository.save(medication);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(EXCEPTION + "saveMedication " + FAIL);
        }
    }


}

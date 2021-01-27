package com.atlaspharmacy.atlaspharmacy.medication.service.implementations;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.IngredientDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.repository.IAllergyRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IAllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AllergyServiceImpl implements IAllergyService {

    @Autowired
    IAllergyRepository _allergyRepository;
    final private static String EXCEPTION = "Exception in Allergy Service Implementation method:";
    final private static String DOES_NOT_EXIST = "Allergy with Id does not exist";
    final private static String FAIL = "execution failed";

    @Override
    public AllergyDTO findById(Long id) {
        Allergy allergy = _allergyRepository.findById(id).orElse(null);
        if(allergy == null){
            throw  new NoSuchElementException(EXCEPTION + " findById" + DOES_NOT_EXIST);
        }

        return  AllergyDTO.convertToAllergyDTO(allergy);    }

    @Override
    public List<AllergyDTO> findAll() {
        List<Allergy> allergies = _allergyRepository.findAll();

        return convertToDTOS(allergies);
    }

    private List<AllergyDTO> convertToDTOS(List<Allergy> allergies) {
        List<AllergyDTO> dtos = new ArrayList<>();
        for(Allergy a : allergies){
            AllergyDTO dto = AllergyDTO.convertToAllergyDTO(a);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public void saveAllergy(Allergy allergy, AllergyDTO allergyDTO) throws Exception {

    }
}

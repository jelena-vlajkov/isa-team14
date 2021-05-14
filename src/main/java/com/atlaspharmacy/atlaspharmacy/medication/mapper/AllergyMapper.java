package com.atlaspharmacy.atlaspharmacy.medication.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.AllergyDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;

import java.util.ArrayList;
import java.util.List;

public class AllergyMapper {
    private AllergyMapper(){}
    public static AllergyDTO convertToDTO(Allergy a){
        return new AllergyDTO(a.getId(), a.getName());
    }
    public static Allergy convertToAllergy(AllergyDTO dto){
        return new Allergy(dto.getId(), dto.getName());
    }
    public static List<AllergyDTO> convertToDTOS(List<Allergy> allergies){
        List<AllergyDTO> dtos = new ArrayList<>();
        for(Allergy a : allergies){
            dtos.add(convertToDTO(a));
        }
        return dtos;
    }
    public static List<Allergy> convertToList(List<AllergyDTO> dtos){
        List<Allergy> allergies = new ArrayList<>();
        for(AllergyDTO a : dtos){
            allergies.add(convertToAllergy(a));
        }
        return allergies;
    }

}

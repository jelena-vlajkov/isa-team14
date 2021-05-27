package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.users.DTO.AverageGradeDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;

public class AverageGradeMapper {
    public static AverageGradeDTO mapToDTO(AverageGrade averageGrade){
        return new AverageGradeDTO(averageGrade.getExcellent(), averageGrade.getVeryGood(), averageGrade.getGood(),averageGrade.getPoor(), averageGrade.getVeryPoor());

    }

    public static AverageGrade mapToAverageGrade(AverageGradeDTO averageGrade){
        AverageGrade avgGrade=new AverageGrade();
        avgGrade.setExcellent(averageGrade.getExcellent());
        avgGrade.setGood(averageGrade.getGood());
        avgGrade.setVeryGood(averageGrade.getVeryGood());
        avgGrade.setPoor(averageGrade.getPoor());
        avgGrade.setVeryPoor(averageGrade.getVeryPoor());
        return avgGrade;
    }
}

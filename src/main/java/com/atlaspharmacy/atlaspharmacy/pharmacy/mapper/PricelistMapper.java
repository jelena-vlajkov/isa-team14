package com.atlaspharmacy.atlaspharmacy.pharmacy.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;

import java.util.ArrayList;
import java.util.List;

public class PricelistMapper {
    private PricelistMapper(){}


    public static PricelistDTO mapPricelistToDTO(Pricelist pricelist){
        return new PricelistDTO(pricelist.getId(), pricelist.getPrice(), MedicationMapper.convertToMedicationDTO(pricelist.getMedication()), PharmacyMapper.mapPharmacyToDTO(pricelist.getPharmacy()), pricelist.getPeriod());
    }

    public static Pricelist mapDTOToPricelist(PricelistDTO dto){
        Pricelist p = new Pricelist();
        p.setId(dto.getId());
        p.setPrice(dto.getPrice());
        p.setPeriod(new Period(dto.getStartPeriod(), dto.getEndPeriod()));
        p.setMedication(MedicationMapper.convertToMedication(dto.getMedication()));
        p.setPharmacy(PharmacyMapper.mapDTOToPharmacy(dto.getPharmacy()));
        return p;
    }

    public static List<PricelistDTO> mapToDTOS(List<Pricelist> pricelists){
        List<PricelistDTO> dtos = new ArrayList<>();
        for(Pricelist p : pricelists){
            dtos.add(mapPricelistToDTO(p));
        }

        return dtos;
    }
}

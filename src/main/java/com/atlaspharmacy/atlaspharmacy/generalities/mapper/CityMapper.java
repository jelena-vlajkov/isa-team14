package com.atlaspharmacy.atlaspharmacy.generalities.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.CityDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.DTO.StateDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.State;

import java.util.ArrayList;
import java.util.List;

public class CityMapper {
    public CityMapper(){}

//    public static CityDTO mapCityToDTO(City city){
//
//        return new CityDTO(city.getId(), city.getName(), city.getState().getId());
//    }
//
//    public static City mapDTOtoCity(CityDTO cityDTO){
//        City c = new City();
//        c.setId(cityDTO.getId());
//        c.setName(cityDTO.getName());
//        return c;
//    }
//
//    public static List<CityDTO> mapToListDTOS(List<City> cities){
//        List<CityDTO> dtos = new ArrayList<>();
//        for( City c : cities){
//            dtos.add(mapCityToDTO(c));
//        }
//        return dtos;
//    }
}

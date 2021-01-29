package com.atlaspharmacy.atlaspharmacy.generalities.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.CoordinatesDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesMapper {
    public CoordinatesMapper(){}

//    public static CoordinatesDTO mapCoordinatesToDTO(Coordinates coordinates){
//        return new CoordinatesDTO(coordinates.getId(), coordinates.getLongitude(), coordinates.getLatitude());
//    }
//    public static Coordinates mapCoordinatesDTOToCoordinates(CoordinatesDTO dto){
//        Coordinates c = new Coordinates();
//        c.setId(dto.getId());
//        c.setLatitude(c.getLatitude());
//        c.setLongitude(c.getLongitude());
//        return c;
//    }
//
//    public static List<CoordinatesDTO> coordinatesToListDTOS(List<Coordinates> coordinates){
//        List<CoordinatesDTO> dtos = new ArrayList<>();
//        for(Coordinates c : coordinates){
//            dtos.add(mapCoordinatesToDTO(c));
//        }
//        return dtos;
//    }
}

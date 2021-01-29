package com.atlaspharmacy.atlaspharmacy.generalities.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.DTO.CityDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.DTO.CoordinatesDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.DTO.StateDTO;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.State;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthorityDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {

    public AddressMapper(){

    }

    public static AddressDTO mapAddressToDTO(Address address) {

        return new AddressDTO(address.getId(), mapCoordinatesToDTO(address.getCoordinates()) , mapCityToDTO(address.getCity()), address.getStreet());
    }

    public static Address mapAddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        return address;
    }
    public static List<AddressDTO> mapAddressListToDTOS(List<Address> addresses) {
        List<AddressDTO> dtos = new ArrayList<>();
        for(Address a : addresses){
            dtos.add(mapAddressToDTO(a));
        }
        return dtos;
    }


    public static StateDTO mapStateToDTO(State state){
        return new StateDTO(state.getId(), state.getName());
    }
    public static State mapDTOtoState(StateDTO stateDTO){
        State s = new State();
        s.setId(stateDTO.getId());
        s.setName(stateDTO.getName());
        return s;
    }
    public static List<StateDTO> mapStateListToDTOS(List<State> states){
        List<StateDTO> dtos = new ArrayList<>();
        for( State s : states){
            dtos.add(mapStateToDTO(s));
        }
        return dtos;
    }

    public static CoordinatesDTO mapCoordinatesToDTO(Coordinates coordinates){
        return new CoordinatesDTO(coordinates.getId(), coordinates.getLongitude(), coordinates.getLatitude());
    }
    public static Coordinates mapCoordinatesDTOToCoordinates(CoordinatesDTO dto){
        Coordinates c = new Coordinates();
        c.setId(dto.getId());
        c.setLatitude(c.getLatitude());
        c.setLongitude(c.getLongitude());
        return c;
    }

    public static List<CoordinatesDTO> coordinatesToListDTOS(List<Coordinates> coordinates){
        List<CoordinatesDTO> dtos = new ArrayList<>();
        for(Coordinates c : coordinates){
            dtos.add(mapCoordinatesToDTO(c));
        }
        return dtos;
    }

    public static CityDTO mapCityToDTO(City city){

        return new CityDTO(city.getId(), city.getName(), mapStateToDTO(city.getState()));
    }

    public static City mapDTOtoCity(CityDTO cityDTO){
        City c = new City();
        c.setId(cityDTO.getId());
        c.setName(cityDTO.getName());
        return c;
    }

    public static List<CityDTO> mapCityListToDTOS(List<City> cities){
        List<CityDTO> dtos = new ArrayList<>();
        for( City c : cities){
            dtos.add(mapCityToDTO(c));
        }
        return dtos;
    }
}

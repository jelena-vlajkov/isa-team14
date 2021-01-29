package com.atlaspharmacy.atlaspharmacy.reservations.mapper;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import java.util.ArrayList;
import java.util.List;

public class CreateDrugReservationMapper {

    public CreateDrugReservationMapper() {
    }

    public static CreateDrugReservationDTO mapCreateDrugReservationToDTO(DrugReservation drugReservation) {
        CreateDrugReservationDTO createDrugReservationDTO = new CreateDrugReservationDTO(
                drugReservation.getPatient().getId(),
                drugReservation.getPatient().getEmail(),
                drugReservation.getPharmacy().getName(),
                drugReservation.getMedication().getName(),
                Integer.valueOf(drugReservation.getUniqueIdentifier()),
                drugReservation.getExpirationDate());
        return createDrugReservationDTO;
    }

    public static List<CreateDrugReservationDTO> mapCreateDrugReservationToListDTO(List<DrugReservation> drugReservations) {
        List<CreateDrugReservationDTO> drugReservationDTOS = new ArrayList<>();
        for (DrugReservation drugReservation : drugReservations) {
            drugReservationDTOS.add(mapCreateDrugReservationToDTO(drugReservation));
        }
        return drugReservationDTOS;
    }

}

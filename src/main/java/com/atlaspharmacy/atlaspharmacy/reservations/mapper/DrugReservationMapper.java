package com.atlaspharmacy.atlaspharmacy.reservations.mapper;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;

import java.util.ArrayList;
import java.util.List;

public class DrugReservationMapper {
    public static DrugReservationDTO mapDrugReservationToDTO(DrugReservation drugReservation) {
        String patientName = drugReservation.getPatient().getName() + " " + drugReservation.getPatient().getSurname();
        DrugReservationDTO dto = new DrugReservationDTO(patientName,
                drugReservation.getPatient().getEmail(),
                drugReservation.getMedication().getName(),
                String.valueOf(drugReservation.getUniqueIdentifier()),
                drugReservation.getExpirationDate());
        return dto;
    }

    public static List<DrugReservationDTO> mapDrugReservationToListDTO(List<DrugReservation> drugReservations) {
        List<DrugReservationDTO> drugReservationDTOS = new ArrayList<>();
        for (DrugReservation drugReservation : drugReservations) {
            drugReservationDTOS.add(mapDrugReservationToDTO(drugReservation));
        }
        return drugReservationDTOS;
    }
}

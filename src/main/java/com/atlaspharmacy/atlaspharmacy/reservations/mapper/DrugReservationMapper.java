package com.atlaspharmacy.atlaspharmacy.reservations.mapper;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.PatientDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrugReservationMapper {
    private DrugReservationMapper() {}

    public static DrugReservationDTO mapDrugReservationToDTO(DrugReservation drugReservation) {
        String patientName = drugReservation.getPatient().getName() + " " + drugReservation.getPatient().getSurname();
        return new DrugReservationDTO(patientName,
                drugReservation.getPatient().getEmail(),
                drugReservation.getMedication().getName(),
                String.valueOf(drugReservation.getUniqueIdentifier()),
                drugReservation.getExpirationDate());
    }

    public static List<DrugReservationDTO> mapDrugReservationToListDTO(List<DrugReservation> drugReservations) {
        List<DrugReservationDTO> drugReservationDTOS = new ArrayList<>();
        for (DrugReservation drugReservation : drugReservations) {
            drugReservationDTOS.add(mapDrugReservationToDTO(drugReservation));
        }
        return drugReservationDTOS;
    }

    public static DrugReservation mapNewReservation(CreateDrugReservationDTO drugReservationDTO) {
        DrugReservation drugReservation = new DrugReservation();
        drugReservation.setReservationDate(new Date());
        drugReservation.setIssued(false);
        drugReservation.setTherapyDays(drugReservationDTO.getTherapyDays());
        drugReservation.setExpirationDate(new Date((new Date()).getTime() + 864000000));
        drugReservation.setCanceled(false);
        return  drugReservation;
    }

    public static DrugReservation mapPatientNewReservation(CreateDrugReservationDTO drugReservationDTO) {
        DrugReservation drugReservation = new DrugReservation();
        drugReservation.setReservationDate(new Date());
        drugReservation.setIssued(false);
        drugReservation.setTherapyDays(10);
        drugReservation.setExpirationDate(drugReservationDTO.getExpirationDate());
        drugReservation.setCanceled(false);
        return  drugReservation;
    }


    public static PatientDrugReservationDTO mapReservationToPatientReservationDTO(DrugReservation drugReservation){

        PatientDrugReservationDTO dto = new PatientDrugReservationDTO(
                drugReservation.getMedication().getName(),
                drugReservation.getPharmacy().getName(),
                drugReservation.getMedication().getProducer(),
                drugReservation.getExpirationDate(),
                drugReservation.isIssued(),
                drugReservation.isCanceled()
        );

        dto.setId(drugReservation.getId());

        return dto;
    }
}

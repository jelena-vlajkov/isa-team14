package com.atlaspharmacy.atlaspharmacy.reservations.service;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDrugReservationService {
    boolean reserveDrug(DrugReservationDTO drugReservationDTO);
    boolean cancelDrugReservation(int uniqueIdentifier);
    boolean issueDrugMedication(int uniqueIdentifier);
    DrugReservation findDrugReservation(int uniqueIdentifier);
    List<DrugReservation> findAllReservation(Long pharmacyId);

}

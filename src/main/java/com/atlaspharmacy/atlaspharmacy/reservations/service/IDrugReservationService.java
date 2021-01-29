package com.atlaspharmacy.atlaspharmacy.reservations.service;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDrugReservationService {
    boolean reserveDrug(CreateDrugReservationDTO drugReservationDTO) throws Exception;
    boolean cancelDrugReservation(int uniqueIdentifier) throws DueDateSoonException;
    boolean issueDrugReservation(int uniqueIdentifier) throws DueDateSoonException;
    DrugReservation findDrugReservation(int uniqueIdentifier) throws DueDateSoonException;
    List<DrugReservation> findAllReservation(Long pharmacyId);

}

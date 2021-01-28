package com.atlaspharmacy.atlaspharmacy.reservations.service.impl;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugReservationService implements IDrugReservationService {

    private final DrugReservationRepository drugReservationRepository;

    @Autowired
    public DrugReservationService(DrugReservationRepository drugReservationRepository) {
        this.drugReservationRepository = drugReservationRepository;
    }

    @Override
    public boolean reserveDrug(CreateDrugReservationDTO drugReservationDTO) {
        return false;
    }

    @Override
    public boolean cancelDrugReservation(int uniqueIdentifier) {
        return false;
    }

    @Override
    public boolean issueDrugReservation(int uniqueIdentifier) throws DueDateSoonException {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null && reservation.isExpired())
            throw new DueDateSoonException();
        reservation.setIssued(true);
        drugReservationRepository.save(reservation);
        return true;
    }

    @Override
    public DrugReservation findDrugReservation(int uniqueIdentifier) throws DueDateSoonException {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null && reservation.isExpired())
            throw new DueDateSoonException();
        return reservation;
    }

    @Override
    public List<DrugReservation> findAllReservation(Long pharmacyId) {
        return drugReservationRepository.findAll()
                .stream()
                .filter(drugReservation -> drugReservation.isPharmacy(pharmacyId))
                .collect(Collectors.toList());
    }
}

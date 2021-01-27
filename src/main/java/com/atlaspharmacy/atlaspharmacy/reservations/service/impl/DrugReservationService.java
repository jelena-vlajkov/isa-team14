package com.atlaspharmacy.atlaspharmacy.reservations.service.impl;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DrugReservationService implements IDrugReservationService {

    private final DrugReservationRepository drugReservationRepository;

    @Autowired
    public DrugReservationService(DrugReservationRepository drugReservationRepository) {
        this.drugReservationRepository = drugReservationRepository;
    }

    @Override
    public boolean reserveDrug(DrugReservationDTO drugReservationDTO) {
        return false;
    }

    @Override
    public boolean cancelDrugReservation(int uniqueIdentifier) {
        return false;
    }

    @Override
    public boolean issueDrugMedication(int uniqueIdentifier) {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null && reservation.isExpired())
            return false;
        reservation.setIssued(true);
        drugReservationRepository.save(reservation);
        return true;
    }

    @Override
    public DrugReservation findDrugReservation(int uniqueIdentifier) {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null && reservation.isExpired())
            return null;
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

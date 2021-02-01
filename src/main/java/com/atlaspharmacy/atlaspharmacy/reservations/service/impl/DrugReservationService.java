package com.atlaspharmacy.atlaspharmacy.reservations.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugReservationService implements IDrugReservationService {

    private final DrugReservationRepository drugReservationRepository;
    private final IMedicationService medicationService;
    private final IUserService userService;

    @Autowired
    public DrugReservationService(DrugReservationRepository drugReservationRepository, IMedicationService medicationService, IUserService userService) {
        this.drugReservationRepository = drugReservationRepository;
        this.medicationService = medicationService;
        this.userService = userService;
    }

    @Override
    public boolean reserveDrug(CreateDrugReservationDTO drugReservationDTO) throws Exception {
        //Dto to ent
        //Kod pacijenta lista likova
        //smanji kolicinu
        DrugReservation reservation = new DrugReservation(
                drugReservationDTO.createUnique(),
                drugReservationDTO.getExpirationDate(),
                medicationService.findByName(drugReservationDTO.getMedicationName()),
                (Patient) userService.getUserBy(drugReservationDTO.getPatientId()),
                null
        );
        reservation.setIssued(true);
        drugReservationRepository.save(reservation);

        return true;
    }

    @Override
    public boolean cancelDrugReservation(int uniqueIdentifier) throws DueDateSoonException  {
        //Kod pacijenta lista likova
        //povecaj kolicinu
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null){
            throw new DueDateSoonException();
        }

        if(!reservation.isExpired()){
            reservation.setIssued(false);
            drugReservationRepository.save(reservation);
            return true;
        }
        else
            throw new DueDateSoonException();

    }

    @Override
    public boolean issueDrugReservation(int uniqueIdentifier) throws DueDateSoonException {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
            throw new DueDateSoonException();
        reservation.setIssued(true);
        drugReservationRepository.save(reservation);
        return true;
    }

    @Override
    public DrugReservation findDrugReservation(int uniqueIdentifier) throws DueDateSoonException {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
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

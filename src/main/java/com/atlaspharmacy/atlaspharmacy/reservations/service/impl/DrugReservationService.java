package com.atlaspharmacy.atlaspharmacy.reservations.service.impl;

import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugReservationService implements IDrugReservationService {

    private final DrugReservationRepository drugReservationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    @Autowired
    public DrugReservationService(DrugReservationRepository drugReservationRepository, UserRepository userRepository, EmailService emailService) {
        this.drugReservationRepository = drugReservationRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
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
    public boolean issueDrugReservation(int uniqueIdentifier) throws DueDateSoonException, IOException, MessagingException {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
            throw new DueDateSoonException();
        reservation.setIssued(true);
        drugReservationRepository.save(reservation);
        emailService.sendMailForIssuingReservation(reservation.getPatient(), reservation);
        return true;
    }

    @Override
    public DrugReservation findDrugReservation(int uniqueIdentifier) throws Exception {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((User)user).getEmail();
        Pharmacist pharmacist = (Pharmacist) userRepository.findByEmail(mail);
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if (!pharmacist.getPharmacy().getId().equals(reservation.getPharmacy().getId())) {
            throw new Exception("Cannot read reservation from other pharmacies!");
        }
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
    @Override
    public List<DrugReservation> getPatientsIssuedDrugReservations(Long id){
        List<DrugReservation> drugReservations = new ArrayList<>();
        for(DrugReservation d : drugReservationRepository.findAll()){
            if(d.getPatient().getId().equals(id) && d.isIssued()){
                drugReservations.add(d);
            }
        }

        return drugReservations;
    }
}

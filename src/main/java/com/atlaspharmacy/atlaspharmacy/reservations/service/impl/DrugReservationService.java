package com.atlaspharmacy.atlaspharmacy.reservations.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.repository.PrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IEPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.DrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.PatientDrugReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.reservations.mapper.DrugReservationMapper;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DrugReservationService implements IDrugReservationService {

    private final DrugReservationRepository drugReservationRepository;
    private final IPharmacyStorageService pharmacyStorageService;
    private final MedicationRepository medicationRepository;
    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final IEPrescriptionService prescriptionService;
    @Autowired
    public DrugReservationService(DrugReservationRepository drugReservationRepository, IPharmacyStorageService pharmacyStorageService, MedicationRepository medicationRepository, PharmacyRepository pharmacyRepository, UserRepository userRepository, EmailService emailService,
                                  IEPrescriptionService prescriptionService) {
        this.drugReservationRepository = drugReservationRepository;
        this.pharmacyStorageService = pharmacyStorageService;
        this.medicationRepository = medicationRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.prescriptionService = prescriptionService;
    }

    @Transactional
    @Override
    public void reserveDrug(CreateDrugReservationDTO drugReservationDTO) throws Exception {
        if (!medicationRepository.findById(drugReservationDTO.getMedicationId()).isPresent()) {
            throw new Exception("Invalid medication");
        }
        if (!pharmacyRepository.findById(drugReservationDTO.getPharmacyId()).isPresent()) {
            throw new Exception("Invalid pharmacy");
        }

        if (!userRepository.findById(drugReservationDTO.getPatientId()).isPresent()) {
            throw new Exception("Invalid patient");
        }

        Medication m = medicationRepository.findById(drugReservationDTO.getMedicationId()).get();
        if (!pharmacyStorageService.isMedicationInPharmacy(m.getCode(), drugReservationDTO.getPharmacyId())) {
            throw new Exception("Invalid request");
        }

        Pharmacy p = pharmacyRepository.findById(drugReservationDTO.getPharmacyId()).get();
        Patient patient = (Patient) userRepository.findById(drugReservationDTO.getPatientId()).get();

        DrugReservation drugReservation = DrugReservationMapper.mapNewReservation(drugReservationDTO);
        drugReservation.setMedication(m);
        drugReservation.setPatient(patient);
        drugReservation.setPharmacy(p);

        Random randomGenerator = new Random();
        drugReservation.setUniqueIdentifier(randomGenerator.nextInt(99999999));

        PrescribedDrug prescribedDrug = new PrescribedDrug();
        pharmacyStorageService.medicationReserved(drugReservationDTO.getMedicationId(), drugReservationDTO.getPharmacyId());
        drugReservationRepository.save(drugReservation);
        //prescriptionService.saveNewPrescription(drugReservationDTO);
    }

    @Transactional
    @Override
    public boolean cancelDrugReservation(Long reservationId) {
        DrugReservation drugReservation = drugReservationRepository.findById(reservationId).get();
        int hoursAvailableToCancel = 3600 * 1000 * 24;

        if(drugReservation.canCancelReservation(hoursAvailableToCancel) == false)
            return  false;
        drugReservation.setCanceled(true);
        pharmacyStorageService.reduceMedicationQuantity(drugReservation.getMedication().getId(), drugReservation.getPharmacy().getId());
        drugReservationRepository.save(drugReservation);
        return  true;


    }

    @Override
    public boolean issueDrugReservation(int uniqueIdentifier) throws DueDateSoonException, IOException, MessagingException {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
            throw new DueDateSoonException();
        reservation.setIssued(true);
        reservation.setDateOfIssue(new Date());
        drugReservationRepository.save(reservation);
        emailService.sendMailForIssuingReservation(reservation.getPatient(), reservation);
        return true;
    }

    @Override
    public DrugReservation findDrugReservation(int uniqueIdentifier) throws Exception {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((User)user).getEmail();
        Pharmacist pharmacist = (Pharmacist) userRepository.findUserByEmail(mail);
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if (!pharmacist.getPharmacy().getId().equals(reservation.getPharmacy().getId())) {
            throw new Exception("Cannot read reservation from other pharmacies!");
        }
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
            throw new DueDateSoonException();
        return reservation;
    }

    @Override
    public List<DrugReservationDTO> findAllReservation(Long pharmacyId) {
        return DrugReservationMapper.mapDrugReservationToListDTO(drugReservationRepository.findByPharmacy(pharmacyId));
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

    @Override
    public List<DrugReservation> findAllIssuedReservationsForPharmacyAndPeriod(Long pharmacyId, PeriodDTO period) {
        List<DrugReservation> allDrugReservations= drugReservationRepository.findAll();
        List<DrugReservation> drugReservationsForPharmacyAndPeriod=new ArrayList<>();
        for(DrugReservation drugReservation:allDrugReservations){
            if(drugReservation.getPharmacy().getId().equals(pharmacyId))
                    if(drugReservation.isIssued())
                    if(drugReservation.getDateOfIssue().after(period.getStartPeriod()))
                        if(drugReservation.getDateOfIssue().before(period.getEndPeriod())){
                drugReservationsForPharmacyAndPeriod.add(drugReservation);
            }
        }
        return drugReservationsForPharmacyAndPeriod;
    }

    @Override
    public void patientDrugReservation(CreateDrugReservationDTO drugReservationDTO) throws Exception {
        if (!medicationRepository.findById(drugReservationDTO.getMedicationId()).isPresent()) {
            throw new Exception("Invalid medication");
        }
        if (!pharmacyRepository.findById(drugReservationDTO.getPharmacyId()).isPresent()) {
            throw new Exception("Invalid pharmacy");
        }

        if (!userRepository.findById(drugReservationDTO.getPatientId()).isPresent()) {
            throw new Exception("Invalid patient");
        }

        Medication m = medicationRepository.findById(drugReservationDTO.getMedicationId()).get();
        if (!pharmacyStorageService.isMedicationInPharmacy(m.getCode(), drugReservationDTO.getPharmacyId())) {
            throw new Exception("Invalid request");
        }

        Pharmacy p = pharmacyRepository.findById(drugReservationDTO.getPharmacyId()).get();
        Patient patient = (Patient) userRepository.findById(drugReservationDTO.getPatientId()).get();

        DrugReservation drugReservation = DrugReservationMapper.mapPatientNewReservation(drugReservationDTO);
        drugReservation.setMedication(m);
        drugReservation.setPatient(patient);
        drugReservation.setPharmacy(p);

        Random randomGenerator = new Random();
        drugReservation.setUniqueIdentifier(randomGenerator.nextInt(99999999));

        drugReservationRepository.save(drugReservation);
        emailService.sendDrugReservation(patient, drugReservation);
    }

    @Override
    public List<PatientDrugReservationDTO> getDrugReservationForPatient(Long patientId) {
        List<PatientDrugReservationDTO> patientDrugReservationDTOS = new ArrayList<>();

        List<DrugReservation> drugReservationsByPatient = drugReservationRepository.findAll()
                .stream().filter(drugReservation -> drugReservation.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
        /*OTKAZAN*/

        if (drugReservationsByPatient.isEmpty()) {
            return new ArrayList<>();
        }
        for (DrugReservation drugReservation : drugReservationsByPatient) {
            patientDrugReservationDTOS.add(DrugReservationMapper.mapReservationToPatientReservationDTO(drugReservation));
        }


        return patientDrugReservationDTOS;
    }
    @Override
    public boolean isDrugReserved(Long medicationId, Long pharmacyId) {
       List<DrugReservation> allDrugReservations= drugReservationRepository.findAll();
       for(DrugReservation d:allDrugReservations){
           if(d.getMedication().getId().equals(medicationId))
              if(d.getPharmacy().getId().equals(pharmacyId))
                   if(d.getExpirationDate().after(new Date()))
                   if( d.getReservationDate().before(new Date())){
               return true;
           }
       }
       return false;
    }
}

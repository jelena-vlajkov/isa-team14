package com.atlaspharmacy.atlaspharmacy.reservations.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IEPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
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
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final PharmacyStorageRepository pharmacyStorageRepository;
    @Autowired
    public DrugReservationService(DrugReservationRepository drugReservationRepository, IPharmacyStorageService pharmacyStorageService, MedicationRepository medicationRepository, PharmacyRepository pharmacyRepository, UserRepository userRepository, EmailService emailService,
                                  IEPrescriptionService prescriptionService, PharmacyStorageRepository pharmacyStorageRepository) {
        this.drugReservationRepository = drugReservationRepository;
        this.pharmacyStorageService = pharmacyStorageService;
        this.medicationRepository = medicationRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.prescriptionService = prescriptionService;
        this.pharmacyStorageRepository = pharmacyStorageRepository;
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
        PharmacyStorage pharmacyStorage = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndMedication(p.getId(), m.getId());

        if (pharmacyStorage.getQuantity() == 0) {
            throw new Exception("Invalid request");
        }

        DrugReservation drugReservation = DrugReservationMapper.mapNewReservation(drugReservationDTO);
        drugReservation.setMedication(m);
        drugReservation.setPatient(patient);
        drugReservation.setPharmacy(p);

        Random randomGenerator = new Random();
        drugReservation.setUniqueIdentifier(randomGenerator.nextInt(99999999));

        try {
            pharmacyStorage.setQuantity(pharmacyStorage.getQuantity() - 1);
            pharmacyStorageRepository.save(pharmacyStorage);
        } catch (Exception e) {
            if (!pharmacyStorageService.isMedicationInPharmacy(m.getCode(), drugReservationDTO.getPharmacyId())) {
                throw new Exception("Invalid request");
            }
            pharmacyStorageService.medicationReserved(m.getId(), p.getId());
        }
        drugReservationRepository.save(drugReservation);
        emailService.sendDrugReservation(patient, drugReservation);
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
        return true;
    }

    @Transactional
    @Override
    public boolean issueDrugReservation(int uniqueIdentifier, Long medicalStaffId) throws Exception {
        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
            throw new DueDateSoonException();
        if (!userRepository.findById(medicalStaffId).isPresent()) {
            throw new Exception("Invalid request");
        }
        Pharmacist pharmacist = (Pharmacist) userRepository.findById(medicalStaffId).get();
        if (!pharmacist.getPharmacy().getId().equals(reservation.getPharmacy().getId())) {
            throw new Exception("Cannot read reservation from other pharmacies!");
        }
        reservation.setIssued(true);
        reservation.setDateOfIssue(new Date());
        drugReservationRepository.save(reservation);
        userRepository.save(pharmacist);
        emailService.sendMailForIssuingReservation(reservation.getPatient(), reservation);
        return true;
    }

    @Override
    @Transactional
    public DrugReservation findDrugReservation(int uniqueIdentifier, Long medicalStaffId) throws Exception {
        if (!userRepository.findById(medicalStaffId).isPresent()) {
            throw new Exception("Invalid request");
        }
        Pharmacist pharmacist = (Pharmacist) userRepository.findById(medicalStaffId).get();

        DrugReservation reservation = drugReservationRepository.findByUniqueIdentifier(uniqueIdentifier);
        if (!pharmacist.getPharmacy().getId().equals(reservation.getPharmacy().getId())) {
            throw new Exception("Cannot read reservation from other pharmacies!");
        }
        if(reservation == null || reservation.isExpired() || reservation.isIssued())
            throw new DueDateSoonException();
        return reservation;
    }

    @Override
    @Transactional
    public List<DrugReservationDTO> findAllReservation(Long pharmacyId) {
        return DrugReservationMapper.mapDrugReservationToListDTO(drugReservationRepository.findByPharmacy(pharmacyId));
    }
    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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

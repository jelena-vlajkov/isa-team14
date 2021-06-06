package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.PenaltyMedication;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.PenaltyMedicationRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.PenaltyRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IPenaltyService;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PenaltyService implements IPenaltyService {
    private final PenaltyRepository penaltyRepository;
    private final AppointmentRepository appointmentRepository;
    private final PenaltyMedicationRepository penaltyMedicationRepository;
    private final DrugReservationRepository drugReservationRepository;

    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository, AppointmentRepository appointmentRepository, PenaltyMedicationRepository penaltyMedicationRepository, DrugReservationRepository drugReservationRepository) {
        this.penaltyRepository = penaltyRepository;
        this.appointmentRepository = appointmentRepository;
        this.penaltyMedicationRepository = penaltyMedicationRepository;
        this.drugReservationRepository = drugReservationRepository;
    }

    @Override
    public void savePenalty(Penalty penalty) throws Exception {
        if (!appointmentRepository.findById(penalty.getAppointment().getId()).isPresent()) {
            throw new Exception("Invalid request!");
        }

        Appointment appointment = appointmentRepository.findById(penalty.getAppointment().getId()).get();
        appointment.setFinished(true);
        appointmentRepository.save(appointment);

        penalty.setGivenDate(new Date());
        penaltyRepository.save(penalty);
    }

    @Override
    public List<Penalty> getByPatient(Long id) {
        return penaltyRepository.getAllPenaltiesForUser(id);
    }

    @Override
    public void saveMedicationPenalty(PenaltyMedication penaltyMedication) throws Exception {
        if (!drugReservationRepository.findById(penaltyMedication.getDrugReservation().getId()).isPresent()) {
            throw new Exception("Invalid request!");
        }

        penaltyMedication.setGivenDate(new Date());
        penaltyMedicationRepository.save(penaltyMedication);
    }

    @Override
    public List<PenaltyMedication> getMedicationPenaltyByPatient(Long patientId) {
        return penaltyMedicationRepository.getAllMedicationPenaltyByPatient(patientId);
    }

    public void initializeMedicationPenalty() throws Exception {
        List<DrugReservation> allDrugReservations = drugReservationRepository.findAll();
        for (DrugReservation d : allDrugReservations) {
            if(d.getExpirationDate().after(getFirstDateInMonth())) { //svi u ovom mesecu
                if(d.getExpirationDate().before(new Date()) && (!d.isCanceled() || !d.isIssued())) { //ako je krajnji rok pre danasnjeg data
                    PenaltyMedication penaltyMedication = new PenaltyMedication();
                    penaltyMedication.setDrugReservation(d);
                    penaltyMedication.setPatient(d.getPatient());
                    saveMedicationPenalty(penaltyMedication);
                }
            }
        }
    }

    @Override
    public int getNumberOfPatientPenaltiesForThisMonth(Long patientId) throws Exception {
        int numOfPenalties = 0;
        if (penaltyMedicationRepository.findAll().isEmpty()){
            initializeMedicationPenalty();
        }


        List<Penalty> appPenalties = getByPatient(patientId);
        List<PenaltyMedication> medPenalties = getMedicationPenaltyByPatient(patientId);

        for (Penalty p : appPenalties) {
            if (p.getGivenDate().after(getFirstDateInMonth()) && p.getGivenDate().before(getLastDateInMonth())) {
                    numOfPenalties++;
            }
        }

        for (PenaltyMedication p : medPenalties) {
            if (p.getGivenDate().after(getFirstDateInMonth()) && p.getGivenDate().before(getLastDateInMonth())) {
                numOfPenalties++ ;
            }
        }

        return numOfPenalties;
    }

    private Date getFirstDateInMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return calendar.getTime();
    }

    private Date getLastDateInMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return calendar.getTime();
    }



}

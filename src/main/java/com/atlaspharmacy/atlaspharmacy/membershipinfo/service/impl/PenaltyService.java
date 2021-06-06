package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.PenaltyRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IPenaltyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyService implements IPenaltyService {
    private final PenaltyRepository penaltyRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository, AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.penaltyRepository = penaltyRepository;
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void savePenalty(Penalty penalty) throws Exception {
        if (!appointmentRepository.findById(penalty.getAppointment().getId()).isPresent()) {
            throw new Exception("Invalid request!");
        }
        if (!userRepository.findById(penalty.getPatient().getId()).isPresent()) {
            throw new Exception("Invalid request!");
        }

        Appointment appointment = appointmentRepository.findById(penalty.getAppointment().getId()).get();
        appointment.setFinished(true);
        appointmentRepository.save(appointment);
        Patient patient = (Patient) userRepository.findById(penalty.getPatient().getId()).get();
        penalty.setPatient(patient);

        penalty.setAppointment(appointment);
        penaltyRepository.save(penalty);
    }

    @Override
    public List<Penalty> getByPatient(Long id) {
        return penaltyRepository.getAllPenaltiesForUser(id);
    }

}

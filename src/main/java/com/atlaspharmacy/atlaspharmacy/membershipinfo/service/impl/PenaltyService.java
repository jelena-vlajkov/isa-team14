package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.PenaltyRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IPenaltyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PenaltyService implements IPenaltyService {
    private final PenaltyRepository penaltyRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository, AppointmentRepository appointmentRepository) {
        this.penaltyRepository = penaltyRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void savePenalty(Penalty penalty) throws Exception {
        if (!appointmentRepository.findById(penalty.getAppointment().getId()).isPresent()) {
            throw new Exception("Invalid request!");
        }

        Appointment appointment = appointmentRepository.findById(penalty.getAppointment().getId()).get();
        appointment.setFinished(true);
        appointmentRepository.save(appointment);


        penaltyRepository.save(penalty);
    }

    @Override
    public List<Penalty> getByPatient(Long id) {
        return penaltyRepository.getAllPenaltiesForUser(id);
    }

}

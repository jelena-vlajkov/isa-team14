package com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PenaltyDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

public class PenaltyMapper {
    private PenaltyMapper() { }

    public static Penalty mapPenaltyFromDto(PenaltyDTO penaltyDTO) {
        return new Penalty(new Patient(penaltyDTO.getPatientId(), false, null), new Appointment(penaltyDTO.getAppointmentId()));
    }

}

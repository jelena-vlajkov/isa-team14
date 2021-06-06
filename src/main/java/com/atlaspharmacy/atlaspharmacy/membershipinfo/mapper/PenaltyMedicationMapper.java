package com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PenaltyMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.PenaltyMedication;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

public class PenaltyMedicationMapper {

    private PenaltyMedicationMapper() { }

    public static PenaltyMedication mapPenaltyFromDto(PenaltyMedicationDTO penaltyDTO) {
        return new PenaltyMedication(new Patient(penaltyDTO.getPatientId(), false, null), new DrugReservation(penaltyDTO.getDrugReservationId()));
    }
}

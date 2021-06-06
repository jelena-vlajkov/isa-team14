package com.atlaspharmacy.atlaspharmacy.medication.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.EPrescriptionDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;

public class EPrescriptionMapper {

    private EPrescriptionMapper() {}

    public static EPrescriptionDTO mapEPrescriptionToDTO(EPrescription ePrescription) {


        return new EPrescriptionDTO(
                ePrescription.getId(),
                ePrescription.getDate(),
                ePrescription.getPatient().getName() + " " + ePrescription.getPatient().getSurname(),
                ePrescription.getPharmacy().getName(),
                ePrescription.getType()
        );
    }


}

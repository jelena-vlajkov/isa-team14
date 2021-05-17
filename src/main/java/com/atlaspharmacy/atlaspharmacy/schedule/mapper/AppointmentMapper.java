package com.atlaspharmacy.atlaspharmacy.schedule.mapper;

import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {
    private AppointmentMapper() {}
    public static AppointmentDTO mapAppointmentToDTO(Appointment appointment) {
        String medicalStaffName;
        String medicalStaffEmail;

        if (appointment.isCounseling()) {
            Counseling counseling = (Counseling) appointment;
            medicalStaffName = counseling.getPharmacist().getName() + " " + counseling.getPharmacist().getSurname();
            medicalStaffEmail = counseling.getPharmacist().getEmail();
        } else {
            Examination examination = (Examination) appointment;
            medicalStaffName = examination.getDermatologist().getName() + " " + examination.getDermatologist().getSurname();
            medicalStaffEmail = examination.getDermatologist().getEmail();
        }

        return new AppointmentDTO(appointment.getAppointmentPeriod().getStartTime(),
                appointment.getAppointmentPeriod().getEndTime(),
                appointment.getCost(),
                appointment.getType(),
                appointment.isCanceled(),
                appointment.getPatient().getName() + " " + appointment.getPatient().getSurname(),
                appointment.getPatient().getEmail(),
                medicalStaffName,
                medicalStaffEmail, appointment.getPharmacy().getId());
    }

    public static List<AppointmentDTO> mapAppointmentsToListDTO(List<Appointment> appointments) {
        List<AppointmentDTO> mappedAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient() == null) {
                mappedAppointments.add(mapForAvailable(appointment));
            } else {
                mappedAppointments.add(mapAppointmentToDTO(appointment));

            }
        }
        return mappedAppointments;
    }
    public static AppointmentDTO mapForAvailable(Appointment appointment) {
        return new AppointmentDTO(appointment.getAppointmentPeriod().getStartTime(), appointment.getAppointmentPeriod().getEndTime());
    }
}

package com.atlaspharmacy.atlaspharmacy.schedule.controller;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @Autowired
    AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @RequestMapping(value = "/getScheduledByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public @ResponseBody List<Appointment> getScheduledByDate(@RequestParam("date") String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        for (Appointment appointment : appointmentService.getOccupiedBy(date)) {
            System.out.println(appointment.getType());
        }
        return appointmentService.getOccupiedBy(date);
    }

    @RequestMapping(value = "/getScheduledByDateAndStaff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public @ResponseBody List<Appointment> getScheduledByDateAndStaff(@RequestParam("date") String stringDate, @RequestParam("id") Long id) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        for (Appointment appointment : appointmentService.getOccupiedBy(date, id)) {
            System.out.println(appointment.getType());
        }
        return appointmentService.getOccupiedBy(date, id);
    }

    @RequestMapping(value = "/getAvailableForStaff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public @ResponseBody List<Appointment> getAvailable(@RequestParam("date") String stringDate, @RequestParam("id") Long id) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(stringDate);
        for (Appointment appointment : appointmentService.findAvailableBy(date, id)) {
            System.out.println(appointment.getType());
        }
        return appointmentService.findAvailableBy(date, id);
    }
}

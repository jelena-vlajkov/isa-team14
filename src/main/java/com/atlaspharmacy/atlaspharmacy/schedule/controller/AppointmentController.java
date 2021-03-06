package com.atlaspharmacy.atlaspharmacy.schedule.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.AppointmentNotFreeException;
import com.atlaspharmacy.atlaspharmacy.schedule.mapper.AppointmentMapper;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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


    @GetMapping(value = "/getScheduledByDate", produces = MediaType.APPLICATION_JSON_VALUE)
    @AppointmentAuthorization
    public @ResponseBody List<AppointmentDTO> getScheduledByDate(@RequestParam("date") String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.getOccupiedBy(date));
    }

    @GetMapping(value = "/getScheduledByDateAndStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    @AppointmentAuthorization
    public @ResponseBody List<AppointmentDTO> getScheduledByDateAndStaff(@RequestParam("date") String stringDate, @RequestParam("id") Long id) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.getOccupiedBy(date, id));
    }

    @GetMapping(value = "/getAvailableForStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    @AppointmentAuthorization
    public @ResponseBody List<AppointmentDTO> getAvailable(@RequestParam("date") String stringDate, @RequestParam("id") Long id) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.findAvailableBy(date, id));
    }

    @GetMapping(value = "/getFinishedAppointments", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AppointmentDTO> getAvailable(@RequestParam("id") Long id){
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.getAllFinishedAppointmentsForPatient(id));
    }

    @ExceptionHandler(DueDateSoonException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    AppointmentNotFreeException handleException(DueDateSoonException e) {
        return new AppointmentNotFreeException();
    }

}

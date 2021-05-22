package com.atlaspharmacy.atlaspharmacy.schedule.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.PatientsOverviewDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.SearchParametersDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.AppointmentNotFreeException;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.InvalidMedicalStaff;
import com.atlaspharmacy.atlaspharmacy.schedule.mapper.AppointmentMapper;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody List<AppointmentDTO> getScheduledByDate(@RequestParam("date") String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy.").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.getOccupiedBy(date));
    }

    @PostMapping(value = "/scheduleAppointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> scheduleAppointment(@RequestBody ScheduleAppointmentDTO dto) throws AppointmentNotFreeException {
        appointmentService.saveAppointment(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/finishAppointment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> scheduleAppointment(@RequestBody Long appointmentId) throws Exception {
        appointmentService.finishAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getScheduledByDateAndStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    @AppointmentAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody List<AppointmentDTO> getScheduledByDateAndStaff(@RequestParam("date") String stringDate, @RequestParam("id") Long id) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy.").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.getOccupiedBy(date, id));
    }

    @GetMapping(value = "/getAvailableForStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    @AppointmentAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody List<AppointmentDTO> getAvailable(@RequestParam("date") String stringDate, @RequestParam("id") Long id) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy.").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.findAvailableBy(date, id));
    }

    @GetMapping(value = "/getFinishedAppointments", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AppointmentDTO> getAvailable(@RequestParam("id") Long id){
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.getAllFinishedAppointmentsForPatient(id));
    }

    @GetMapping(value = "/getNumberOfScheduledByDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int getNumberOfScheduledByDate(@RequestParam("date") Date date){
        return appointmentService.getNumberOfScheduledByDate(date);
    }

    @GetMapping(value = "/getNumberOfScheduledForMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Integer> getNumberOfScheduledForMonth(@RequestParam("month") int month, @RequestParam("year") int year){
        return appointmentService.getNumberOfAppointmentsForMonth(month,year);
    }


    @GetMapping(value = "/getAvailableExaminationsForDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Examination> getAvailableExaminationsForDermatologist(@RequestParam("medicalStaffId") Long medicalStaffId, @RequestParam("pharmacyId") Long pharmacyId) throws ParseException {
       return appointmentService.findAvailableExaminationsForDermatologist(medicalStaffId,pharmacyId);
    }

    @GetMapping(value = "/getPatientsByMedicalStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @EmployeeAuthorization
    public @ResponseBody List<PatientsOverviewDTO> getPatientsByMedicalStaff(@RequestParam("medicalStaffId") Long medicalStaffId) throws Exception, InvalidMedicalStaff {
        return appointmentService.getPatientsByMedicalStaff(medicalStaffId);
    }

    @GetMapping(value = "/getAppointmentsForEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @EmployeeAuthorization
    public @ResponseBody List<AppointmentDTO> getPatientsByMedicalStaff(@RequestParam("medicalStaffId") Long medicalStaffId, @RequestParam("pharmacyId") Long pharmacyId,
                                                                        @RequestParam("date") String stringDate) throws Exception, InvalidMedicalStaff {
        Date date = new SimpleDateFormat("dd.MM.yyyy.").parse(stringDate);
        return AppointmentMapper.mapAppointmentsToListDTO(appointmentService.findAvailableByEmployeeAndPharmacy(pharmacyId, medicalStaffId, date));
    }


    @PostMapping(value = "/searchPatients", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @EmployeeAuthorization
    public @ResponseBody List<PatientsOverviewDTO> searchPatients(@RequestBody SearchParametersDTO searchParametersDTO) throws Exception, InvalidMedicalStaff {
        return appointmentService.SearchPatientsByParameters(searchParametersDTO);
    }




    @ExceptionHandler(DueDateSoonException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    AppointmentNotFreeException handleException(DueDateSoonException e) {
        return new AppointmentNotFreeException();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Exception handleException(Exception e) {
        return e;
    }

}

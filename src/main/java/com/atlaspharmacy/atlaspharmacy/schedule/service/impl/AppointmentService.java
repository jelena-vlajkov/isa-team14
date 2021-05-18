package com.atlaspharmacy.atlaspharmacy.schedule.service.impl;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;
import com.atlaspharmacy.atlaspharmacy.medication.repository.PrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.AppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.PatientsOverviewDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.SearchParametersDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.AppointmentNotFreeException;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.InvalidMedicalStaff;
import com.atlaspharmacy.atlaspharmacy.schedule.mapper.AppointmentMapper;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.*;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final WorkDayService workDayService;
    private final MedicalRecordRepository medicalRecordRepository;
    private static final int appointmentDuration = 30*60000;
    private static final double cost = 1000.00;


    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, PrescriptionRepository prescriptionRepository, WorkDayService workDayService, MedicalRecordRepository medicalRecordRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.workDayService = workDayService;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    @Override
    public Appointment scheduleCounseling(ScheduleAppointmentDTO appointmentDTO) throws AppointmentNotFreeException {
        if (isTimeValid(appointmentDTO.getStartDate(), appointmentDTO.getMedicalStaffId())) {
            Counseling counseling = new Counseling(new Period(appointmentDTO.getStartDate(), appointmentDTO.getEndDate()), cost, AppointmentType.Values.Counseling,
                    false, (Pharmacist) userRepository.findById(appointmentDTO.getMedicalStaffId()).get(), (Patient) userRepository.findById(appointmentDTO.getPatientId()).get());
            appointmentRepository.save(counseling);
            return counseling;
        }
        throw new AppointmentNotFreeException();
    }

    @Override
    public Appointment scheduleExamination(ScheduleAppointmentDTO appointmentDTO) throws AppointmentNotFreeException {
        if (isTimeValid(appointmentDTO.getStartDate(), appointmentDTO.getMedicalStaffId())) {
            Examination counseling = new Examination(new Period(appointmentDTO.getStartDate(), appointmentDTO.getEndDate()), cost, AppointmentType.Values.Counseling,
                    false, (Dermatologist) userRepository.findById(appointmentDTO.getMedicalStaffId()).get(), (Patient) userRepository.findById(appointmentDTO.getPatientId()).get());
            appointmentRepository.save(counseling);
            return counseling;
        }
        throw new AppointmentNotFreeException();
    }

    @Override
    public boolean cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        int hoursAvailableToCancel = 3600 * 1000 * 24;
        if (appointment.canCancel(hoursAvailableToCancel))
            return false;
        appointment.setCanceled(true);
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public List<Appointment> findAvailableBy(Date date, Long medicalStaffId) {
        List<Appointment> allOcupied = getOccupiedBy(date, medicalStaffId);
        List<Appointment> allFree = initializeAppointmentTime(date, medicalStaffId);
        List<Appointment> available = new ArrayList<>(allFree);
        for(Appointment appointment : allFree)
        {
            if (allOcupied.stream().anyMatch(a-> a.isOccupied(appointment.getAppointmentPeriod())))
                available.remove(appointment);
        }
        return available;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Counseling> findAvailableCounselingsBy(Date date) {
        List<WorkDay> allWorkingStaff = workDayService.getByDate(date);
        List<Counseling> counselings = new ArrayList<>();
        for (WorkDay workDay : allWorkingStaff) {
            if (workDay.isPharmacist())
                counselings.addAll((List<Counseling>)(List<?>) findAvailableBy(date, workDay.getMedicalStaff().getId()));
        }
        return counselings;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Examination> findAvailableExaminationsBy(Date date) {
        List<WorkDay> allWorkingStaff = workDayService.getByDate(date);
        List<Examination> examinations = new ArrayList<>();
        for (WorkDay workDay : allWorkingStaff) {
            if (workDay.isDermatologist())
                examinations.addAll((List<Examination>)(List<?>) findAvailableBy(date, workDay.getMedicalStaff().getId()));
        }
        return examinations;
    }

    @Override
    public List<Examination> findAvailableExaminationsForDermatologist(Long medicalStaffId,Long pharmacyId) {
        List<Examination> availableExaminations = new ArrayList<>();
        List<WorkDay> workDaysForDermatologist=workDayService.getBy(medicalStaffId);
        for (WorkDay workDay : workDaysForDermatologist) {
            if (workDay.getPharmacy().getId().equals(pharmacyId))
                availableExaminations.addAll((List<Examination>)(List<?>) findAvailableBy(workDay.getDate(), workDay.getMedicalStaff().getId()));
        }
        return availableExaminations;
    }

    @Override
    public List<PatientsOverviewDTO> getPatientsByMedicalStaff(Long medicalStaffId) throws InvalidMedicalStaff, Exception {
        if (!userRepository.findById(medicalStaffId).isPresent()) {
            throw new InvalidMedicalStaff();
        }
        User user = userRepository.findById(medicalStaffId).get();

        if (user.getRole().equals(Role.Values.Dermatologist)) {
            return findPatientsForDermatologist(medicalStaffId);
        }
        return findPatientsByPharmacist(medicalStaffId);
    }

    @Override
    public List<PatientsOverviewDTO> SearchPatientsByParameters(SearchParametersDTO searchParametersDTO) throws InvalidMedicalStaff, Exception {
        List<PatientsOverviewDTO> allPatients = getPatientsByMedicalStaff(searchParametersDTO.getMedicalStaffId());
        if (searchParametersDTO.getName().trim().isEmpty() && searchParametersDTO.getDate() == null) {
            return allPatients;
        }
        List<PatientsOverviewDTO> retVal = new ArrayList<>();
        String fullName;
        for (PatientsOverviewDTO dto : allPatients) {
            if (!searchParametersDTO.getName().trim().isEmpty()) {
                fullName = dto.getName() + " " + dto.getSurname();
                if (fullName.toLowerCase().contains(searchParametersDTO.getName().toLowerCase().trim())) {
                    retVal.add(dto);
                }
            }
            if (searchParametersDTO.getDate() != null) {
                for (AppointmentDTO a : dto.getPreviousAppointments()) {
                    if (a.getStartTime().getYear() == searchParametersDTO.getDate().getYear() &&
                        a.getStartTime().getMonth() == searchParametersDTO.getDate().getMonth() &&
                            a.getStartTime().getDay() == searchParametersDTO.getDate().getDay()) {
                        retVal.add(dto);
                        break;
                    }
                }
            }
        }
        return retVal;
    }

    private List<PatientsOverviewDTO> findPatientsByPharmacist(Long medicalStaffId) throws Exception {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<PatientsOverviewDTO> retVal = new ArrayList<>();
        List<Long> uniquePatients = new ArrayList<>();
        Counseling c;
        PatientsOverviewDTO p;
        List<AppointmentDTO> appointmentDTOS;
        for (Appointment a : allAppointments) {
            if (a.isCounseling()) {
                c = (Counseling) a;
                if (c.getPharmacist().getId().equals(medicalStaffId)){
                    if(!uniquePatients.contains(c.getPatient().getId())) {
                        uniquePatients.add(c.getPatient().getId());
                        p = new PatientsOverviewDTO();
                        p.setPatientId(a.getPatient().getId());
                        appointmentDTOS = p.getPreviousAppointments();
                        appointmentDTOS.add(AppointmentMapper.mapAppointmentToDTO(a));
                        p.setPreviousAppointments(appointmentDTOS);
                        retVal.add(p);
                        continue;
                    }
                    p = retVal.stream().filter(r -> r.getPatientId().equals(a.getPatient().getId())).findFirst().get();
                    appointmentDTOS = p.getPreviousAppointments();
                    appointmentDTOS.add(AppointmentMapper.mapAppointmentToDTO(a));
                    p.setPreviousAppointments(appointmentDTOS);
                }
            }
        }

        for (PatientsOverviewDTO po : retVal) {
            mapPrescribedDrugsToDTO(po);
        }

        return retVal;
    }

    private void mapPrescribedDrugsToDTO(PatientsOverviewDTO po) {
        List<PrescribedDrug> prescribedDrugs = prescriptionRepository.findAll();
        Patient patient;

        patient = (Patient) userRepository.findById(po.getPatientId()).get();

        po.setName(patient.getName());
        po.setSurname(patient.getSurname());
        po.setDateOfBirth(patient.getDateOfBirth());
        po.setGender(patient.getGender());

        List<String> medications = new ArrayList<>();
        for (PrescribedDrug prescribedDrug : prescribedDrugs) {
            if (prescribedDrug.getEprescription().getPatient().getId().equals(po.getPatientId())) {
                if (!userRepository.findById(po.getPatientId()).isPresent()) {
                    continue;
                }
                if (!po.getPrescribedDrugs().contains(prescribedDrug.getPrescribedMedication().getName())) {
                    medications = po.getPrescribedDrugs();
                    medications.add(prescribedDrug.getPrescribedMedication().getName());
                    po.setPrescribedDrugs(medications);

                }

            }
        }
    }



    private List<PatientsOverviewDTO> findPatientsForDermatologist(Long medicalStaffId) {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<PatientsOverviewDTO> retVal = new ArrayList<>();
        Examination c;;
        PatientsOverviewDTO p;
        List<AppointmentDTO> appointmentDTOS;
        List<Long> uniquePatients = new ArrayList<>();

        for (Appointment a : allAppointments) {
            if (a.isExamination()) {
                c = (Examination) a;
                if (c.getDermatologist().getId().equals(medicalStaffId)){
                    if(!uniquePatients.contains(c.getPatient().getId())) {
                        uniquePatients.add(c.getPatient().getId());
                        p = new PatientsOverviewDTO();
                        p.setPatientId(a.getPatient().getId());
                        appointmentDTOS = p.getPreviousAppointments();
                        appointmentDTOS.add(AppointmentMapper.mapAppointmentToDTO(a));
                        p.setPreviousAppointments(appointmentDTOS);
                        retVal.add(p);
                        continue;
                    }
                    p = retVal.stream().filter(r -> r.getPatientId().equals(a.getPatient().getId())).findFirst().get();
                    appointmentDTOS = p.getPreviousAppointments();
                    appointmentDTOS.add(AppointmentMapper.mapAppointmentToDTO(a));
                    p.setPreviousAppointments(appointmentDTOS);
                }
            }
        }

        for (PatientsOverviewDTO po : retVal) {
            mapPrescribedDrugsToDTO(po);
        }

        return retVal;
    }


    @Override
    public boolean isTimeValid(Date date, Long medicalStaffId) {
        List<Appointment> available = findAvailableBy(date, medicalStaffId);
        return available.stream()
                .anyMatch(appointment -> appointment.isOccupied(new Period(date, new Date(date.getTime() + appointmentDuration))));
    }

    @Override
    public List<Appointment> getScheduledForPatient(Long patinetId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isPatient(patinetId))
                .collect(Collectors.toList());
    }
    public List<Appointment> getPatientsAppointments(Long id){
        List<Appointment> appointments = appointmentRepository.findAll();
        List<Appointment> patientsAppointments = new ArrayList<>();
        for(Appointment a : appointments){
            if(a.getPatient().getId().equals(id)){
                patientsAppointments.add(a);
            }
        }
        return patientsAppointments;
    }
    @Override
    public List<Appointment> getAllFinishedAppointmentsForPatient(Long patientId){
        List<Appointment> appointments = new ArrayList<>();
        List<Appointment> patientsAppointments = getPatientsAppointments(patientId);
        for(Appointment a : patientsAppointments){
            if(a.getAppointmentPeriod().getEndTime().compareTo(new Date())<0){
                appointments.add(a);
            }
        }
        return appointments;
    }

    @Override
    public boolean occupiedExaminationExists(Long dermatologistId, Long pharmacyId) {
        List<Appointment> examinationsForDermatologistAndPharmacy=getOccupiedBy(dermatologistId)
                .stream().filter(appointment->appointment.getPharmacy().getId()
                        .equals(pharmacyId)).collect(Collectors.toList());
        if(examinationsForDermatologistAndPharmacy.size()!=0){
            return true;
        }
        return false;

    }

    @Override
    public boolean occupiedCounselingsExists(Long pharmacistId) { return getOccupiedBy(pharmacistId).size()!=0;}

    @Override
    public List<Counseling> getFinishedPatientsCounselings(Long id){
        List<Counseling> counselings = new ArrayList<>();
        List<Appointment> patientsFinishedAppointments = getAllFinishedAppointmentsForPatient(id);
        if(patientsFinishedAppointments!=null){
            for(Appointment a : patientsFinishedAppointments){
                if(a.getType().equals(AppointmentType.Counseling.toString())){
                    counselings.add((Counseling) appointmentRepository.findById(a.getId()).get());
                }
            }
        }

        return counselings;
    }
    @Override
    public List<Examination> getFinishedPatientsExaminations(Long id){
        List<Examination> exams = new ArrayList<>();
        List<Appointment> patientsFinishedAppointments = getAllFinishedAppointmentsForPatient(id);
        if(patientsFinishedAppointments!=null){
            for(Appointment a : patientsFinishedAppointments){
                if(a.getType().equals(AppointmentType.Examination.toString())){
                    exams.add((Examination) appointmentRepository.findById(a.getId()).get());

                }
            }
        }
        return exams;
    }

    @Override
    public List<Appointment> getOccupiedBy(Date date) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isSameDay(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getOccupiedBy(Long medicalStaffId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isMedicalStaff(medicalStaffId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getOccupiedBy(Date date, Long medicalStaffId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isMedicalStaffAndDate(medicalStaffId, date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> initializeAppointmentTime(Date date, Long medicalStaffId) {
        List<Appointment> appointments = new ArrayList<>();
        WorkDay workDay = workDayService.getBy(medicalStaffId, date);

        if (workDay == null)
            return appointments;

        int endTime = workDay.getWorkDayPeriod().getEndTime().getHours();
        Date appointmentStart = new Date(date.getYear(), date.getMonth(), date.getDate(), workDay.getWorkDayPeriod().getStartTime().getHours(), 0, 0);

        for (int i = 0; i < endTime - 1; i++)
        {
            Appointment appointment = new Appointment(new Period(new Date(appointmentStart.getTime() + (long) appointmentDuration * i),
                    new Date(appointmentStart.getTime() + (long) appointmentDuration * (i + 1))),
                    cost, "", false, null);
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public List<Counseling> getAllOccupiedCounselings(Date date) {
        List<Appointment> occupiedAppointments = appointmentRepository.findAll();
        List<Counseling> counselings = new ArrayList<>();
        for (Appointment appointment : occupiedAppointments) {
            if (appointment.isCounseling() && appointment.isSameDay(date))
                counselings.add((Counseling) appointment);
        }
        return counselings;
    }

    @Override
    public List<Examination> getAllOccupiedExaminations(Date date) {
        List<Appointment> occupiedAppointments = appointmentRepository.findAll();
        List<Examination> examinations = new ArrayList<>();
        for (Appointment appointment : occupiedAppointments) {
            if (appointment.isExamination() && appointment.isSameDay(date))
                examinations.add((Examination) appointment);
        }
        return examinations;
    }
    @Override
    public int getNumberOfScheduledByDate(Date date) {
        List<Appointment> allAppointments=appointmentRepository.findAll();
        int numberOfAppointments=0;
        for(Appointment a:allAppointments){
            if(a.isSameDay(date) && a.getType().equals(AppointmentType.Examination)) {
                numberOfAppointments++;
            }
        }
        return numberOfAppointments;
    }

    @Override
    public List<Integer> getNumberOfAppointmentsForMonth(int month, int year) {
        //datum se ne pomera dobro iz nepoznatog razloga,mozda nece ni biti potrebna
        //ova metoda,al nek stoji za sad
        List<Integer> scheduledForMonth = new ArrayList<>();
        Date startDate = new Date(year, month - 1, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date endDate = new Date(year, month - 1, day);
        while (startDate.before(endDate)) {
            scheduledForMonth.add(getNumberOfScheduledByDate(startDate));
            Long newTime = startDate.getTime() + 24 * 60 * 60 * 1000;
            startDate = new Date(newTime);
        }
        return scheduledForMonth;
    }
        @Override
        public List<Integer> getNumberOfAppointmentsForHalfYear(int part, int year) {
            return null;
        }

        @Override
        public List<Integer> getNumberOfAppointmentsForMonth(int year) {
            return null;
        }

    }



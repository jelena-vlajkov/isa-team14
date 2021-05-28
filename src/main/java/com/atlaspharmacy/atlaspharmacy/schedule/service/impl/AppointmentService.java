package com.atlaspharmacy.atlaspharmacy.schedule.service.impl;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyPricelistService;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.*;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;
import com.atlaspharmacy.atlaspharmacy.medication.repository.PrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
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
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final WorkDayService workDayService;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PharmacyRepository pharmacyRepository;
    private final IPharmacyPricelistService pharmacyPricelistService;
    private final IEmailService emailService;
    private static final int appointmentDuration = 30*60000;
    private static final double cost = 1000.00;


    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, PrescriptionRepository prescriptionRepository, WorkDayService workDayService, MedicalRecordRepository medicalRecordRepository, PharmacyRepository pharmacyRepository, IPharmacyPricelistService pharmacyPricelistService, IEmailService emailService) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.workDayService = workDayService;
        this.medicalRecordRepository = medicalRecordRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacyPricelistService = pharmacyPricelistService;
        this.emailService = emailService;
    }


    @Override
    public Appointment scheduleCounseling(ScheduleAppointmentDTO appointmentDTO) throws Exception {
        if (isTimeValid(appointmentDTO.getStartTime(), appointmentDTO.getMedicalStaffId())) {
            Counseling counseling = new Counseling(new Period(appointmentDTO.getStartTime(), appointmentDTO.getEndTime()), cost, AppointmentType.Values.Counseling,
                    false, (Pharmacist) userRepository.findById(appointmentDTO.getMedicalStaffId()).get(), (Patient) userRepository.findById(appointmentDTO.getPatientId()).get());
            Patient patient = (Patient) userRepository.findById(appointmentDTO.getPatientId()).get();
            counseling.setPatient(patient);
            counseling.setPharmacy(pharmacyRepository.findById(appointmentDTO.getPharmacyId()).get());
            counseling.setCost(pharmacyPricelistService.counselingCost(appointmentDTO.getPharmacyId()));
            emailService.successfullyScheduledCounseling(counseling);
            appointmentRepository.save(counseling);
            return counseling;
        }
        throw new AppointmentNotFreeException();
    }

    @Override
    public Appointment scheduleExamination(ScheduleAppointmentDTO appointmentDTO) throws Exception {
        if (isTimeValid(appointmentDTO.getStartTime(), appointmentDTO.getMedicalStaffId())) {
            Examination counseling = new Examination(new Period(appointmentDTO.getStartTime(), appointmentDTO.getEndTime()), cost, AppointmentType.Values.Counseling,
                    false, (Dermatologist) userRepository.findById(appointmentDTO.getMedicalStaffId()).get(), (Patient) userRepository.findById(appointmentDTO.getPatientId()).get());
            Patient patient = (Patient) userRepository.findById(appointmentDTO.getPatientId()).get();
            counseling.setPatient(patient);
            counseling.setPharmacy(pharmacyRepository.findById(appointmentDTO.getPharmacyId()).get());
            counseling.setCost(pharmacyPricelistService.examinationCost(appointmentDTO.getPharmacyId()));
            appointmentRepository.save(counseling);
            emailService.successfullyScheduledAppointment(counseling);
            return counseling;
        }
        throw new AppointmentNotFreeException();
    }

    @Transactional
    @Override
    public Appointment saveAppointment(ScheduleAppointmentDTO scheduleAppointmentDTO) throws Exception {
        if (!userRepository.findById(scheduleAppointmentDTO.getMedicalStaffId()).isPresent()) {
            throw new AppointmentNotFreeException("Invalid request!");
        }

        User user = userRepository.findById(scheduleAppointmentDTO.getMedicalStaffId()).get();
        if (user.getRole().equals(Role.Values.Dermatologist)) {
            if((appointmentRepository.overlappingExaminations(scheduleAppointmentDTO.getStartTime(),
                    scheduleAppointmentDTO.getEndTime(), scheduleAppointmentDTO.getMedicalStaffId())).size() != 0) {
                throw new Exception("Invalid request");
            }

            return scheduleExamination(scheduleAppointmentDTO);
        } else {
            if((appointmentRepository.ovelappingCunselings(scheduleAppointmentDTO.getStartTime(),
                    scheduleAppointmentDTO.getEndTime(), scheduleAppointmentDTO.getMedicalStaffId())).size() != 0) {
                throw new Exception("Invalid request");
            }

            return scheduleCounseling(scheduleAppointmentDTO);
        }
    }

    @Override
    public boolean cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        int hoursAvailableToCancel = 3600 * 1000 * 24;
        if (!appointment.canCancelAppointment(hoursAvailableToCancel))
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

    //slobodni termini kod dermatologa
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
    public List<Appointment> findAvailableByEmployeeAndPharmacy(Long pharmacyId, Long employeeId, Date date) {
        List<Appointment> appointmentsByDate = findAvailableBy(date, employeeId);
        List<Appointment> retVal = new ArrayList<>();
        for(Appointment a : appointmentsByDate) {
            if (a.isSameDay(date) && a.getPharmacy().getId().equals(pharmacyId)) {
                retVal.add(a);
            }
        }
        return retVal;
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

        for (PatientsOverviewDTO po : retVal) {
            mapPrescribedDrugsToDTO(po);
            po.setUpcomingAppointment(findUpcomingAppointments(po.getPatientId(), searchParametersDTO.getMedicalStaffId()));
        }
        return retVal;
    }

    @Override
    public void finishAppointment(Long appointmentId) throws Exception {
        if (!appointmentRepository.findById(appointmentId).isPresent()) {
            throw new Exception("Invalid request!");
        }

        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointment.setFinished(true);
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findSpecificAppointment(Date dateObj, Long medicalStaffId, Long patientId) throws Exception {
        List<Appointment> appointments = getOccupiedBy(dateObj, medicalStaffId);

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId().equals(patientId) && !appointment.isFinished()) {
                return appointment;
            }
        }
        throw new Exception("No such appointment!");
    }

    @Override
    public List<Appointment> findAvailableForPatient(PatientAppointmentDTO dto) throws Exception {
        Date date = new SimpleDateFormat("dd.MM.yyyy.").parse(dto.getDate());

        List<Appointment> availableForStaff = findAvailableByEmployeeAndPharmacy(dto.getPharmacyId(), dto.getMedicalStaffId(), date);
        List<Appointment> appointments = getPatientsAppointments(dto.getPatientId());
        List<Appointment> retVal = new ArrayList<>();

        for (Appointment a : availableForStaff) {
            if (appointments.stream().noneMatch(app -> app.isOccupied(a.getAppointmentPeriod()))) {
                retVal.add(a);
            }
        }

        return checkIfPatientHasScheduled(retVal, dto.getPatientId());
    }

    private List<Appointment> checkIfPatientHasScheduled(List<Appointment> retVal, Long patientId) {
        List<Appointment> upcomingScheduled = appointmentRepository.findUpcomingForPatient(patientId);
        List<Appointment> finalRetVal = new ArrayList<>();

        for (Appointment appointment : retVal) {
            if (!upcomingScheduled.stream().anyMatch(a -> a.isOccupied(appointment.getAppointmentPeriod()))) {
                finalRetVal.add(appointment);
            }
        }
        return finalRetVal;
    }

    @Override
    public List<Appointment> getScheduledByMonth(Date date, Long id) {
        List<Appointment> appointments = getOccupiedBy(id);
        List<Appointment> appointmentsByMonth = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getAppointmentPeriod().getStartTime().getMonth() == date.getMonth() &&
                a.getAppointmentPeriod().getStartTime().getYear() == date.getYear()) {
                appointmentsByMonth.add(a);
            }
        }
        return appointmentsByMonth;
    }

    private List<PatientsOverviewDTO> findPatientsByPharmacist(Long medicalStaffId) throws Exception {
        List<Counseling> allAppointments = appointmentRepository.getAllCounselingsByPharmacist(medicalStaffId);
        List<PatientsOverviewDTO> retVal = new ArrayList<>();
        List<Long> uniquePatients = new ArrayList<>();
        Counseling c;
        PatientsOverviewDTO p;
        List<AppointmentDTO> appointmentDTOS;
        for (Counseling a : allAppointments) {

            if(!uniquePatients.contains(a.getPatient().getId())) {
                uniquePatients.add(a.getPatient().getId());
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


        for (PatientsOverviewDTO po : retVal) {
            mapPrescribedDrugsToDTO(po);
            po.setUpcomingAppointment(findUpcomingAppointments(po.getPatientId(), medicalStaffId));
        }

        return retVal;
    }

    private boolean findUpcomingAppointments(Long patientId, Long medicalStaffId) {
        List<Appointment> scheduled = getOccupiedBy(new Date(), medicalStaffId);
        for (Appointment a : scheduled) {
            if (a.getPatient().getId().equals(patientId) && !a.isFinished()) {
                return true;
            }
        }
        return false;
    }

    private void mapPrescribedDrugsToDTO(PatientsOverviewDTO po) {
        List<PrescribedDrug> prescribedDrugs = prescriptionRepository.getPrescribedDrugBy(po.getPatientId());
        Patient patient;

        patient = (Patient) userRepository.findById(po.getPatientId()).get();

        po.setName(patient.getName());
        po.setSurname(patient.getSurname());
        po.setDateOfBirth(patient.getDateOfBirth());
        po.setGender(patient.getGender());

        List<String> medications = new ArrayList<>();
        for (PrescribedDrug prescribedDrug : prescribedDrugs) {
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



    private List<PatientsOverviewDTO> findPatientsForDermatologist(Long medicalStaffId) {
        List<Examination> allAppointments = appointmentRepository.getAllExaminationsByDermatologist(medicalStaffId);
        List<PatientsOverviewDTO> retVal = new ArrayList<>();
        Examination c;;
        PatientsOverviewDTO p;
        List<AppointmentDTO> appointmentDTOS;
        List<Long> uniquePatients = new ArrayList<>();

        for (Examination a : allAppointments) {
            if(!uniquePatients.contains(a.getPatient().getId())) {
                uniquePatients.add(a.getPatient().getId());
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
    public List<Appointment> getScheduledForPatient(Long patientId) {
        return appointmentRepository.findAppointmentsByPatient(patientId);
    }
    public List<Appointment> getPatientsAppointments(Long id) {
        return appointmentRepository.findAppointmentsByPatient(id);
    }
    @Override
    public List<Appointment> getAllFinishedAppointmentsForPatient(Long patientId){
        return appointmentRepository.getAllFinishedAppointments(patientId);
    }

    @Override
    public boolean occupiedExaminationExists(Long dermatologistId, Long pharmacyId) {
        return appointmentRepository.getOccupiedExaminationsByDermatologistAndPharmacy(dermatologistId, pharmacyId).size() != 0;
    }

    @Override
    public boolean occupiedCounselingsExists(Long pharmacistId) { return getOccupiedBy(pharmacistId).size()!=0;}

    @Override
    public List<Counseling> getFinishedPatientsCounselings(Long id){
       return appointmentRepository.getFinishedCounselingsByPatient(id);
    }
    @Override
    public List<Examination> getFinishedPatientsExaminations(Long id){
        return appointmentRepository.getFinishedExaminations(id);
    }
    @Override
    public List<AppointmentDTO> finishedAppointmentExamination(Long id){
        List<AppointmentDTO> exams = new ArrayList<>();
        List<Appointment> patientsFinishedAppointments = appointmentRepository.getAllAppointmentsFinishedForSpecificTypeAndPatient(AppointmentType.Values.Examination, id);
        if(patientsFinishedAppointments!=null){
            for(Appointment a : patientsFinishedAppointments) {
                    exams.add(AppointmentMapper.mapAppointmentToDTO(a));
            }
        }
        return exams;
    }

    @Override
    public List<AppointmentDTO> finishedAppointmentCounseling(Long patientId) {
        List<AppointmentDTO> exams = new ArrayList<>();
        List<Appointment> patientsFinishedAppointments = appointmentRepository.getAllAppointmentsFinishedForSpecificTypeAndPatient(AppointmentType.Values.Counseling, patientId);
        if(patientsFinishedAppointments!=null){
            for(Appointment a : patientsFinishedAppointments){
                    exams.add(AppointmentMapper.mapAppointmentToDTO(a));
            }
        }
        return exams;
    }

    @Override
    public List<AppointmentDTO> getNotFinishedAppointmentsForPatient(Long patientId) {
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        int hoursAvailableToCancel = 3600 * 1000 * 24;
        for(Appointment appointment : appointmentRepository.getNotFinishedAppointmentsForPatient(patientId)) {
                appointmentDTOS.add(AppointmentMapper.mapAppointmentToDTO(appointment));
        }

        return appointmentDTOS;
    }

    @Override
    public List<Appointment> getOccupiedBy(Date date) {
        return appointmentRepository.getAppointmentsByDate(date);
    }

    @Override
    public List<Appointment> getOccupiedBy(Long medicalStaffId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isMedicalStaff(medicalStaffId))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getOccupiedBy2(Long medicalStaffId) {
        try {
            return AppointmentMapper.mapAppointmentsToListDTO(appointmentRepository.findAll()
                    .stream()
                    .filter(appointment -> appointment.isMedicalStaff(medicalStaffId) && !appointment.isFinished())
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Appointment> getOccupiedBy(Date date, Long medicalStaffId) {
        List<Appointment> appointmentsByDate = appointmentRepository.getAppointmentsByDate(date);
        return appointmentsByDate.stream()
                .filter(appointment -> appointment.isMedicalStaff(medicalStaffId))
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
            appointment.setPharmacy(workDay.getPharmacy());
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public List<Counseling> getAllOccupiedCounselings(Date date) {
        return appointmentRepository.getAllOccupiedCounselings(date);
    }

    @Override
    public List<Examination> getAllOccupiedExaminations(Date date) {
        return appointmentRepository.getAllOccupiedExaminations(date);
    }

    @Override
    public int getNumberOfScheduledByDate(Date date) {
        List<Appointment> allAppointments=appointmentRepository.getAllAppointmentsByDateForSpecificType(date, AppointmentType.Values.Examination);
        return allAppointments.size();
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



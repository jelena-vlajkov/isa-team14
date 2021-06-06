package com.atlaspharmacy.atlaspharmacy.pharmderm.unittests;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.medication.repository.PrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyPricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyPricelistRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyPricelistService;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.PatientsOverviewDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.SearchParametersDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.SortingType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.InvalidMedicalStaff;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.WorkDayRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.WorkDayService;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.DERMATOLOGIST_ID;
import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PHARMACIST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScheduleTests {


    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkDayRepository workDayRepository;

    @Mock
    private VacationRequestRepository vacationRequestRepository;

    @Mock
    private PharmacyRepository pharmacyRepository;

    @Mock
    private PharmacyPricelistRepository pharmacyPricelistRepository;

    @Mock
    private IPharmacyPricelistService pharmacyPricelistService;

    @Mock
    private WorkDayService workDayService;

    @Mock
    private IEmailService emailService;

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    @Spy
    private AppointmentService appointmentService;

    @Test
    public void testFindAllAvailableAppointmentsForPharmacist() {
        List<Appointment> appointments = new ArrayList<>();
        Pharmacy p = PharmDerm.createPharmacy();
        Patient pa = PharmDerm.createPatient();
        Pharmacist ph = PharmDerm.createPharmacist(p, new Address());
        WorkDay w = new WorkDay();
        w.setId(1L);
        w.setMedicalStaff(ph);
        w.setDate(new Date());
        w.setPharmacy(p);
        Date todaysDate = new Date();
        List<WorkDay> workDays = new ArrayList<>();
        workDays.add(w);

        w.setWorkDayPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 0, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 0, 0)));

        appointments.add(PharmDerm.createAppointment(p, pa, ph));

        when(appointmentRepository.getAppointmentsByDate(any(Date.class))).thenReturn(appointments);
        when(workDayService.getBy(any(Long.class), any(Date.class))).thenReturn(workDays);
        when(vacationRequestRepository.getVacationRequestBy(any(Long.class), any(Date.class))).thenReturn(new ArrayList<>());
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of((User) ph));
        List<Appointment> appointmentList = appointmentService.findAvailableByEmployeeAndPharmacy(100L, PHARMACIST_ID, new Date());

        assertThat(appointmentList).hasSize(1);

        verify(appointmentRepository, times(1)).getAppointmentsByDate(any(Date.class));

        verify(workDayService, times(1)).getBy(any(Long.class), any(Date.class));
        verifyNoMoreInteractions(workDayService);

    }

    @Test
    public void testGettingPatientsForPharmacist() throws InvalidMedicalStaff, Exception {
        Pharmacy p = PharmDerm.createPharmacy();
        Patient pa = PharmDerm.createPatient();
        Pharmacist ph = PharmDerm.createPharmacist(p, new Address());

        when(prescriptionRepository.getPrescribedDrugBy(any(Long.class))).thenReturn(new ArrayList<>());
        when(appointmentRepository.getAllCounselingsByPharmacist(any(Long.class))).thenReturn(PharmDerm.createAppointments(p, pa, ph));
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(ph));

        List<PatientsOverviewDTO> overviewDTOS = appointmentService.getPatientsByMedicalStaff(PHARMACIST_ID, SortingType.NONE);

        assertThat(overviewDTOS).hasSize(2);

        verify(prescriptionRepository, times(2)).getPrescribedDrugBy(any(Long.class));
        verifyNoMoreInteractions(prescriptionRepository);

        verify(appointmentRepository, times(1)).getAllCounselingsByPharmacist(any(Long.class));


    }
    @Test
    void testSearchingPatients() throws InvalidMedicalStaff, Exception {
        Pharmacy p = PharmDerm.createPharmacy();
        Patient pa = PharmDerm.createPatient();
        Pharmacist ph = PharmDerm.createPharmacist(p, new Address());

        when(prescriptionRepository.getPrescribedDrugBy(any(Long.class))).thenReturn(new ArrayList<>());
        when(appointmentRepository.getAllCounselingsByPharmacist(any(Long.class))).thenReturn(PharmDerm.createAppointments(p, pa, ph));
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(ph));
        SearchParametersDTO dto = new SearchParametersDTO();
        dto.setName("jelena");
        dto.setDate(null);
        dto.setMedicalStaffId(PHARMACIST_ID);
        List<PatientsOverviewDTO> overviewDTOS = appointmentService.SearchPatientsByParameters(dto);

        assertThat(overviewDTOS).hasSize(1);

        verify(appointmentRepository, times(1)).getAllCounselingsByPharmacist(any(Long.class));
    }

    @Test
    public void testFindingScheduledForDermatologistAndMonth() {

        when(appointmentRepository.findAllSorted()).thenReturn(PharmDerm.createRandomAppointments());

        List<Appointment> appointments = appointmentService.getScheduledByMonth(new Date(), DERMATOLOGIST_ID);

        assertThat(appointments).hasSize(3);

        verify(appointmentRepository, times(1)).findAllSorted();
        verifyNoMoreInteractions(appointmentRepository);



    }
}


package com.atlaspharmacy.atlaspharmacy.pharmderm;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyPricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyPricelistRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyPricelistService;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.WorkDayRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.WorkDayService;
import org.aspectj.lang.annotation.Before;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @InjectMocks
    @Spy
    private AppointmentService appointmentService;

    @Test
    public void testFindAllAvailableAppointmentsForPharmacist() {
        List<Appointment> appointments = new ArrayList<>();
        Pharmacy p = PharmDerm.createPharmacy();
        Patient pa = PharmDerm.createPatient();
        Pharmacist ph = PharmDerm.createPharmacist(p);
        WorkDay w = new WorkDay();
        w.setId(1L);
        w.setMedicalStaff(ph);
        w.setDate(new Date());
        w.setPharmacy(p);
        Date todaysDate = new Date();

        w.setWorkDayPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 0, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 0, 0)));

        appointments.add(PharmDerm.createAppointment(p, pa, ph));

        WorkDayFactory workDayFactoryMock = Mockito.mock(WorkDayFactory.class);

        when(appointmentRepository.getAppointmentsByDate(any(Date.class))).thenReturn(appointments);
        when(workDayService.getBy(any(Long.class), any(Date.class))).thenReturn(w);
        when(vacationRequestRepository.getVacationRequestBy(any(Long.class), any(Date.class))).thenReturn(new ArrayList<>());

        List<Appointment> appointmentList = appointmentService.findAvailableByEmployeeAndPharmacy(100L, PHARMACIST_ID, new Date());

        assertThat(appointmentList).hasSize(1);

        verify(appointmentRepository, times(1)).getAppointmentsByDate(any(Date.class));

        verify(workDayService, times(1)).getBy(any(Long.class), any(Date.class));
        verifyNoMoreInteractions(workDayService);

    }

    @Test
    public void testCounselingScheduling() {
        List<Appointment> appointmentList = new ArrayList<>();
        assertThat(appointmentList).hasSize(4);
    }
}


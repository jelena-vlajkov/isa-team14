package com.atlaspharmacy.atlaspharmacy.pharmderm;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ScheduleTests {

    @Test
    public void testFindAllAvailableAppointmentsForPharmacist() {
        List<Appointment> appointmentList = new ArrayList<>();
        assertThat(appointmentList).hasSize(3);
    }

    @Test
    public void testCounselingScheduling() {
        List<Appointment> appointmentList = new ArrayList<>();
        assertThat(appointmentList).hasSize(4);
    }



}

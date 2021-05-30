package com.atlaspharmacy.atlaspharmacy.pharmderm.integrationtests;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.*;
import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PRESCRIPTION_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReportTests {
    @Autowired
    private DrugReservationRepository drugReservationRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AddressRepository addressRepository;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(username = DERMATOLOGIST_EMAIL, authorities = { DERMATOLOGIST_ROLE })
    void testSaveReport() throws Exception {
        Address a = addressRepository.save(new Address("a", new City("a"), new State("a"), new Coordinates(1.0,1.0)));
        Pharmacy p = pharmacyRepository.save(PharmDerm.createPharmacy2(a));
        Dermatologist d = userRepository.save(PharmDerm.createDermatologist(p, a));
        Patient pa = userRepository.save(PharmDerm.createPatient());

        String json = "{" +
                "\"patientId\" : " + pa.getId() +
                ",\"reportNotes\" : \"Sve okic\"" +
                ",\"medicalStaffId\" : " + d.getId() +
                ",\"pharmacyId\" : " + p.getId()
                + "}";

        this.mockMvc.perform(post(BASE_URL + REPORTS_URL).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(username = PHARMACIST_EMAIL, authorities = { PHARMACIST_ROLE })
    void testSavePenalty() throws Exception {
        Pharmacy p = pharmacyRepository.save(PharmDerm.createPharmacy());
        Patient pa = userRepository.save(PharmDerm.createPatient());
        Address ad = addressRepository.save(new Address());
        Pharmacist ph = userRepository.save(PharmDerm.createPharmacist(p, ad));
        Appointment a = appointmentRepository.save(PharmDerm.createAppointment(p, pa, ph));

        String json = "{\"patientId\" : " + pa.getId() + ",\"appointmentId\" : " + a.getId() + "}";

        this.mockMvc.perform(post(BASE_URL + PENALTY_URL).contentType(contentType).content(json)).andExpect(status().isOk());

    }
}

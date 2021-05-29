package com.atlaspharmacy.atlaspharmacy.pharmderm;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PrescribeMedicationTests {

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
    private PharmacyStorageRepository pharmacyStorageRepository;

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
    void testPrescribingMedication() throws Exception {

        Pharmacy p = pharmacyRepository.save(PharmDerm.createPharmacy());
        Dermatologist d = userRepository.save(PharmDerm.createDermatologist(p));
        Medication m = medicationRepository.save(PharmDerm.createMedication1());
        PharmacyStorage ps = pharmacyStorageRepository.save(PharmDerm.createPharmacyStorage2(p, m));
        Patient pa = userRepository.save(PharmDerm.createPatient());

        String json = "{" +
                "\"patientId\" : " + pa.getId() +
                ",\"pharmacyId\" : " + p.getId() +
                ",\"medicationId\" : " + m.getId() +
                ",\"therapyDays\" : " + 10 +
                ",\"medicalStaffId\" : " + d.getId() + "}";

        this.mockMvc.perform(post(BASE_URL + PRESCRIPTION_URL).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @WithMockUser(username = DERMATOLOGIST_EMAIL, authorities = { DERMATOLOGIST_ROLE })
    void testPrescribingMedicationWhenNoQuantity() throws Exception {
        Pharmacy p = pharmacyRepository.save(PharmDerm.createPharmacy());
        Dermatologist d = userRepository.save(PharmDerm.createDermatologist(p));
        Medication m = medicationRepository.save(PharmDerm.createMedication2());
        PharmacyStorage ps = pharmacyStorageRepository.save(PharmDerm.createPharmacyStorage1(p, m));
        Patient pa = userRepository.save(PharmDerm.createPatient());

        String json = "{" +
                "\"patientId\" : " + pa.getId() +
                ",\"pharmacyId\" : " + p.getId() +
                ",\"medicationId\" : " + m.getId() +
                ",\"therapyDays\" : " + 10 +
                ",\"medicalStaffId\" : " + d.getId() + "}";

        this.mockMvc.perform(post(BASE_URL + PRESCRIPTION_URL).contentType(contentType).content(json)).andExpect(status().isInternalServerError());
    }

}

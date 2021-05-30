package com.atlaspharmacy.atlaspharmacy.pharmderm.integrationtests;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.IssueReservationDTO;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.repository.DrugReservationRepository;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DrugReservationTests {
    @Autowired
    private DrugReservationRepository drugReservationRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private UserRepository userRepository;


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
    @WithMockUser(username = PHARMACIST_EMAIL, authorities = { PHARMACIST_EMAIL })
    void testIssuingDrugReservation() throws Exception {
        Pharmacy p = pharmacyRepository.save(PharmDerm.createPharmacy());
        DrugReservation d = drugReservationRepository.save(PharmDerm.createDrugReservation(p));
        User u = userRepository.save(PharmDerm.createPharmacist(p));
        int num = userRepository.findAll().size();
        for (User u2 : userRepository.findAll()) {
            System.out.println(u.getId());
        }

        String json = "{\"uniqueIdentifier\":" + DRUG_RESERVATION_UNIQUE_IDENTIFIER + ", \"medicalStaffId\":" + u.getId() + "}";

        this.mockMvc.perform(post(BASE_URL + ISSUE_DRUG_RESERVATION_URL).contentType(contentType).content(json)).andExpect(status().isOk());
    }


}

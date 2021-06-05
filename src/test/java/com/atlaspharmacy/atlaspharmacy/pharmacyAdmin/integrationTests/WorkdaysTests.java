package com.atlaspharmacy.atlaspharmacy.pharmacyAdmin.integrationTests;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacyAdmin.TestData;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.WorkDayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.BASE_URL;
import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PHARMACYADMIN_EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class WorkdaysTests {
    @Autowired
    WorkDayRepository workDayRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private AddressRepository addressRepository;

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
    @Rollback(value = true)
    @WithMockUser(username = PHARMACYADMIN_EMAIL, authorities = { PHARMACYADMIN_EMAIL })
    void testGetWorkdaysByPharmacist() throws Exception {
        Address address=new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2));
        addressRepository.save(address);
        Pharmacy pharmacy= TestData.createPharmacy1(address);
        pharmacyRepository.save(pharmacy);
        userRepository.save(TestData.CreatePharmacyAdmin(address,pharmacy));
        MedicalStaff u =(MedicalStaff) userRepository.save(TestData.createPharmacist5(address,pharmacy));
        workDayRepository.save(TestData.createWorkday(u,pharmacy));
        this.mockMvc.perform(get(BASE_URL + "workDay/getByMedicalStaff").param("medicalStaffId","300")).andExpect(status().isOk());
    }
    
}

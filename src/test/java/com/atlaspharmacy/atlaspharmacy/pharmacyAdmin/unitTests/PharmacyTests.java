package com.atlaspharmacy.atlaspharmacy.pharmacyAdmin.unitTests;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacyAdmin.TestData;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.PatientsOverviewDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.SortingType;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.InvalidMedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacistMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.DermatologistService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.PharmacistService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PHARMACIST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class PharmacyTests {
    @Mock
    private PharmacistRepository pharmacistRepository;
    @InjectMocks
    @Spy
    private PharmacistService pharmacistService;
    @Mock
    private DermatologistRepository dermatologistRepository;
    @InjectMocks
    @Spy
    private DermatologistService dermatologistService;

    @Test
    public void testSearchingPharmacists() {
        List<Pharmacist> pharmacists = new ArrayList<>();
        Address address = new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2));
        address.setId(100L);
        Pharmacy pharmacy=TestData.createPharmacy1(address);
        pharmacists.add(TestData.createPharmacist1(pharmacy,new Address()));
        pharmacists.add(TestData.createPharmacist2(pharmacy,new Address()));
        pharmacists.add(TestData.createPharmacist3(pharmacy,new Address()));
        pharmacists.add(TestData.createPharmacist4(pharmacy,new Address()));

        when(pharmacistRepository.findPharmacistByPharmacy(any(Long.class))).thenReturn(pharmacists);
        when(pharmacistRepository.findAll()).thenReturn(pharmacists);
        Long pharmacyId= 100L;
        String searchInput = "djura";

        List<Pharmacist> searchResults = pharmacistService.searchPharmacists(pharmacyId,searchInput);

        assertThat(searchResults).hasSize(2);
    }

    @Test
    public void testSearchingDermatologists() {
        List<Dermatologist> dermatologists = new ArrayList<>();
        dermatologists.add(TestData.createDermatologist1());
        dermatologists.add(TestData.createDermatologist2());

        when(dermatologistRepository.findAll()).thenReturn(dermatologists);
        Long pharmacyId= 2L;
        String searchInput = "lana";

        List<Dermatologist> searchResults = dermatologistService.searchDermatologists(pharmacyId,searchInput);

        assertThat(searchResults).hasSize(1);
    }

    @Test
    public void testFilteringDermatologistsByGrade(){
        List<Dermatologist> dermatologists = new ArrayList<>();
        dermatologists.add(TestData.createDermatologist1());
        dermatologists.add(TestData.createDermatologist2());
        List<DermatologistDTO> dtos= DermatologistMapper.mapToListDTOS(dermatologists);
        int grade=3;
        List<DermatologistDTO> filteredDermatologists = dermatologistService.filterDermatologistsByGrade(dtos,grade);
        assertThat(filteredDermatologists).hasSize(2);
    }


    @Test
    public void testFilteringDermatologistsByPharmacy(){
        List<Dermatologist> dermatologists = new ArrayList<>();
        dermatologists.add(TestData.createDermatologist1());
        dermatologists.add(TestData.createDermatologist2());
        List<DermatologistDTO> dtos= DermatologistMapper.mapToListDTOS(dermatologists);
        Long pharmacyId=100L;
        List<DermatologistDTO> filteredDermatologists = dermatologistService.filterDermatologistsByPharmacy(dtos,pharmacyId);
        assertThat(filteredDermatologists).hasSize(1);
    }

    @Test
    public void testFilteringPharmacistsByPharmacy(){
        List<Pharmacist> pharmacists = new ArrayList<>();
        Address address = new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2));
        address.setId(100L);
        pharmacists.add(TestData.createPharmacist1(TestData.createPharmacy1(address),address));
        pharmacists.add(TestData.createPharmacist2(TestData.createPharmacy2(),address));
        pharmacists.add(TestData.createPharmacist3(TestData.createPharmacy1(address),address));
        pharmacists.add(TestData.createPharmacist4(TestData.createPharmacy1(address),address));
        List<PharmacistDTO> dtos = PharmacistMapper.mapToListDTOS(pharmacists);
        Long pharmacyId = 100L;
        List<PharmacistDTO> filteredPharmacists = pharmacistService.filterPharmacistsByPharmacy(dtos,pharmacyId);
        assertThat(filteredPharmacists).hasSize(1);
    }

    @Test
    public void testFilteringPharmacistsByGrade(){
        Address address=new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2));
        address.setId(100L);
        List<Pharmacist> pharmacists = new ArrayList<>();
        pharmacists.add(TestData.createPharmacist1(TestData.createPharmacy1(address),address));
        pharmacists.add(TestData.createPharmacist2(TestData.createPharmacy2(),address));
        pharmacists.add(TestData.createPharmacist3(TestData.createPharmacy1(address),address));
        pharmacists.add(TestData.createPharmacist4(TestData.createPharmacy1(address),address));
        List<PharmacistDTO> dtos = PharmacistMapper.mapToListDTOS(pharmacists);
        int grade = 4;
        List<PharmacistDTO> filteredPharmacists = pharmacistService.filterPharmacistsByGrade(dtos,grade);
        assertThat(filteredPharmacists).hasSize(1);
    }
}

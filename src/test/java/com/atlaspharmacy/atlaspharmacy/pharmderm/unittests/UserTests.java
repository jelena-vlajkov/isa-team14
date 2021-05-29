package com.atlaspharmacy.atlaspharmacy.pharmderm.unittests;

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PATIENT_ID;
import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PHARMACY_ID;
import static org.assertj.core.api.Assertions.assertThat;

import com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO.MedicationToRecommendDTO;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.repository.MedicalRecordRepository;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.service.impl.MedicalRecordService;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.pharmderm.domain.PharmDerm;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTests {
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private PharmacyStorageService pharmacyStorageService;

    @InjectMocks
    @Spy
    private MedicalRecordService medicalRecordService;

    @Test
    void testRecommendingMedicationForPatient() {
        List<Medication> medications = new ArrayList<Medication>();

        medications.add(PharmDerm.createMedication3());
        medications.add(PharmDerm.createMedication4());
        medications.add(PharmDerm.createMedication5());
        medications.add(PharmDerm.createMedication6());

        when(medicationRepository.findAll()).thenReturn(medications);
        when(medicalRecordRepository.getByPatientId(any(Long.class))).thenReturn(PharmDerm.createMedicalRecord());
        when(pharmacyStorageService.isMedicationInPharmacy(Matchers.any(), Matchers.any())).thenReturn(true);

        List<MedicationToRecommendDTO> recommendedMedications = medicalRecordService.recommendMedicationForPatient(PATIENT_ID, PHARMACY_ID);

        assertThat(recommendedMedications).hasSize(3);

        verify(medicationRepository, times(1)).findAll();
        verifyNoMoreInteractions(medicationRepository);

        verify(pharmacyStorageService, times(3)).isMedicationInPharmacy(Matchers.any(), Matchers.any());
        verifyNoMoreInteractions(pharmacyStorageService);

        verify(medicalRecordRepository, times(1)).getByPatientId(any(Long.class));
        verifyNoMoreInteractions(medicalRecordRepository);
    }

}

package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyStorageDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;

import java.util.List;

public interface IPharmacyStorageService {
    List<PharmacyStorage> getMedicationsByPharmacy(Long pharmacyId);
    PharmacyStorage getMedicationInPharmacy(Long medicationId, Long pharmacyId);
    boolean isMedicationInPharmacy(Long code, Long id);
    void deleteMedicationFromPharmacyStorage(Long medicationId,Long pharmacyId);
    void editMedicationAmount(Long medicationId,Long pharmacyId,Long amount);
    List<MedicationDTO> getMedicationsInPharmacy(long pharmacyId);
    void addNewMedicationsToStorage(Order order);
    void addMedicationToPharmacy(Long medicationId,Long pharmacyId,Long amount);

    List<PharmacyStorage> getAllPharmaciesByMedication(Long id);

    List<PharmacyStorage> getAllPharmaciesByMedicationCode(Long code);

    void medicationReserved(Long medicationId, Long pharmacyId);

    void reduceMedicationQuantity(Long medicationId, Long pharmacyId);
}

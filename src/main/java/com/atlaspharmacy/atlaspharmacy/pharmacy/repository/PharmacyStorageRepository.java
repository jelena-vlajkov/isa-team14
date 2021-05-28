package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyStorageRepository extends JpaRepository<PharmacyStorage, Long> {
    
    @Query(value = "SELECT p FROM PharmacyStorage p WHERE p.pharmacy.id = ?1")
    List<PharmacyStorage> getAllPharmaciesStoragesByPharmacy(Long pharmacyId);

    @Query(value = "SELECT p FROM PharmacyStorage p WHERE p.pharmacy.id = ?1 AND p.medication.id = ?2")
    PharmacyStorage getAllPharmaciesStoragesByPharmacyAndMedication(Long pharmacyId, Long medicationId);

    @Query(value = "SELECT p FROM PharmacyStorage p WHERE p.pharmacy.id = ?1 AND p.medication.code = ?2")
    PharmacyStorage getAllPharmaciesStoragesByPharmacyAndCode(Long pharmacyId, Long code);

    @Query(value = "SELECT p FROM PharmacyStorage p WHERE p.medication.id = ?1")
    List<PharmacyStorage> getAllPharmaciesByMedication(Long medicationId);

    @Query(value = "SELECT p FROM PharmacyStorage p WHERE p.medication.code = ?1")
    List<PharmacyStorage> getAllPharmaciesByMedicationCode(Long code);
}

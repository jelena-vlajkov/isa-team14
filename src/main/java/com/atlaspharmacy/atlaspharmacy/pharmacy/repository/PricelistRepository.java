package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {

    @Query(value = "SELECT p FROM Pricelist p WHERE p.medication.id = ?1 AND p.validPeriod.startTime < current_date AND p.validPeriod.endTime > current_date ")
    List<Pricelist> getPricelistsByMedication(Long medicationId);

    @Query(value = "SELECT p FROM Pricelist p WHERE p.medication.code = ?1 AND p.validPeriod.startTime < current_date AND p.validPeriod.endTime > current_date ")
    List<Pricelist> getPricelistsByMedicationCode(Long code);

    @Query(value = "SELECT p FROM Pricelist p WHERE p.medication.code = ?1 AND p.validPeriod.startTime <= current_date AND p.validPeriod.endTime >= current_date AND p.pharmacy.id = ?2")
    Pricelist getPricelistsByMedicationCodeAndPharmacy(Long code, Long pharmacyId);

    @Query(value = "SELECT p FROM Pricelist p WHERE p.medication.code = ?1 AND p.validPeriod.startTime <= CAST(?3 as date) AND p.validPeriod.endTime >= CAST(?4 as date) AND p.pharmacy.id = ?2")
    Pricelist getPricelistsByMedicationCodeAndPharmacyAndPeriod(Long code, Long pharmacyId,Date start,Date end);

    @Query(value = "SELECT p FROM Pricelist p WHERE p.medication.code = ?1 AND CAST(p.validPeriod.startTime as date) < CAST(?2 as date) AND CAST(p.validPeriod.endTime as date) > CAST(?3 as date)")
    Pricelist getPricelistsByMedicationCodeAndPeriod(Long code, Date start, Date end);

    @Query(value = "SELECT p FROM Pricelist p WHERE p.medication.id = ?1 AND CAST(p.validPeriod.startTime as date) < CAST(?2 as date) AND CAST(p.validPeriod.endTime as date) > CAST(?3 as date)")
    Pricelist getPricelistsByMedicationIdAndPeriod(Long code, Date start, Date end);

    @Query(value = "SELECT p FROM Pricelist p WHERE  p.pharmacy.id = ?1 AND CAST(p.validPeriod.endTime as date) >= current_date")
    List<Pricelist> getPricelistsByPharmacy(Long pharmacyId);
}

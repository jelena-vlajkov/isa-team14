package com.atlaspharmacy.atlaspharmacy.reservations.repository;

import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = "SELECT d FROM DrugReservation d WHERE d.uniqueIdentifier = ?1")
    DrugReservation findByUniqueIdentifier(int uniqueIdentifier);

    @Query(value = "SELECT d FROM DrugReservation d WHERE d.pharmacy.id = ?1")
    List<DrugReservation> findByPharmacy(Long pharmacyId);


}

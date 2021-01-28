package com.atlaspharmacy.atlaspharmacy.reservations.repository;

import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long> {
    DrugReservation findByUniqueIdentifier(int uniqueIdentifier);
}

package com.atlaspharmacy.atlaspharmacy.membershipinfo.repository;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    @Query(value = "SELECT p FROM Penalty p WHERE p.patient.id = ?1")
    List<Penalty> getAllPenaltiesForUser(Long id);
}

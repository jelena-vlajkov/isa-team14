package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
}

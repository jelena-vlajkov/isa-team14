package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
}

package com.atlaspharmacy.atlaspharmacy.schedule.repository;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

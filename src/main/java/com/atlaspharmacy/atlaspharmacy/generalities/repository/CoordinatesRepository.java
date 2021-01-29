package com.atlaspharmacy.atlaspharmacy.generalities.repository;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}

package com.atlaspharmacy.atlaspharmacy.generalities.repository;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

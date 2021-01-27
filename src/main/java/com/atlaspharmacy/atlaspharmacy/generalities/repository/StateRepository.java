package com.atlaspharmacy.atlaspharmacy.generalities.repository;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}

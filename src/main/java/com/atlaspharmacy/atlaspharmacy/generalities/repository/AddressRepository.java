package com.atlaspharmacy.atlaspharmacy.generalities.repository;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}

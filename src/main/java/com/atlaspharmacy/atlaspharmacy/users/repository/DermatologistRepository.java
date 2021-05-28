package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DermatologistRepository extends JpaRepository<Dermatologist,Long> {
    @Query(value = "SELECT u FROM Dermatologist u WHERE LOWER(u.name) LIKE %?1% OR LOWER(u.surname) LIKE %?1%")
    List<Dermatologist> findUsersByFullName(String name);
}

package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE LOWER(u.name || ' ' || u.surname) LIKE %?1%")
    List<User> findUsersByFullName(String name);
}

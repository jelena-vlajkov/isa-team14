package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query(value = "SELECT i FROM Ingredient i WHERE i.id = ?1")
    Ingredient findIngredientById(Long id);

}

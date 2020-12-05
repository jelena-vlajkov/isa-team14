package com.atlaspharmacy.atlaspharmacy.pswregistration.repository;

import com.atlaspharmacy.atlaspharmacy.pswregistration.model.SpecialOffer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialOfferRepository extends CrudRepository<SpecialOffer, Integer> {
    
}
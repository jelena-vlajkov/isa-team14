package com.atlaspharmacy.atlaspharmacy.pswregistration.repository;

import com.atlaspharmacy.atlaspharmacy.pswregistration.model.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHospitalRepository extends CrudRepository<Hospital, String>{
    
}
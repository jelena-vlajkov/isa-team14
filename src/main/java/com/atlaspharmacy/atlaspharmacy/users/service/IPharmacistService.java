package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import java.util.ArrayList;
import java.util.List;

public interface IPharmacistService {
     List<Pharmacist> getAllPharmacistsToComplain(Long id);
     List<Pharmacist> findByPharmacy(Long id);
     Pharmacist editPharmacist(PharmacistDTO pharmacistDTO);

}

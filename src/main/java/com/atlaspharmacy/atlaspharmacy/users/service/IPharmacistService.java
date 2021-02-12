package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import java.util.ArrayList;
import java.util.List;

public interface IPharmacistService {
     List<Pharmacist> getAllPharmacistsToComplain(Long id);
}

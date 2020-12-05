package com.atlaspharmacy.atlaspharmacy.pswregistration.service;

import java.util.List;

import com.atlaspharmacy.atlaspharmacy.pswregistration.model.Hospital;

public interface IHospitalService {
    boolean add(Hospital hospital);
    boolean remove(String apiKey);
    boolean update(Hospital hospital);
    Hospital get(String apiKey);
    List<Hospital> getAll();

}
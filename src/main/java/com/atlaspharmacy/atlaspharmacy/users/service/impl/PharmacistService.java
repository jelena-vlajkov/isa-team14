package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PharmacistService implements IPharmacistService {
    private final PharmacistRepository pharmacistRepository;
    private final IPharmacyService pharmacyService;
    private final AppointmentService appointmentService;

    public PharmacistService(PharmacistRepository pharmacistRepository, IPharmacyService pharmacyService, AppointmentService appointmentService) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacyService = pharmacyService;
        this.appointmentService = appointmentService;
    }

    public boolean isPharmacistInList(List<Pharmacist> list,Long id){
        for(Pharmacist p : list){
            if(p.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public List<Pharmacist> distinctPharmacistToComplain(List<Pharmacist> list){
        List<Pharmacist> derms = new ArrayList<>();
        Map<Long, Pharmacist> map = new HashMap<>();
        for (Pharmacist d : list) {
            Long key = d.getId();
            if (!map.containsKey(key)) {
                map.put(key, d);
            }
        }
        Collection<Pharmacist> distinct = map.values();
        for(Pharmacist d : distinct){
            derms.add(d);
        }
        return derms;
    }
    @Override
    public List<Pharmacist> getAllPharmacistsToComplain(Long id) {
        List<Counseling> counselings = appointmentService.getFinishedPatientsCounselings(id);
        List<Pharmacist> pharmacistsToComplain = new ArrayList<>();
        for(Counseling c : counselings){
           pharmacistsToComplain.add(pharmacistRepository.findById(c.getPharmacist().getId()).get());
        }

        return distinctPharmacistToComplain(pharmacistsToComplain);
    }
}

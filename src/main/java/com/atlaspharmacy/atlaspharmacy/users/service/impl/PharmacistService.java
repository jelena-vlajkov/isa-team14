package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.IPharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PharmacistService implements IPharmacistService {
    private final PharmacistRepository pharmacistRepository;
    private final UserRepository userRepository;
    private final AppointmentService appointmentService;

    public PharmacistService(PharmacistRepository pharmacistRepository, UserRepository userRepository, AppointmentService appointmentService) {
        this.pharmacistRepository = pharmacistRepository;
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public List<Pharmacist> findByPharmacy(Long id) {
        List<Pharmacist> pharmacists= pharmacistRepository.findAll();

        List<Pharmacist> pharmacistsByPharmacy= new ArrayList<>();
        for (Pharmacist pharmacist: pharmacists) {
            if (pharmacist.getPharmacy().getId() == id)
            {
                pharmacistsByPharmacy.add(pharmacist);
            }
        }
        return pharmacistsByPharmacy;
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

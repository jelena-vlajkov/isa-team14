package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.service.IAddressService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PharmacistService implements IPharmacistService {
    private final PharmacistRepository pharmacistRepository;
    private final IPharmacyService pharmacyService;
    private final IAppointmentService appointmentService;
    private final AddressRepository addressRepository;
    private final PharmacyRepository pharmacyRepository;
    private final IAddressService addressService;

    @Autowired
    public PharmacistService(PharmacistRepository pharmacistRepository, IPharmacyService pharmacyService, IAppointmentService appointmentService, AddressRepository addressRepository, PharmacyRepository pharmacyRepository, IAddressService addressService) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacyService = pharmacyService;
        this.appointmentService = appointmentService;
        this.addressRepository = addressRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.addressService = addressService;
    }

    @Override
    public List<Pharmacist> findByPharmacy(Long id) {
        List<Pharmacist> allPharmacists=pharmacistRepository.findAll();

        List<Pharmacist> pharmacistsByPharmacy=new ArrayList<>();
        for(Pharmacist p: allPharmacists){
            if(p.getPharmacy().getId().equals(id)){
                pharmacistsByPharmacy.add(p);
            }
        }
        return pharmacistsByPharmacy;
    }

    @Override
    public Pharmacist editPharmacist(PharmacistDTO pharmacistDTO) {
        Pharmacist pharmacistToUpdate=pharmacistRepository.getOne(pharmacistDTO.getId());
        pharmacistToUpdate.setEmail(pharmacistDTO.getEmail());
        pharmacistToUpdate.setDateOfBirth(pharmacistDTO.getDateOfBirth());
        pharmacistToUpdate.setGender(pharmacistDTO.getGender());
        pharmacistToUpdate.setName(pharmacistDTO.getName());
        pharmacistToUpdate.setSurname(pharmacistDTO.getSurname());
        pharmacistToUpdate.setPhoneNumber(pharmacistDTO.getPhoneNumber());
        Address address = addressService.updateAddress(pharmacistDTO.getAddress());
        pharmacistToUpdate.setAddress(address);

        Pharmacy pharmacy = pharmacyService.editPharmacy(pharmacistDTO.getPharmacy());
        pharmacistToUpdate.setPharmacy(pharmacy);
        pharmacistRepository.save(pharmacistToUpdate);
        return pharmacistToUpdate;
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

package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.service.IAddressService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.AuthorityMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.AverageGradeMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacistMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;


    @Autowired
    public PharmacistService(PharmacistRepository pharmacistRepository, IPharmacyService pharmacyService, IAppointmentService appointmentService, AddressRepository addressRepository, PharmacyRepository pharmacyRepository, IAddressService addressService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacyService = pharmacyService;
        this.appointmentService = appointmentService;
        this.addressRepository = addressRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
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
    public List<Pharmacist> searchPharmacistsByPharmacyAdmin(String searchInput, Long pharmacyId) {
        List<Pharmacist> allPharmacists = findByPharmacy(pharmacyId);
        List<Pharmacist> searchedPharmacists = new ArrayList<>();
        for (Pharmacist p : allPharmacists) {
            if (searchInput.contains(p.getName()) || searchInput.contains(p.getSurname())) {
                searchedPharmacists.add(p);
            }
        }
        return searchedPharmacists;
    }
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

    @Override
    public List<Pharmacist> searchPharmacists(Long pharmacyId,String searchInput) {
        List<Pharmacist> pharmacistsToSearch = new ArrayList<>();
        if(pharmacyId!=null){ pharmacistsToSearch = findByPharmacy(pharmacyId); }
        else{ pharmacistsToSearch = getAll(); }
        List<Pharmacist> searchedPharmacists = new ArrayList<>();
        if(searchInput.equals("")){
            return pharmacistsToSearch;
        }
        for(Pharmacist p:pharmacistsToSearch)
        {
            if(searchInput.toLowerCase().contains(p.getName().toLowerCase()) || searchInput.toLowerCase().contains(p.getSurname().toLowerCase())){
                searchedPharmacists.add(p);
            }
        }
        return searchedPharmacists;
    }

    @Override
    public List<PharmacistDTO>  filterPharmacistsByPharmacy(List<PharmacistDTO> pharmacistsToFilter,Long pharmacyId) {
        List<PharmacistDTO> filteredPharmacists=new ArrayList<>();
        for(PharmacistDTO p:pharmacistsToFilter)
        {
            if(p.getPharmacy().getId().equals(pharmacyId)){
                filteredPharmacists.add(p);
            }
        }
        return filteredPharmacists;
    }

    @Override
    public List<PharmacistDTO>  filterPharmacistsByGrade(List<PharmacistDTO> pharmacistsToFilter, int grade) {
        List<PharmacistDTO> filteredPharmacists = new ArrayList<>();
        for(PharmacistDTO p:pharmacistsToFilter){
            if(p.getAverageGrade().count()>=grade){
                filteredPharmacists.add(p);
            }
        }
        return filteredPharmacists;
    }

    @Override
    public Pharmacist registerPharmacist(PharmacistDTO dto) throws Exception {
        if(userRepository.findByEmail(dto.getEmail())==null && !pharmacyService.isPharamcyRegistered(dto.getEmail())){
            String role ="ROLE_PHARMACIST";
            String password = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(password);
           // Address a = AddressMapper.mapAddressDTOToAddress(dto.getAddress());
            //addressRepository.save(a);

            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setName(dto.getName());
            pharmacist.setSurname(dto.getSurname());
            pharmacist.setDateOfBirth(dto.getDateOfBirth());
            pharmacist.setPhoneNumber(dto.getPhoneNumber());
            pharmacist.setEmail(dto.getEmail());
            pharmacist.setPassword(dto.getPassword());
            pharmacist.setGender(dto.getGender());
            pharmacist.setPharmacy(PharmacyMapper.mapDTOToPharmacy(dto.getPharmacy()));
            pharmacist.setFirstTimePassword(dto.isFirstTimeChanged());
            pharmacist.setAverageGrade(AverageGradeMapper.mapToAverageGrade(dto.getAverageGrade()));
            pharmacist.setRole(role);
            pharmacist.setAuthorities(authorityService.getAllRolesAuthorities(role));
            //pharmacist.setAddress(a);
            userRepository.save(pharmacist);
            return pharmacist;
        }
        throw new InvalidEmail();
    }

    @Override
    public boolean deletePharmacist(Long pharmacistId) {
        if(!appointmentService.occupiedCounselingsExists(pharmacistId)){
            pharmacistRepository.delete(pharmacistRepository.findById(pharmacistId).get());
            return true;
        }
        return false;

    }

    @Override
    public Pharmacist findById(Long pharmacistId) {
        return pharmacistRepository.findById(pharmacistId).get();
    }

    @Override
    public List<Pharmacist> getAll() {
        return pharmacistRepository.findAll();
    }

}

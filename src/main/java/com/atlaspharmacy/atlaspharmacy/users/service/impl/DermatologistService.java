package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.grade.domain.Grade;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository dermatologistRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final AppointmentService appointmentService;
    private final IPharmacyService pharmacyService;
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public DermatologistService(DermatologistRepository _dermatologistRepository, UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder passwordEncoder, AuthorityService authorityService, AppointmentService appointmentService, PharmacyService pharmacyService, PharmacyRepository pharmacyRepository) {
        this.dermatologistRepository = _dermatologistRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.appointmentService = appointmentService;
        this.pharmacyService = pharmacyService;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Dermatologist> findAllByPharmacy(Long id) {
        List<Dermatologist> allDermatologists= dermatologistRepository.findAll();
        List<Dermatologist> dermatologistsByPharmacy= new ArrayList<>();
        for (Dermatologist dermatologist: allDermatologists) {
            if (dermatologist.getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getId().equals(id)))
            {
                dermatologistsByPharmacy.add(dermatologist);
            }
        }
        return dermatologistsByPharmacy;
    }

    @Override
    public Dermatologist registerDermatologist(DermatologistDTO dto) throws Exception {
        if(userRepository.findUserByEmail(dto.getEmail())==null && !pharmacyService.isPharamcyRegistered(dto.getEmail())){
            String role ="ROLE_DERMATOLOGIST";
            String password = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(dto.getAddress());
            addressRepository.save(a);

            Dermatologist dermatologist = DermatologistMapper.mapDTOToDermatologist(dto);
            dermatologist.setAuthorities(authorityService.getAllRolesAuthorities(role));
            dermatologist.setAddress(a);
            dermatologist.setPharmacies(PharmacyMapper.maptDTOSToList(dto.getPharmacies()));
            userRepository.save(dermatologist);
            return dermatologist;
        }
        throw new InvalidEmail();
    }
    public List<Dermatologist> distinctDermatologistsToComplain(List<Dermatologist> list){
        List<Dermatologist> derms = new ArrayList<>();
        Map<Long, Dermatologist> map = new HashMap<>();
        for (Dermatologist d : list) {
            Long key = d.getId();
            if (!map.containsKey(key)) {
                map.put(key, d);
            }
        }
        Collection<Dermatologist> distinct = map.values();
        for(Dermatologist d : distinct){
            derms.add(d);
        }
        return derms;
    }
    @Override
    public List<Dermatologist> getAllDermatologistsToComplain(Long id){
        List<Examination> examinations = appointmentService.getFinishedPatientsExaminations(id);
        List<Dermatologist> dermatologistsToComplain = new ArrayList<>();
        for(Examination e : examinations){
            dermatologistsToComplain.add(dermatologistRepository.findById(e.getDermatologist().getId()).get());
        }
        return distinctDermatologistsToComplain(dermatologistsToComplain);
    }

    @Override
    public List<Dermatologist> searchDermatologists(Long pharmacyId, String searchInput) {
        List<Dermatologist> dermatologistsToSearch = new ArrayList<>();
        if (pharmacyId != null) {
            dermatologistsToSearch = findAllByPharmacy(pharmacyId);
        } else {
            dermatologistsToSearch = getAll();
        }
        List<Dermatologist> searchedDermatologists = new ArrayList<>();
        if (searchInput.equals("")) {
            return dermatologistsToSearch;
        }
        for (Dermatologist d : dermatologistsToSearch) {
            if (d.getName().toLowerCase().contains(searchInput.toLowerCase()) || d.getSurname().toLowerCase().contains(searchInput.toLowerCase())) {
                searchedDermatologists.add(d);
            }
        }
        return searchedDermatologists;
    }
    @Override
    public List<DermatologistDTO> filterDermatologistsByGrade(List<DermatologistDTO> dermatologistsToFilter, int grade) {
        List<DermatologistDTO> filteredDermatologists = new ArrayList<>();
        for(DermatologistDTO d:dermatologistsToFilter){
            if(d.getAverageGrade() >= grade){
                filteredDermatologists.add(d);
            }
        }
        return filteredDermatologists;
    }

    @Override
    public List<DermatologistDTO> filterDermatologistsByPharmacy(List<DermatologistDTO> dermatologistsToFilter,Long pharmacyId) {
        List<DermatologistDTO> filteredDermatologists = new ArrayList<>();
        for(DermatologistDTO d:dermatologistsToFilter){
            if(d.getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getId().equals(pharmacyId))){
                filteredDermatologists.add(d);
            }
        }
        return filteredDermatologists;
    }




    @Override
    public void addDermatologistToPharmacy(Long dermatologistId, Long pharmacyId) {
        Dermatologist dermatologist=dermatologistRepository.findById(dermatologistId).get();
        List<Pharmacy> dermatologistPharmacies=dermatologist.getPharmacies();
        dermatologistPharmacies.add(pharmacyService.getById(pharmacyId));
        dermatologist.setPharmacies(dermatologistPharmacies);
        dermatologistRepository.save(dermatologist);
    }

    @Override
    public boolean deleteDermatologistFromPharmacy(Long dermatologistId, Long pharmacyId) {
        if(!appointmentService.occupiedExaminationExists(dermatologistId,pharmacyId)){
            Dermatologist dermatologist=dermatologistRepository.findById(dermatologistId).get();
            List<Pharmacy> dermatologistPharmacies=dermatologist.getPharmacies();
            Iterator<Pharmacy> iterator = dermatologistPharmacies.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getId().equals(pharmacyId)) {
                    iterator.remove();
                }
            }
            dermatologistRepository.save(dermatologist);
            return true;
        }
        return false;

    }


    @Override
    public List<DermatologistDTO> getDermatologistsNotInPharmacy(Long pharmacyId) {
        List<Dermatologist> allDermatologists= dermatologistRepository.findAll();
        List<Dermatologist> dermatologistsNotInPharmacy= new ArrayList<>();
        for (Dermatologist dermatologist: allDermatologists) {
            if (!dermatologist.getPharmacies().stream().anyMatch(pharmacy -> pharmacy.getId().equals(pharmacyId)))
            {
                dermatologistsNotInPharmacy.add(dermatologist);
            }
        }
        return DermatologistMapper.mapToListDTOS(dermatologistsNotInPharmacy);
    }

    @Override
    public List<Dermatologist> getAll() {
        return dermatologistRepository.findAll();
    }

    @Override
    public List<DermatologistDTO> findForPatientGrading(Long patientId) {
        List<Examination> patientExaminations = appointmentService.getFinishedPatientsExaminations(patientId);
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();

        if(patientExaminations.isEmpty()) return new ArrayList<>();

        for (Examination e : patientExaminations) {
            dermatologistDTOS.add(DermatologistMapper.mapDermatologistToDTO(e.getDermatologist()));
        }

        List<DermatologistDTO> uniqueDermatologists = dermatologistDTOS.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(DermatologistDTO::getId))),
                        ArrayList::new));

        return uniqueDermatologists;

    }




}

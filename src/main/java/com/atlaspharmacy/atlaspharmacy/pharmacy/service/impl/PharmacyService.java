package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.EPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Subscription;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.ISubscriptionService;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl.SubscriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions.InvalidPharmacyData;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.IPharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.service.impl.DrugReservationService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository pharmacyRepository;
    private final AddressRepository addressRepository;
    private final AppointmentService appointmentService;
    private final EPrescriptionService ePrescriptionService;
    private final DrugReservationService drugReservationService;
    private final PharmacyStorageService pharmacyStorageService;
    private final ISubscriptionService subscriptionService;
    private final UserRepository userRepository;
    @Autowired
    public PharmacyService(IPharmacyRepository pharmacyRepository, AddressRepository addressRepository, AppointmentService appointmentService, EPrescriptionService ePrescriptionService, DrugReservationService drugReservationService, PharmacyStorageService pharmacyStorageService, SubscriptionService subscriptionService , UserRepository userRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.addressRepository = addressRepository;
        this.appointmentService = appointmentService;
        this.ePrescriptionService = ePrescriptionService;
        this.drugReservationService = drugReservationService;
        this.pharmacyStorageService = pharmacyStorageService;
        this.subscriptionService = subscriptionService;
        this.userRepository = userRepository;
    }

    @Override
    public PharmacyDTO getById(Long id) {
        Pharmacy pharmacy=pharmacyRepository.getById(id).orElse(null);
        return PharmacyMapper.mapPharmacyToDTO(pharmacy);
    }

    @Override
    public Pharmacy registerPharmacy(PharmacyDTO pharmacyDTO) throws InvalidPharmacyData, ParseException {
        if(userRepository.findByEmail(pharmacyDTO.getEmail())==null && !isPharamcyRegistered(pharmacyDTO.getEmail())){
            Address a = AddressMapper.mapAddressDTOToAddress(pharmacyDTO.getAddress());

            Pharmacy p = PharmacyMapper.mapDTOToPharmacy(pharmacyDTO);
            p.setAddress(a);

            addressRepository.save(a);
            pharmacyRepository.save(p);

            return p;
        }
        throw new InvalidPharmacyData();
    }
    @Override
    public boolean isPharamcyRegistered(String email){
        for(PharmacyDTO dto : getAllPharmacies()){
            if(dto.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    @Override
    public List<PharmacyDTO> getAllPharmacies(){
        List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
        List<PharmacyDTO> dtos = new ArrayList<>();
        if(pharmacies.size()!=0){
            for(Pharmacy p : pharmacies){
                dtos.add(PharmacyMapper.mapPharmacyToDTO(p));
            }
        }

        return dtos;
    }
    @Override
    public List<PharmacyDTO> getPharmaciesByMedication(Long  code){
        List<PharmacyDTO> pharmacies = getAllPharmacies();
        List<PharmacyDTO> pharmaciesContainingMedication = new ArrayList<>();
        for(PharmacyDTO p : pharmacies){
            List<PharmacyStorage> storages = pharmacyStorageService.getMedicationsByPharmacy(p.getId());
            for(PharmacyStorage s : storages){
                if(s.getMedication().getCode().equals(code)){
                    pharmaciesContainingMedication.add(p);
                }
            }
        }

        return pharmaciesContainingMedication;
    }

    @Override
    public List<PharmacyDTO> getSubscribed(Long id) {
        List<PharmacyDTO> pharmacies = new ArrayList<>();

        for(Subscription s : subscriptionService.getAllUsersSubscriptions(id)){
            if(s.getPatient().getId().equals(id)){
                pharmacies.add(PharmacyMapper.mapPharmacyToDTO(pharmacyRepository.getById(s.getPharmacy().getId()).get()));
            }
        }
        return pharmacies;
    }

    @Override
    public List<PharmacyDTO> findByName(String name) {
        List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
        List<PharmacyDTO> dtos = new ArrayList<>();
        if(pharmacies.size()!=0){
            for(Pharmacy p : pharmacies){
                dtos.add(PharmacyMapper.mapPharmacyToDTO(p));
            }
        }
        if(name.trim().equals("")){
            return dtos;
        }
        return  dtos.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacyDTO> findByAddress(String address) {
        List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
        List<PharmacyDTO> dtos = new ArrayList<>();
        if(pharmacies.size()!=0){
            for(Pharmacy p : pharmacies){
                dtos.add(PharmacyMapper.mapPharmacyToDTO(p));
            }
        }
        if(address.trim().equals("")){
            return dtos;
        }

        return dtos.stream()
                .filter(p -> p.getAddress().getStreet().toLowerCase().contains(address.toLowerCase().trim())
                || p.getAddress().getCity().getName().toLowerCase().contains(address.toLowerCase().trim())
                || p.getAddress().getState().getName().toLowerCase().contains(address.toLowerCase().trim()))
                .collect(Collectors.toList());

    }

    public List<Pharmacy> distinctPharmacyToComplain(List<Pharmacy> list){
        List<Pharmacy> derms = new ArrayList<>();
        Map<Long, Pharmacy> map = new HashMap<>();
        for (Pharmacy d : list) {
            Long key = d.getId();
            if (!map.containsKey(key)) {
                map.put(key, d);
            }
        }
        Collection<Pharmacy> distinct = map.values();
        for(Pharmacy d : distinct){
            derms.add(d);
        }
        return derms;
    }

    public List<Pharmacy> getEPrescriptionsPharmacies(Long id){
        List<EPrescription> prescriptions = ePrescriptionService.getPatientsEPrescription(id);
        List<Pharmacy> pharmacies = new ArrayList<>();
        for(EPrescription ep : prescriptions){
            pharmacies.add(pharmacyRepository.getById(ep.getPharmacy().getId()).get());
        }

        return distinctPharmacyToComplain(pharmacies);
    }
    public List<Pharmacy> getPatientsDrugIssuedPharmacies(Long id){
        List<DrugReservation> drugReservations = drugReservationService.getPatientsIssuedDrugReservations(id);
        List<Pharmacy> pharmacies = new ArrayList<>();
        for(DrugReservation d: drugReservations){
            pharmacies.add(pharmacyRepository.getById(d.getPharmacy().getId()).get());
        }

        return distinctPharmacyToComplain(pharmacies);
    }

    public List<Pharmacy> getPharmaciesWhereWasAppointment(Long id) {
        List<Appointment> patientsAppointments = appointmentService.getAllFinishedAppointmentsForPatient(id);
        List<Pharmacy> pharmacies = new ArrayList<>();
        for(Appointment a : patientsAppointments){
            pharmacies.add(pharmacyRepository.findById(a.getPharmacy().getId()).get());
        }

        return distinctPharmacyToComplain(pharmacies);

    }

    @Override
    public List<Pharmacy> getPharmaciesToComplain(Long id){
        List<Pharmacy> pharmacies = getEPrescriptionsPharmacies(id);
        pharmacies.addAll(getPharmaciesWhereWasAppointment(id));
        pharmacies.addAll(getPatientsDrugIssuedPharmacies(id));
        return distinctPharmacyToComplain(pharmacies);
    }




}

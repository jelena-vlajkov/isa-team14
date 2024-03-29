package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.service.IAddressService;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.service.IEPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Subscription;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.ISubscriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions.InvalidPharmacyData;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.IPharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyPricelistService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository pharmacyRepository;
    private final AddressRepository addressRepository;
    private final IAppointmentService appointmentService;
    private final IEPrescriptionService ePrescriptionService;
    private final IDrugReservationService drugReservationService;
    private final IPharmacyStorageService pharmacyStorageService;
    private final ISubscriptionService subscriptionService;
    private final UserRepository userRepository;
    private final IAddressService addressService;
    private final IPharmacyPricelistService pricelistService;
    private final IMedicationService medicationService;

    @Autowired
    public PharmacyService(IPharmacyRepository pharmacyRepository, AddressRepository addressRepository,
                           IAppointmentService appointmentService, IEPrescriptionService ePrescriptionService,
                           IDrugReservationService drugReservationService, IPharmacyStorageService pharmacyStorageService,
                           ISubscriptionService subscriptionService, UserRepository userRepository, IAddressService addressService, IPharmacyPricelistService pricelistService, IMedicationService medicationService) {
        this.pharmacyRepository = pharmacyRepository;
        this.addressRepository = addressRepository;
        this.appointmentService = appointmentService;
        this.ePrescriptionService = ePrescriptionService;
        this.drugReservationService = drugReservationService;
        this.pharmacyStorageService = pharmacyStorageService;
        this.subscriptionService = subscriptionService;
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.pricelistService = pricelistService;
        this.medicationService = medicationService;
    }



    @Override
    public Pharmacy getById(Long id) {return pharmacyRepository.getById(id).orElse(null);}

    @Override
    public Pharmacy registerPharmacy(PharmacyDTO pharmacyDTO) throws Exception {
        if(userRepository.findUserByEmail(pharmacyDTO.getEmail())==null && !isPharamcyRegistered(pharmacyDTO.getEmail())){
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
    public boolean isPharamcyRegistered(String email) throws Exception {
        Pharmacy pharmacy = pharmacyRepository.isPharmacyRegistered(email);
        if (pharmacy != null) {
            return true;
        }
        return false;
    }

    @Override
    public Pharmacy editPharmacy(PharmacyDTO pharmacyDTO) {
        Pharmacy pharmacyToUpdate= pharmacyRepository.findById(pharmacyDTO.getId()).get();
        Address address = addressRepository.findById(pharmacyToUpdate.getAddress().getId()).get();
        address.setCity(pharmacyDTO.getAddress().getCity());
        address.setCoordinates(pharmacyDTO.getAddress().getCoordinates());
        address.setState(pharmacyDTO.getAddress().getState());
        address.setStreet(pharmacyDTO.getAddress().getStreet());
        pharmacyToUpdate.setAddress(address);
        pharmacyToUpdate.setDescription(pharmacyDTO.getDescription());
        pharmacyToUpdate.setName(pharmacyDTO.getName());
        pharmacyToUpdate.setTelephone(pharmacyDTO.getTelephone());
        pharmacyToUpdate.setEmail(pharmacyDTO.getEmail());
        pharmacyRepository.save(pharmacyToUpdate);
        return pharmacyToUpdate;
    }

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public List<PharmacyDTO> getAllPharmacies() throws Exception {
        List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
        List<PharmacyDTO> dtos = new ArrayList<>();
        if(pharmacies.size()!=0){
            PharmacyDTO dto;
            for(Pharmacy p : pharmacies){
                dto = PharmacyMapper.mapPharmacyToDTO(p);
                dto.setCounselingCost(pricelistService.counselingCost(p.getId()));
                dto.setExaminationCost(pricelistService.examinationCost(p.getId()));
                dtos.add(dto);
            }
        }

        return dtos;
    }
    @Override
    public List<PharmacyDTO> getPharmaciesByMedication(Long  code) throws Exception {
        List<PharmacyStorage> pharmacyStorages = pharmacyStorageService.getAllPharmaciesByMedicationCode(code);
        List<PharmacyDTO> pharmaciesContainingMedication = new ArrayList<>();
        for(PharmacyStorage p : pharmacyStorages){
            pharmaciesContainingMedication.add(PharmacyMapper.mapPharmacyToDTO(p.getPharmacy()));
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


    @Override
    public List<PharmacyDTO> getPharmaciesByMedicationId(Long id) throws Exception {
        List<PharmacyStorage> allPharmacies = pharmacyStorageService.getAllPharmaciesByMedication(id);
        List<PharmacyDTO> dtos = new ArrayList<>();
        for (PharmacyStorage p : allPharmacies) {
            dtos.add(PharmacyMapper.mapPharmacyToDTO(p.getPharmacy()));
        }
        return dtos;
    }

    @Override
    public List<PharmacyDTO> findForPatientGrading(Long patientId) throws Exception {


        //medics
        List<MedicationDTO> medicationForGrading = medicationService.findForPatientGrading(patientId);
        List<PharmacyDTO> pharmaciesByMedication = new ArrayList<>();
        List<PharmacyDTO> uniquePharmaciesBymedications = new ArrayList<>();
        if (!medicationForGrading.isEmpty()) {
            for (MedicationDTO m : medicationForGrading) {
                pharmaciesByMedication.addAll(getPharmaciesByMedicationId(m.getId()));
            }
            uniquePharmaciesBymedications = pharmaciesByMedication.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(PharmacyDTO::getId))),
                            ArrayList::new));
        }

        //pharmacist
        List<Counseling> patientCounseling = appointmentService.getFinishedPatientsCounselings(patientId);
        List<PharmacyDTO> pharmaciesByCounseling = new ArrayList<>();
        List<PharmacyDTO> uniquePharmaciesByCounseling = new ArrayList<>();
        if (!patientCounseling.isEmpty()) {
            for (Counseling c : patientCounseling) {
                pharmaciesByCounseling.add(PharmacyMapper.mapPharmacyToDTO(c.getPharmacy()));
            }

            uniquePharmaciesByCounseling = pharmaciesByCounseling.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(PharmacyDTO::getId))),
                            ArrayList::new));
        }

        //dermatologist
        List<Examination> patientExamination = appointmentService.getFinishedPatientsExaminations(patientId);
        List<PharmacyDTO> pharmaciesByExamination = new ArrayList<>();
        List<PharmacyDTO> uniquePharmaciesByExamination = new ArrayList<>();
        if (!patientExamination.isEmpty()) {
            for (Examination e : patientExamination) {
                pharmaciesByExamination.add(PharmacyMapper.mapPharmacyToDTO(e.getPharmacy()));
            }

            uniquePharmaciesByExamination = pharmaciesByExamination.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(PharmacyDTO::getId))),
                            ArrayList::new));
        }

        //derm+pharm
        List<PharmacyDTO> dermPharmPharmacies = new ArrayList<>(Stream.of(uniquePharmaciesByCounseling, uniquePharmaciesByExamination).flatMap(List::stream)
                .collect(Collectors.toMap(PharmacyDTO::getId, d -> d, (PharmacyDTO x, PharmacyDTO y) -> x == null ? y : x)).values());

        //derm+pharm+med
        List<PharmacyDTO> dermPharmMedPharmacies = new ArrayList<>(Stream.of(dermPharmPharmacies, uniquePharmaciesBymedications).flatMap(List::stream)
                .collect(Collectors.toMap(PharmacyDTO::getId, d -> d, (PharmacyDTO x, PharmacyDTO y) -> x == null ? y : x)).values());

        if (dermPharmMedPharmacies.isEmpty()) {
            return new ArrayList<>();
        }

        return dermPharmMedPharmacies;

    }


}

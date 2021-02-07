package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.EPrescriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions.InvalidPharmacyData;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.IPharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.service.impl.DrugReservationService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.service.impl.AppointmentService;
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
    @Autowired
    public PharmacyService(IPharmacyRepository pharmacyRepository, AddressRepository addressRepository, AppointmentService appointmentService, EPrescriptionService ePrescriptionService, DrugReservationService drugReservationService) {
        this.pharmacyRepository = pharmacyRepository;
        this.addressRepository = addressRepository;
        this.appointmentService = appointmentService;
        this.ePrescriptionService = ePrescriptionService;
        this.drugReservationService = drugReservationService;
    }

    @Override
    public Pharmacy getById(Long id) {
        return  pharmacyRepository.getById(id).orElse(null);
    }

    @Override
    public Pharmacy registerPharmacy(PharmacyDTO pharmacyDTO) throws InvalidPharmacyData, ParseException {

        Address a = AddressMapper.mapAddressDTOToAddress(pharmacyDTO.getAddress());

        Pharmacy p = PharmacyMapper.mapDTOToPharmacy(pharmacyDTO);
        p.setAddress(a);

        addressRepository.save(a);
        pharmacyRepository.save(p);
        return p;
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

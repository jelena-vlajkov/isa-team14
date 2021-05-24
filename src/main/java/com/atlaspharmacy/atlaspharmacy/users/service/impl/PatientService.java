package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.generalities.service.IAddressService;
import com.atlaspharmacy.atlaspharmacy.generalities.service.impl.AddressService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl.PharmacyService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.PatientRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPatientService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    private final AuthorityService authorityService;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final EmailService emailService;
    private final IPharmacyService pharmacyService;
    private final IAddressService addressService;
    @Autowired
    public PatientService(UserRepository patientRepository, AuthorityService authorityService, AddressRepository addressRepository, VerificationTokenService verificationTokenService, BCryptPasswordEncoder passwordEncoder, PatientRepository patientRepository1, EmailService emailService, PharmacyService pharmacyService, IAddressService addressService) {
        this.userRepository = patientRepository;
        this.authorityService = authorityService;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository1;
        this.emailService = emailService;
        this.pharmacyService = pharmacyService;
        this.addressService = addressService;
    }
    public Patient findById(Long id){
        return (Patient) userRepository.findById(id).get();
    }
    public boolean dbHasEmail(String email){
        List<User> allUsers = userRepository.findAll();
        for(User u : allUsers){
            if(u.getEmail().toLowerCase().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Patient save(Patient patient){
        return userRepository.save(patient);
    }

    @Override
    public Patient registerPatient(PatientDTO patientDTO) throws Exception {
        if(userRepository.findByEmail(patientDTO.getEmail())==null && !pharmacyService.isPharamcyRegistered(patientDTO.getEmail())){
            String role ="ROLE_PATIENT";

            String password = passwordEncoder.encode(patientDTO.getPassword());
            patientDTO.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(patientDTO.getAddress());
            addressRepository.save(a);

            Patient patient = PatientMapper.mapDTOToPatient(patientDTO);
            patient.setVerificationCode(RandomString.make(64));
            patient.setAuthorities(authorityService.getAllRolesAuthorities(role));
            patient.setAddress(a);
            patient.setEnabled(false);

            Optional<Patient> saved = Optional.of(save(patient));
            saved.ifPresent( u->{
                try {
                    emailService.sendConfirmationEmail(saved.get());
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }

            });

            return saved.get();
        }
        throw new InvalidEmail();
    }
//    @Override
//    public boolean cancelAppointment(Long appointmentId) {
//        Appointment appointment = appointmentRepository.findById(appointmentId).get();
//        int hoursAvailableToCancel = 3600 * 1000 * 24;
//        if (appointment.canCancel(hoursAvailableToCancel))
//            return false;
//        appointment.setCanceled(true);
//        appointmentRepository.save(appointment);
//        return true;
//    }
    public Patient findByVerificattionCode(String token){
        List<Patient> patients = patientRepository.findAll();
        for(Patient p : patients){
            if(p.getVerificationCode().equals(token)){
                return p;
            }
        }
        return null;
    }
    @Override
    public Patient enablePatient(String token) {
        Patient patient = findByVerificattionCode(token);
        if(!patient.getEnabled()){
            patient.setEnabled(true);
            patient.setVerificationCode(null);
            userRepository.save(patient);
            return patient;
        }

        return null;
    }

    @Transactional
    public void editPatient(PatientDTO patientDTO){
        //bolje getOne od find
        Patient patientToUpdate = (Patient) userRepository.getOne(patientDTO.getId());
        Address address= addressService.updateAddress(patientDTO.getAddress());
        patientToUpdate.setSurname(patientDTO.getSurname());
        patientToUpdate.setName(patientDTO.getName());
        patientToUpdate.setAddress(address);
        patientToUpdate.setGender(patientDTO.getGender());
        patientToUpdate.setDateOfBirth(patientDTO.getDateOfBirth());
        patientToUpdate.setPhoneNumber(patientDTO.getPhoneNumber());
        patientToUpdate.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        userRepository.save(patientToUpdate);

    }

    public Patient getByMail(String mail){
        List<Patient> patients = patientRepository.findAll();
        for(Patient p : patients){
            if(p.getEmail().equalsIgnoreCase(mail)){
                return p;
            }
        }
        return null;

    }
}

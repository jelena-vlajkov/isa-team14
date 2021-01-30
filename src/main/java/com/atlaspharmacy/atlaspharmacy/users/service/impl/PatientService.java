package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IPatientService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService implements IPatientService {

    private final AuthorityService authorityService;
    private final AddressRepository addressRepository;
    private final VerificationTokenService verificationTokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository patientRepository;
    private final EmailService emailService;

    @Autowired
    public PatientService(UserRepository patientRepository, AuthorityService authorityService, AddressRepository addressRepository, VerificationTokenService verificationTokenService, BCryptPasswordEncoder passwordEncoder, EmailService emailService) {
        this.patientRepository = patientRepository;
        this.authorityService = authorityService;
        this.addressRepository = addressRepository;
        this.verificationTokenService = verificationTokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }
    public Patient findById(Long id){
        return (Patient)patientRepository.findById(id).get();
    }
    public boolean dbHasEmail(String email){
        List<User> allUsers = patientRepository.findAll();
        for(User u : allUsers){
            if(u.getEmail().toLowerCase().equals(email)){
                return true;
            }
        }
        return false;
    }
    @Transactional
    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }
    @Override
    public Patient registerPatient(PatientDTO patientDTO) throws InvalidPatientData, IOException, MessagingException {
        if(!dbHasEmail(patientDTO.getEmail())){
            String role = "ROLE_PATIENT";
            String password = passwordEncoder.encode(patientDTO.getPassword());
            patientDTO.setPassword(password);
            Address a = AddressMapper.mapAddressDTOToAddress(patientDTO.getAddress());
            addressRepository.save(a);
            List<Authority> auths = authorityService.getAllRolesAuthorities(role);
            Patient patient = PatientMapper.mapDTOToPatient(patientDTO);
            String code = RandomString.make(64);
            patient.setVerificationCode(code);
            patient.setAuthorities(auths);
            patient.setAddress(a);
            patient.setEnabled(false);

//        patientRepository.save(patient);
//
            Optional<Patient> saved = Optional.of(save(patient));
            saved.ifPresent( u->{
                try {
                    emailService.sendEmail(saved.get());
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            return saved.get();
        }
        return null;
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
    @Override
    public Patient enablePatient(Long id) {
        Patient patient = (Patient)patientRepository.findById(id).get();
        if(!patient.getEnabled()){
            patient.setEnabled(true);
            patientRepository.save(patient);
            return patient;
        }

        return null;
    }
}

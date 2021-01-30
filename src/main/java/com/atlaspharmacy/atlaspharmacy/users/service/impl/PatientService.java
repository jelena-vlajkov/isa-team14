package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
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

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }
    @Override
    public Patient registerPatient(PatientDTO patientDTO) throws InvalidPatientData {
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
            try{
                String token = UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(),token);

                //SEND EMAIL
                emailService.sendEmail(u);

            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return saved.get();
    }
}

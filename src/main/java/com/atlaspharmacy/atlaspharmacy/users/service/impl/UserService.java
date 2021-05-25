package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.repository.AddressRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmployeeFirstTimeLoginDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmployeePassChange;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmDermDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final AddressRepository addressRepository;
    private final VerificationTokenService verificationTokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, AuthorityService authorityService, AddressRepository addressRepository, VerificationTokenService verificationTokenService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.addressRepository = addressRepository;
        this.verificationTokenService = verificationTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserBy(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByMail(String mail) {
        return userRepository.findByEmail(mail);
    }

    public User getPharmacyAdmin(Long pharmacyId) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateEmployee(PharmDermDTO pharmDermDTO) {
        User loggedInEmployee = getByEmail(pharmDermDTO.getEmail());
        if (pharmDermDTO.getGender().toLowerCase().trim().equals("female")) {
            loggedInEmployee.setGender(Gender.FEMALE);
        } else {
            loggedInEmployee.setGender(Gender.MALE);
        }
        loggedInEmployee.setName(pharmDermDTO.getName());
        loggedInEmployee.setSurname(pharmDermDTO.getSurname());
        loggedInEmployee.setPhoneNumber(pharmDermDTO.getPhoneNumber());
        loggedInEmployee.setDateOfBirth(pharmDermDTO.getDateOfBirth());
        userRepository.save(loggedInEmployee);

    }

    @Override
    public void updateEmployeePassword(EmployeePassChange employeePassChange) throws Exception {
        User user = userRepository.findByEmail(employeePassChange.getEmail());
        String encoded = passwordEncoder.encode(employeePassChange.getOldpassword());
        if (!passwordEncoder.matches(employeePassChange.getOldpassword(), user.getPassword())) {
            throw new Exception("Invalid password!");
        }
        if (passwordEncoder.matches(employeePassChange.getNewpassword(), user.getPassword())) {
            throw new Exception("Password must be different from the last one!");
        }
        user.setPassword(passwordEncoder.encode(employeePassChange.newpassword));
        userRepository.save(user);
    }

    @Override
    public void updateEmployeePassFirstTime(EmployeeFirstTimeLoginDTO employeePassChange) throws Exception {
        if (!passwordEncoder.matches(employeePassChange.getRepnewpassword(), passwordEncoder.encode(employeePassChange.getNewpassword()))) {
            throw new Exception("Invalid repeated password!");
        }

        User user = userRepository.findByEmail(employeePassChange.getEmail());
        user.setPassword(passwordEncoder.encode(employeePassChange.getNewpassword()));
        user.setFirstTimePassword(true);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersForEmployee() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsersByName(String name) {
        List<User> users = userRepository.findAll();
        List<User> retVal = new ArrayList<>();
        String fullName = "";
        for (User u : users) {
            fullName = u.getName() + " " + u.getSurname();

            if (fullName.toLowerCase().contains(name.toLowerCase().trim())) {
                retVal.add(u);
            }
        }
        return retVal;
    }

}


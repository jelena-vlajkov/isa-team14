package com.atlaspharmacy.atlaspharmacy.pharmacyAdmin;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.City;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.Coordinates;
import com.atlaspharmacy.atlaspharmacy.generalities.domain.valueobjects.State;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.domain.*;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;
import org.hibernate.jdbc.Work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestData {
    public static Pharmacy createPharmacy1(Address address) {
        Pharmacy pharmacy = new Pharmacy(2L);
        pharmacy.setName("Zegin");
        pharmacy.setEmail("zegin@gmail.com");
        pharmacy.setAddress(address);
        pharmacy.setTelephone(1221L);
        pharmacy.setDescription("tralalalala");
        pharmacy.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        return pharmacy;
    }

    public static Pharmacy createPharmacy2() {
        Pharmacy pharmacy = new Pharmacy(100L);
        pharmacy.setName("jankovic");
        pharmacy.setEmail("jankovic@gmail.com");
        pharmacy.setTelephone(1221L);
        pharmacy.setAddress(new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2)));

        pharmacy.setDescription("tralalalala");
        pharmacy.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        return pharmacy;
    }

    public static Pharmacist createPharmacist1(Pharmacy p, Address address) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setEmail("vlajkovn@gmail.com");
        pharmacist.setPharmacy(p);
        pharmacist.setId(500L);
        pharmacist.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        pharmacist.setAuthorities(authorityList);
        pharmacist.setName("Nadezda");
        pharmacist.setSurname("Vlajkov");
        pharmacist.setAddress(address);
        pharmacist.setDateOfBirth(new Date());
        pharmacist.setFirstTimePassword(true);
        pharmacist.setAddress(address);
        pharmacist.setGender(Gender.FEMALE);
        pharmacist.setPhoneNumber("");
        pharmacist.setLicenseNumber("");
        pharmacist.setRole(Role.Values.Pharmacist);
        return pharmacist;
    }

    public static Pharmacist createPharmacist3(Pharmacy p, Address address) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setEmail("djuro@gmail.com");
        pharmacist.setPharmacy(p);
        pharmacist.setId(500L);
        pharmacist.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        pharmacist.setAuthorities(authorityList);
        pharmacist.setName("Djura");
        pharmacist.setSurname("Djuric");
        pharmacist.setAddress(address);
        pharmacist.setDateOfBirth(new Date());
        pharmacist.setFirstTimePassword(true);
        pharmacist.setAddress(address);
        pharmacist.setGender(Gender.MALE);
        pharmacist.setPhoneNumber("");
        pharmacist.setLicenseNumber("");
        pharmacist.setRole(Role.Values.Pharmacist);
        return pharmacist;
    }

    public static Pharmacist createPharmacist4(Pharmacy p, Address address) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setEmail("djurojovic@gmail.com");
        pharmacist.setPharmacy(p);
        pharmacist.setId(700L);
        pharmacist.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        pharmacist.setAuthorities(authorityList);
        pharmacist.setName("djura");
        pharmacist.setSurname("Jovic");
        pharmacist.setAddress(address);
        pharmacist.setDateOfBirth(new Date());
        pharmacist.setFirstTimePassword(true);
        pharmacist.setAddress(address);
        pharmacist.setGender(Gender.MALE);
        pharmacist.setPhoneNumber("");
        pharmacist.setLicenseNumber("");
        pharmacist.setRole(Role.Values.Pharmacist);
        return pharmacist;
    }

    public static Pharmacist createPharmacist2(Pharmacy p, Address address) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setEmail("vlajkovj@gmail.com");
        pharmacist.setPharmacy(p);
        pharmacist.setId(300L);
        pharmacist.setAverageGrade(new AverageGrade(40, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        pharmacist.setAuthorities(authorityList);
        pharmacist.setName("Jelena");
        pharmacist.setSurname("Vlajkov");
        pharmacist.setAddress(address);
        pharmacist.setDateOfBirth(new Date());
        pharmacist.setFirstTimePassword(true);
        pharmacist.setAddress(address);
        pharmacist.setGender(Gender.FEMALE);
       pharmacist.setPhoneNumber("");
        pharmacist.setLicenseNumber("");
        pharmacist.setRole(Role.Values.Pharmacist);
        return pharmacist;
    }

    public static Dermatologist createDermatologist1() {
        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setEmail("vlajkovj@gmail.com");
        dermatologist.setId(300L);
        dermatologist.setAverageGrade(new AverageGrade(20, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        dermatologist.setAuthorities(authorityList);
        Address address = new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2));
        address.setId(100L);
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(createPharmacy1(address));
        pharmacies.add(createPharmacy2());
        dermatologist.setPharmacies(pharmacies);
        dermatologist.setName("Jelena");
        dermatologist.setSurname("Vlajkov");
        dermatologist.setDateOfBirth(new Date());
        dermatologist.setFirstTimePassword(true);
        dermatologist.setGender(Gender.FEMALE);
        dermatologist.setPhoneNumber("");
        dermatologist.setLicenseNumber("");
        dermatologist.setAddress(new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2)));
        dermatologist.setRole(Role.Values.Dermatologist);
        return dermatologist;
    }

    public static Dermatologist createDermatologist2() {
        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setEmail("lanakapor@gmail.com");
        dermatologist.setId(300L);
        dermatologist.setAverageGrade(new AverageGrade(20, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        dermatologist.setAuthorities(authorityList);
        dermatologist.setName("Lana");
        List<Pharmacy> pharmacies = new ArrayList<>();
        Address address = new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2));
        address.setId(100L);
        pharmacies.add(createPharmacy1(address));
        dermatologist.setPharmacies(pharmacies);
        dermatologist.setSurname("Kapor");
        dermatologist.setDateOfBirth(new Date());
        dermatologist.setFirstTimePassword(true);
        dermatologist.setGender(Gender.FEMALE);
        dermatologist.setPhoneNumber("");
        dermatologist.setLicenseNumber("");
        dermatologist.setRole(Role.Values.Dermatologist);
        dermatologist.setAddress(new Address("Brace Radic 2",new City("Subotica"),new State("Srbija"),new Coordinates(22.2,22.2)));
        return dermatologist;
    }


    public static Dermatologist createDermatologist5(Address address, Pharmacy pharmacy) {
        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setEmail("vlajkovj@gmail.com");
        dermatologist.setId(300L);
        dermatologist.setAverageGrade(new AverageGrade(20, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        dermatologist.setAuthorities(authorityList);
        dermatologist.setAddress(address);
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(pharmacy);
        dermatologist.setPharmacies(pharmacies);
        dermatologist.setName("Jelena");
        dermatologist.setSurname("Vlajkov");
        dermatologist.setDateOfBirth(new Date());
        dermatologist.setFirstTimePassword(true);
        dermatologist.setGender(Gender.FEMALE);
        dermatologist.setPhoneNumber("");
        dermatologist.setLicenseNumber("");
       dermatologist.setRole(Role.Values.Dermatologist);
        return dermatologist;
    }

    public static PharmacyAdmin CreatePharmacyAdmin(Address address,Pharmacy pharmacy){
        PharmacyAdmin pharmacyAdmin=new PharmacyAdmin();
        pharmacyAdmin.setEmail("vojvodicd@gmail.com");
         List<Authority> authorityList = new ArrayList<>();
        pharmacyAdmin.setAuthorities(authorityList);
        pharmacyAdmin.setAddress(address);
        pharmacyAdmin.setPharmacy(pharmacy);
        pharmacyAdmin.setName("Danica");
        pharmacyAdmin.setSurname("Vojvodic");
        pharmacyAdmin.setDateOfBirth(new Date());
        pharmacyAdmin.setFirstTimePassword(true);
        pharmacyAdmin.setGender(Gender.FEMALE);
        pharmacyAdmin.setPhoneNumber("");
        pharmacyAdmin.setRole(Role.Values.PharmacyAdmin);
        return pharmacyAdmin;
    }

    public static Pharmacist createPharmacist5(Address address, Pharmacy pharmacy) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setEmail("vlajkovj@gmail.com");
        pharmacist.setId(300L);
        pharmacist.setAverageGrade(new AverageGrade(20, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        pharmacist.setAuthorities(authorityList);
        pharmacist.setAddress(address);
        pharmacist.setPharmacy(pharmacy);
        pharmacist.setName("Jelena");
        pharmacist.setSurname("Vlajkov");
        pharmacist.setDateOfBirth(new Date());
        pharmacist.setFirstTimePassword(true);
        pharmacist.setGender(Gender.FEMALE);
        pharmacist.setPhoneNumber("");
        pharmacist.setLicenseNumber("");
        pharmacist.setRole(Role.Values.Dermatologist);
        return pharmacist;
    }

    public static WorkDay createWorkday(MedicalStaff medicalStaff,Pharmacy pharmacy){
        WorkDay w = new WorkDay();
        w.setId(1L);
        w.setMedicalStaff(medicalStaff);
        w.setDate(new Date());
        w.setPharmacy(pharmacy);
        Date todaysDate = new Date();

        w.setWorkDayPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 0, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 0, 0)));
        return w;
    }
}

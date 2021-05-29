package com.atlaspharmacy.atlaspharmacy.pharmderm.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.PHARMACIST_ROLE;

public class PharmDerm {

    public static DrugReservation createDrugReservation(Pharmacy p) {
        DrugReservation drugReservation = new DrugReservation();
        Patient patient = new Patient();
        patient.setName("Ozren");
        patient.setEmail("vlajkovj31@gmail.com");
        Medication m = new Medication();
        m.setName("DulcoLax");
        drugReservation.setPharmacy(p);
        drugReservation.setExpirationDate(new Date((new Date()).getTime() + (1000 * 60 * 60 * 72)));
        drugReservation.setReservationDate(new Date());
        drugReservation.setUniqueIdentifier(1235);
        drugReservation.setId(1L);
        drugReservation.setPatient(patient);
        drugReservation.setMedication(m);
        drugReservation.setTherapyDays(10);
        drugReservation.setIssued(false);
        return drugReservation;
    }

    public static Pharmacist createPharmacist(Pharmacy p) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setEmail("vlajkovn@gmail.com");
        pharmacist.setPharmacy(p);
        pharmacist.setId(7L);
        pharmacist.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority(1L, PHARMACIST_ROLE));
        pharmacist.setAuthorities(authorityList);
        pharmacist.setName("Nadezda");
        pharmacist.setSurname("Vlajkov");
        pharmacist.setAddress(new Address());
        pharmacist.setDateOfBirth(new Date());
        pharmacist.setFirstTimePassword(true);
        pharmacist.setGender(Gender.FEMALE);
        pharmacist.setPhoneNumber("");
        pharmacist.setLicenseNumber("");
        pharmacist.setRole(Role.Values.Pharmacist);
        return pharmacist;
    }

    public static Dermatologist createDermatologist(Pharmacy p) {
        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setEmail("vlajkovn@gmail.com");
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(p);
        dermatologist.setPharmacies(pharmacies);
        dermatologist.setId(7L);
        dermatologist.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority(1L, PHARMACIST_ROLE));
        dermatologist.setAuthorities(authorityList);
        dermatologist.setName("Nadezda");
        dermatologist.setSurname("Vlajkov");
        dermatologist.setAddress(new Address());
        dermatologist.setDateOfBirth(new Date());
        dermatologist.setFirstTimePassword(true);
        dermatologist.setGender(Gender.FEMALE);
        dermatologist.setPhoneNumber("");
        dermatologist.setLicenseNumber("");
        dermatologist.setRole(Role.Values.Dermatologist);
        return dermatologist;
    }


    public static Pharmacy createPharmacy() {
        Pharmacy pharmacy = new Pharmacy(1L);
        pharmacy.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        return pharmacy;
    }

    public static Medication createMedication1() {
        Medication m = new Medication();
        m.setName("Xanax");
        m.setCode(123L);
        return m;
    }

    public static Medication createMedication2() {
        Medication m = new Medication();
        m.setName("Bromazepam");
        m.setCode(125L);
        return m;
    }

    public static Patient createPatient() {
        Patient p = new Patient();
        p.setName("Ozren");
        p.setSurname("Ozrenovic");
        p.setEmail("vlajkovj31@gmail.com");
        return p;
    }

    public static PharmacyStorage createPharmacyStorage1(Pharmacy p, Medication m) {
        PharmacyStorage pharmacyStorage = new PharmacyStorage();
        pharmacyStorage.setPharmacy(p);
        pharmacyStorage.setMedication(m);
        pharmacyStorage.setQuantity(0L);
        return pharmacyStorage;
    }


    public static PharmacyStorage createPharmacyStorage2(Pharmacy p, Medication m) {
        PharmacyStorage pharmacyStorage = new PharmacyStorage();
        pharmacyStorage.setPharmacy(p);
        pharmacyStorage.setMedication(m);
        pharmacyStorage.setQuantity(500L);
        return pharmacyStorage;
    }

    public static Counseling createAppointment(Pharmacy p, Patient pa, Pharmacist ph) {
        Counseling c = new Counseling();
        c.setPatient(pa);
        c.setPharmacy(p);
        c.setPharmacist(ph);
        c.setFinished(false);
        return c;
    }
}

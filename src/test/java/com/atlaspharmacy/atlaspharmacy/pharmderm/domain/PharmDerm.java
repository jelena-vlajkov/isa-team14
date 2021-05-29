package com.atlaspharmacy.atlaspharmacy.pharmderm.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;
import org.checkerframework.checker.units.qual.A;
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
        return pharmacist;
    }

    public static Pharmacy createPharmacy() {
        Pharmacy pharmacy = new Pharmacy(1L);
        pharmacy.setAverageGrade(new AverageGrade(1, 1, 1, 1, 1));
        return pharmacy;
    }
}

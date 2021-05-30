package com.atlaspharmacy.atlaspharmacy.pharmderm.domain;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.medicalrecord.domain.MedicalRecord;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
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

import static com.atlaspharmacy.atlaspharmacy.pharmderm.domain.ConstantValues.DERMATOLOGIST_ID;
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
        pharmacist.setId(500L);
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
        dermatologist.setId(DERMATOLOGIST_ID);
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
        Pharmacy pharmacy = new Pharmacy(100L);
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
        p.setId(1L);
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
        Date todaysDate = new Date();
        c.setType(AppointmentType.Values.Counseling);

        c.setAppointmentPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 0, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 30, 0)));

        c.setPharmacist(ph);
        c.setFinished(false);
        return c;
    }

    public static List<Counseling> createAppointments(Pharmacy p, Patient pa, Pharmacist ph) {
        Counseling c = new Counseling();
        c.setPatient(pa);
        c.setPharmacy(p);
        Date todaysDate = new Date();
        c.setType(AppointmentType.Values.Counseling);

        c.setAppointmentPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 0, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 12, 30, 0)));

        c.setPharmacist(ph);
        c.setFinished(false);
        List<Counseling> counselings = new ArrayList<>();

        Patient c1 = new Patient();
        c1.setId(2L);
        c1.setSurname("Ozrenovic");
        c1.setName("Marija");

        Counseling cs = new Counseling();
        cs.setPatient(pa);
        cs.setPharmacy(p);
        cs.setType(AppointmentType.Values.Counseling);

        cs.setAppointmentPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 0, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 30, 0)));

        cs.setPharmacist(ph);
        cs.setFinished(false);

        counselings.add(cs);

        Counseling cs2 = new Counseling();
        cs2.setPatient(pa);
        cs2.setPharmacy(p);
        cs2.setType(AppointmentType.Values.Counseling);

        cs2.setAppointmentPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 30, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 14, 0, 0)));

        cs2.setPharmacist(ph);
        cs2.setFinished(false);


        Patient c2 = new Patient();
        c2.setId(3L);
        c2.setName("Marija");
        c2.setSurname("Ozrenovic");
        cs.setPatient(c2);


        Counseling cs3 = new Counseling();
        cs3.setPatient(pa);
        cs3.setPharmacy(p);
        cs3.setType(AppointmentType.Values.Counseling);

        cs3.setAppointmentPeriod(new Period(new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 13, 30, 0),
                new Date(todaysDate.getYear(), todaysDate.getMonth(), todaysDate.getDate(), 14, 0, 0)));

        cs3.setPharmacist(ph);
        cs3.setFinished(false);


        Patient c5 = new Patient();
        c5.setId(6L);
        c5.setName("Jelena");
        c5.setSurname("Ozrenovic");
        cs3.setPatient(c5);
        counselings.add(cs3);
        return counselings;
    }

    public static MedicalRecord createMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(createPatient());
        medicalRecord.setIngredients(createIngredients1());
        medicalRecord.setAllergies(createAllergies1());
        return medicalRecord;
    }

    public static Medication createMedication3() {
        Medication m = new Medication();
        m.setId(1L);

        m.setName("Xanax");
        m.setIngredients(createIngredients2());
        List<Medication> substituteMedications = new ArrayList<>();
        substituteMedications.add(createMedication5());
        substituteMedications.add(createMedication4());
        m.setSubstituteMedication(substituteMedications);

        return m;
    }

    public static Medication createMedication4() {
        Medication m = new Medication();
        m.setId(1L);

        m.setName("Belbian");

        m.setIngredients(createIngredients3());
        List<Medication> substituteMedications = new ArrayList<>();
        substituteMedications.add(createMedication6());
        m.setSubstituteMedication(substituteMedications);

        return m;
    }

    public static Medication createMedication5() {
        Medication m = new Medication();
        m.setId(1L);

        m.setName("Bromazepam");

        m.setIngredients(createIngredients1());
        List<Medication> substituteMedications = new ArrayList<>();
        substituteMedications.add(createMedication6());
        m.setSubstituteMedication(substituteMedications);

        return m;
    }


    public static Medication createMedication6() {
        Medication m = new Medication();
        m.setId(1L);

        m.setName("Lexilium");

        m.setIngredients(createIngredients4());
        List<Medication> substituteMedications = new ArrayList<>();
        m.setSubstituteMedication(substituteMedications);

        return m;
    }


    public static List<Ingredient> createIngredients1() {
        Ingredient i = new Ingredient();

        i.setId(1L);
        i.setAllergies(createAllergies1());
        i.setName("Veoma otrovni ingridient");

        Ingredient i2 = new Ingredient();
        i2.setId(2L);
        i2.setAllergies(createAllergies1());
        i2.setName("Drugi veoma otrovni ingridient");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        ingredients.add(i2);

        return ingredients;
    }


    public static List<Ingredient> createIngredients2() {
        Ingredient i = new Ingredient();

        i.setId(3L);
        i.setAllergies(createAllergies2());
        i.setName("Treci veoma otrovni ingridient");

        Ingredient i2 = new Ingredient();
        i2.setId(4L);
        i2.setAllergies(createAllergies3());
        i2.setName("Cetvrti veoma otrovni ingridient");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        ingredients.add(i2);

        return ingredients;
    }


    public static List<Ingredient> createIngredients3() {
        Ingredient i = new Ingredient();

        i.setId(5L);
        i.setAllergies(createAllergies4());
        i.setName("Peti veoma otrovni ingridient");

        Ingredient i2 = new Ingredient();
        i2.setId(6L);
        i2.setAllergies(createAllergies3());
        i2.setName("Sesti veoma otrovni ingridient");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        ingredients.add(i2);

        return ingredients;
    }


    public static List<Ingredient> createIngredients4() {
        Ingredient i = new Ingredient();

        i.setId(7L);
        i.setAllergies(createAllergies4());
        i.setName("Sedmi veoma otrovni ingridient");

        Ingredient i2 = new Ingredient();
        i2.setId(8L);
        i2.setAllergies(createAllergies2());
        i2.setName("Osmi veoma otrovni ingridient");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        ingredients.add(i2);

        return ingredients;
    }

    public static List<Allergy> createAllergies1() {
        Allergy allergy = new Allergy();
        allergy.setId(1L);
        allergy.setName("Brufen");
        List<Allergy> a = new ArrayList<>();
        a.add(allergy);
        return a;
    }

    public static List<Allergy> createAllergies2() {
        Allergy allergy = new Allergy();
        allergy.setId(2L);
        allergy.setName("Brufen 2");
        List<Allergy> a = new ArrayList<>();
        a.add(allergy);
        return a;
    }

    public static List<Allergy> createAllergies3() {
        Allergy allergy = new Allergy();
        allergy.setId(3L);
        allergy.setName("Brufen 3");
        List<Allergy> a = new ArrayList<>();
        a.add(allergy);
        return a;
    }
    public static List<Allergy> createAllergies4() {
        Allergy allergy = new Allergy();
        allergy.setId(4L);
        allergy.setName("Brufen 4");
        List<Allergy> a = new ArrayList<>();
        a.add(allergy);
        return a;
    }

    public static List<Appointment> createRandomAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        Examination a1 = new Examination();
        Pharmacy p = new Pharmacy();
        a1.setPharmacy(createPharmacy());
        a1.setDermatologist(createDermatologist(p));
        Date date = new Date();
        a1.setAppointmentPeriod(new Period(
                new Date(date.getYear(), date.getMonth(), date.getDate(), 12, 0, 0),
                new Date(date.getYear(), date.getMonth(), date.getDate(), 12, 30, 0)
        ));
        a1.setType(AppointmentType.Values.Examination);

        a1.setPatient(createPatient());

        appointments.add(a1);

        Examination a2 = new Examination();
        a2.setPharmacy(createPharmacy());
        a2.setDermatologist(createDermatologist(p));

        a2.setType(AppointmentType.Values.Examination);
        a2.setAppointmentPeriod(new Period(
                new Date(date.getYear(), date.getMonth() + 2, date.getDate(), 12, 0, 0),
                new Date(date.getYear(), date.getMonth() + 2, date.getDate() , 12, 30, 0)
        ));

        a2.setPatient(createPatient());

        appointments.add(a2);

        Examination a3 = new Examination();
        a3.setPharmacy(createPharmacy());
        a3.setDermatologist(createDermatologist(p));

        a3.setAppointmentPeriod(new Period(
                new Date(date.getYear(), date.getMonth() + 1, date.getDate(), 12, 0, 0),
                new Date(date.getYear(), date.getMonth() + 1, date.getDate(), 12, 30, 0)
        ));

        a3.setPatient(createPatient());

        a3.setType(AppointmentType.Values.Examination);
        appointments.add(a3);

        Examination a4 = new Examination();
        a4.setPharmacy(createPharmacy());
        a4.setDermatologist(createDermatologist(p));

        a4.setAppointmentPeriod(new Period(
                new Date(date.getYear(), date.getMonth(), date.getDate(), 12, 30, 0),
                new Date(date.getYear(), date.getMonth(), date.getDate(), 13, 0, 0)
        ));

        a4.setType(AppointmentType.Values.Examination);
        a4.setPatient(createPatient());

        appointments.add(a4);


        Examination a5 = new Examination();
        a5.setPharmacy(createPharmacy());
        a5.setDermatologist(createDermatologist(p));

        a5.setType(AppointmentType.Values.Examination);
        a5.setAppointmentPeriod(new Period(
                new Date(date.getYear(), date.getMonth(), date.getDate(), 13, 0, 0),
                new Date(date.getYear(), date.getMonth(), date.getDate(), 13, 30, 0)
        ));

        a5.setPatient(createPatient());

        appointments.add(a5);

        return appointments;


    }



}

insert into addresses(id, city, latitude, longitude, state, street) values (100, 'Novi Sad', 45.2378437,19.8425568,'Serbia' ,'Bulevar Despota Stefana 3');
insert into addresses(id, city, latitude, longitude, state, street) values (200,'Novi Sad', 45.2469369, 19.8498081,'Serbia' ,'Bulevar Cara Lazara  20');

insert into addresses(id, city, latitude, longitude, state, street) values (300,'Belgrade',44.8058901,20.4585641,'Serbia','Nemanjina 6');

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 100,'2000-01-01 00:00:01',  'vlajkovj@gmail.com', true, 0, 'Jelena', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Vlajkov', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 500, '1986-01-01 00:00:01', 'vlajkovn@gmail.com', true, 0, 'Nadezda','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Vlajkov', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('PharmacyAdmin', 400, '1966-01-01 00:00:01', 'vojvodicd@gmail.com', true, 0, 'Danica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Vojvodic', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Dermatologist', 600,'1966-01-01 00:00:01', 'marko@gmail.com', true, 1, 'Marko', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Stantic', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 300, '1966-01-01 00:00:01', 'lazic@gmail.com', true, 1, 'Lazar', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 12125126, 'Lazic', 200);


insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 200, '1997-01-01 00:00:01', 'alexignjat1998@gmail.com', true, 1, 'Aleksandar', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Ignjatijevic',300);



insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('SysAdmin', 900, '1997-01-01 00:00:01', 'alexignjat@gmail.com', true, 1, 'Admin', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 9999999 ,'Admin', 300);
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('SysAdmin', 905, '1997-01-01 00:00:01', 'alex@gmail.com', false, 1, 'Admin', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 9999999 ,'Admin', 300);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 990, '1997-01-01 00:00:01', 'elit@gmail.com', true, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 300);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 991, '1997-01-01 00:00:01', 'elit1@gmail.com', true, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 300);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 992, '1997-01-01 00:00:01', 'elit3@gmail.com', false, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 300);


insert into pharmacies(id, average_grade, description, name, address_id) values (100, 3.4, 'Talala', 'Apoteka Jankovic', 100);
insert into pharmacies(id, average_grade, description, name, address_id) VALUES (200, 5.0,'ldkjdljf','ZEGIN',200);
insert into pharmacies(id, average_grade, description, name, address_id) VALUES (300, 5.0,'ldkjdljf','APOTEKA BENU',300);

insert into pharmacy_admins(id, pharmacy_id) values(400,200);
insert into medicalstaff(license_number, id) values ('16657568',100);
insert into medicalstaff(license_number, id) values ('243532',600);
insert into medicalstaff(license_number, id) values ('333',300);
insert into medicalstaff(license_number, id) values('1241241', 500);

insert into pharmacists(id, pharmacy_id) values (500, 100);

insert into patients(enabled, verification_code, id) values (true, '98a6sxiy9a8sh9as7f8asbf9asfaosjbfn8a7sfgb', 200);

insert into dermatologists(id) values (100);
insert into dermatologists(id) values (600);
insert into dermatologists(id) values (300);

insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100,100);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100,200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (600,200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (300,200);

insert into sysadmins(id) values(900);
insert into sysadmins(id) values(905);

insert into suppliers(firm_name, id) VALUES ('ElitNS', 990);
insert into suppliers(firm_name, id) VALUES ('ElitNS2', 991);
insert into suppliers(firm_name, id) VALUES ('ElitNS2', 992);

insert into authority(id, name) values (1, 'ROLE_PHARMACIST');
insert into authority(id, name) values (2, 'ROLE_DERMATOLOGIST');
insert into authority(id, name) values (3, 'ROLE_PATIENT');
insert into authority(id, name) values (4, 'ROLE_SYSADMIN');
insert into authority(id, name) values (5, 'ROLE_PHARMACYADMIN');
insert into authority(id, name) values (6, 'ROLE_MEDICALSTAFF');
insert into authority(id, name) values (7, 'ROLE_SUPPLIER');

insert into user_authority(user_id, authority_id) values (100, 2);
insert into user_authority(user_id, authority_id) values (600, 3);
insert into user_authority(user_id, authority_id) values (500, 1);
insert into user_authority(user_id, authority_id) VALUES (900, 4);
insert into user_authority(user_id, authority_id) VALUES (905, 4);

insert into user_authority(user_id, authority_id) VALUES (990, 7);
insert into user_authority(user_id, authority_id) VALUES (991, 7);
insert into user_authority(user_id, authority_id) VALUES (992, 7);

insert into workdays(id, date, start_time, end_time, medical_staff_id) values (1, '2021-02-10', 10, 18, 100);
insert into appointment(type, id, appointment_end_time, appointment_start_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling',100, '2021-02-10 12:00:00', '2021-02-10 12:30:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 200, '2021-02-10 13:00:00', '2021-02-10 13:30:00', 1000.00,false, 100,500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 300, '2021-02-10 13:30:00', '2021-02-10 14:00:00', 1000.00, false, 100,500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 400, '2021-02-10 14:00:00', '2021-02-10 14:30:00', 1000.00, false, 100,100, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 500, '2021-02-10 14:30:00', '2021-02-10 15:00:00', 1000.00,false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 600, '2021-02-10 15:30:00', '2021-02-10 16:00:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 700, '2021-02-10 16:30:00', '2021-02-10 17:00:00', 1000.00, false,100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 800, '2021-02-05 11:00:00', '2021-02-05 11:30:00', 1000.00, false, 100,  100, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 900, '2021-02-11 12:00:00', '2021-02-10 12:30:00', 1000.00, false, 100, 100, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 1000, '2021-02-11 13:00:00', '2021-02-10 13:30:00', 1000.00, false, 100, 100, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 1100, '2021-02-12 13:30:00', '2021-02-10 14:00:00', 1000.00, false, 100, 100, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 1200, '2021-02-12 14:00:00', '2021-02-10 14:30:00', 1000.00, false, 100, 100, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1300, '2021-02-12 14:30:00', '2021-02-10 15:00:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1400, '2021-02-13 15:30:00', '2021-02-10 16:00:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1500, '2021-02-13 16:30:00', '2021-02-10 17:00:00', 1000.00, false, 100,500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1600, '2021-02-13 11:00:00', '2021-02-11 11:30:00', 1000.00, false, 100, 500, 200);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1700, '2021-02-03 12:00:00', '2021-02-03 12:30:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1800, '2021-02-02 13:00:00', '2021-02-02 13:30:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id) values ('Counseling', 1900, '2021-02-07 13:30:00', '2021-02-07 14:00:00', 1000.00, false, 100, 500, 200);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id) values ('Examination', 2000, '2021-02-01 14:00:00', '2021-02-01 14:30:00', 1000.00, false, 100, 100, 200);

insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (100, 'Od ovog leka ce te bole ledja', 123401231 ,'Moguc bol u predelu donjih ledja, nista strasno, nije rak rlx', 2, 1, 1, 0, 'Borozepam', 'Loncar Doo', 1,2.4, 6);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (200, 'Lek nije namenjen maloletnim licima',123401232 ,'Moguca pospanost', 2, 1, 1, 1, 'Xanax', 'Pfizer', 1, 4.4,3);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade,dosage) values (300, null, 123401233,null, 2, 1, 1, 2, 'Eferalgan', 'Galenika a.d.', 0,3, 800);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (400, 'Ne piti na prazan stomak', 123401234, null, 2, 1, 1, 3, 'Andol', 'Pliva Hrvatska d.o.o.', 0, 1, 200);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade,dosage) values (500, 'Ne piti na prazan stomak', 123401235, 'Moguc osecaj nesvestice', 2, 1, 1, 0, 'Caffetine', 'Alkaloid AD-Skopje', 0, 5, 250);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade,dosage) values (600, null, 123401236,'Moguca pospanost, umor', 2, 1, 1, 1, 'Brufen', 'Galenika a.d.', 0, 3.4, 500);

insert into substitute_medications(original_id, substitute_id) VALUES (100, 300);
insert into substitute_medications(original_id, substitute_id) VALUES (100, 400);
insert into substitute_medications(original_id, substitute_id) VALUES (300, 100);
insert into substitute_medications(original_id, substitute_id) VALUES (300, 500);
insert into substitute_medications(original_id, substitute_id) VALUES (300, 600);
insert into substitute_medications(original_id, substitute_id) VALUES (400, 300);
insert into substitute_medications(original_id, substitute_id) VALUES (400, 500);
insert into substitute_medications(original_id, substitute_id) VALUES (400, 600);
insert into substitute_medications(original_id, substitute_id) VALUES (600, 300);
insert into substitute_medications(original_id, substitute_id) VALUES (600, 400);
insert into substitute_medications(original_id, substitute_id) VALUES (600, 500);
insert into substitute_medications(original_id, substitute_id) VALUES (600, 100);


insert into ingredients(id, name) VALUES (100, 'paracetamol');
insert into ingredients(id, name) VALUES (200, 'kofein');
insert into ingredients(id, name) VALUES (300, 'kodeinfosfat');
insert into ingredients(id, name) VALUES (400, 'seskvidhidrat');
insert into ingredients(id, name) VALUES (500, 'propifenazon');
insert into ingredients(id, name) VALUES (600, 'ibuprofena');
insert into ingredients(id, name) VALUES (700, 'bromazepam');
insert into ingredients(id,name) values (800, 'alprazolam');
insert into ingredients(id,name) values (900, 'acetilsalicilna kiselina');


insert into medication_ingredients(medication_id, ingredient_id) VALUES (100, 700);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (200, 700);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (300, 100);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (400, 900);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (500, 200);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (500, 300);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (500, 400);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (500, 700);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (600, 600);



--
-- insert into drugreservation(id, expiration_date, issued, reservation_date, unique_identifier, medication_id, patient_id, pharmacy_id) VALUES (100, '2021-01-21 12:00:00', false, '2021-01-04 12:00:00', 123455, 200, 200, 100);
-- insert into drugreservation(id, expiration_date, issued, reservation_date, unique_identifier, medication_id, patient_id, pharmacy_id) VALUES (200, '2021-01-05 12:00:00', true, '2021-01-04 12:00:00', 123456, 100, 200, 100);
-- insert into drugreservation(id, expiration_date, issued, reservation_date, unique_identifier, medication_id, patient_id, pharmacy_id) VALUES (300, '2021-01-03 12:00:00', true, '2021-01-01 12:00:00', 123457, 300, 200, 200);

insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id) values (100, '2021-01-09 12:00:00', 'Aleksandar','Ignjatijevic' , 200, 100);
insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id) values (200, '2021-01-05 12:00:00', 'Aleksandar','Ignjatijevic' , 200, 200);
insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id) values (300, '2021-01-04 12:00:00', 'Aleksandar','Ignjatijevic' , 200, 100);
insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id) values (400, '2021-01-01 12:00:00', 'Aleksandar','Ignjatijevic' , 200, 200);

insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (100, 2, 100, 100);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (200, 1, 100, 200);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (300, 4, 100, 200);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (400, 2, 200, 300);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (500, 3, 200, 300);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (600, 5, 200, 400);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (700, 1, 300, 500);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (800, 1, 300, 600);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (900, 1, 400, 300);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (1000, 4, 400, 200);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id) VALUES (1100, 3, 400, 200);

--
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) VALUES (1, 1);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (1,2);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (1, 3);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) VALUES (2, 4);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (2,5);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (2, 6);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) VALUES (3, 7);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (3,8);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (3, 9);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) VALUES (4, 10);
-- insert into e_prescriptions_prescribed_drug_list(eprescription_id, prescribed_drug_list_id) values (4,11);

insert into allergies(id, name) values (100, 'Rash');
insert into allergies(id, name) values (200, 'Red skin');
insert into allergies(id, name) values (300, 'Fainting');
insert into allergies(id, name) values (400, 'Nausea');
insert into allergies(id, name) values (500, 'Vomiting');
insert into allergies(id, name) values (600, 'Diarrhea');


insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (200, 300);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (200, 400);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (200, 500);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (200, 600);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (600, 100);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (600, 200);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (600, 300);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (600, 400);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (600, 500);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (600, 600);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (900, 100);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (900, 200);

insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id) values (100, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 123, 200, 300, true, 100);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id) values (200, '2021-01-28 12:00:00', '2021-01-28 12:00:00', 1234, 200,300, true, 200);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id) values (300, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1235, 200, 300, false, 300);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id) values (400, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1236, 200, 300, false, 400);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id) values (500, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237, 200, 300, false, 500);

insert into medical_record(id, patient_id) values (100, 200);

insert into medical_record_ingredients(medical_record_id, ingredients_id) values (100, 900);

insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (100, 100, 100, 990);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (200, 200, 75, 990);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (300, 400, 50, 990);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (400, 600, 25, 990);

insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (500, 300, 100, 991);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (600, 500, 200, 991);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (700, 200, 50, 991);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (800, 100, 250, 991);

insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (100, '2021-02-26 12:00:00','2021-02-05 12:00:00' ,531282,100);
insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (200, '2021-02-25 12:00:00', '2021-02-08 12:00:00', 531283,100);
insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (300, '2021-02-24 12:00:00', '2021-02-21 12:00:00', 531284,100);
insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (400, '2021-02-22 12:00:00', '2021-02-20 12:00:00', 531285, 100);

insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (500, '2021-02-26 12:00:00', '2021-02-22 12:00:00',531286,200);
insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (600, '2021-02-22 12:00:00','2021-02-21 12:00:00',531287 ,200);
insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (700, '2021-02-22 12:00:00','2021-02-27 12:00:00', 531289 ,200);
insert into orders(id, due_date, editable_due, uniqueidentifier, pharmacy_id) VALUES (800, '2021-02-27 12:00:00', '2021-02-01 12:00:00',531288 ,200);


insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (100, 100, 190, 100);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (200, 200, 30, 100);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (300, 300, 19, 100);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (400, 400, 20, 100);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (500, 200, 30, 200);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (600, 100, 19, 200);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (700, 300, 25, 200);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (800, 500, 11, 200);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (900, 100, 6, 300);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1000, 200, 66, 300);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1100, 400, 3, 300);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1200, 200, 12, 400);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1300, 400, 78, 400);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1400, 200, 13, 500);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1500, 300, 64, 500);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1600, 100, 55, 600);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1700, 200, 19, 600);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1800, 300, 28, 700);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (1900, 200, 12, 700);

insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (2000, 200, 55, 800);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (2100, 100, 15, 800);
insert into orderedmedication(id, medication_id, quantity, order_id) VALUES (2200, 400, 30, 800);


insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (100,'2021-02-23 12:00:00', 0, 9000, 8126302 ,200, 991);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (101,'2021-02-22 12:00:00', 0, 6000,8126303 ,300, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (102, '2021-02-19 12:00:00', 0, 3800,8126304 ,700, 991);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (103, '2021-02-26 12:00:00', 0, 3230,8126305 ,800, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (104, '2021-02-19 12:00:00', 0, 3800,8126304 ,400, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (105, '2021-02-26 12:00:00', 0, 3230,8126305 ,500, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (106, '2021-02-26 12:00:00', 1, 3230,8126305 ,100, 990);


insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (100, false, 'pharmacy', 'Apoteka je jako ruzna i smara me raspored lekova', 200, 200);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (200, false, 'pharmacy', 'Osecam se uvredjeno jer je boja apoteke ruzna', 100, 200);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (300, true, 'pharmacy', 'Da li je moguce da ova apoteka nema lek koji meni treba??????????????', 300, 200);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (400, false, 'pharmacist', 'HAOS LOS FARMACEUT', 500, 200);


insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (100, 120, 100, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (200, 60, 200, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (300, 55, 300, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (400, 10, 400, 100);


insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (500, 111, 200, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (600, 66, 400, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (700, 42, 600, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (800, 69, 100, 200);

insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (900, 666, 300, 300);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1000, 90, 400, 300);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1100, 96, 500, 300);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1200, 29, 200, 300);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (100, 800, '2021-05-26 12:00:00', '2021-01-26 12:00:00', 100, 100);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (200, 560, '2021-05-26 12:00:00', '2021-01-26 12:00:00', 200, 100);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (300, 330, '2021-05-26 12:00:00', '2021-01-26 12:00:00', 300, 100);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (400, 580, '2021-05-26 12:00:00', '2021-01-26 12:00:00', 400, 100);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (500, 920, '2021-02-12 12:00:00', '2021-01-26 12:00:00', 200, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (600, 600, '2021-05-26 12:00:00', '2021-01-26 12:00:00', 400, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (700, 130, '2021-02-26 12:00:00', '2021-01-26 12:00:00', 600, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (800, 978, '2021-03-26 12:00:00', '2021-01-26 12:00:00', 100, 200);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (900, 123, '2021-05-26 12:00:00', '2021-01-23 12:00:00', 300, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1000, 748, '2021-05-26 12:00:00', '2021-01-12 12:00:00', 400, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1100, 639, '2021-05-26 12:00:00', '2021-01-09 12:00:00', 500, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1200, 826, '2021-05-26 12:00:00', '2021-01-09 12:00:00', 200, 300);
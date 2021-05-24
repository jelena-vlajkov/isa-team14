insert into addresses(id, city, latitude, longitude, state, street) values (100, 'Novi Sad', 45.2378437,19.8425568,'Serbia' ,'Bulevar Despota Stefana 3');
insert into addresses(id, city, latitude, longitude, state, street) values (200,'Novi Sad', 45.2469369, 19.8498081,'Serbia' ,'Bulevar Cara Lazara  20');
insert into addresses(id, city, latitude, longitude, state, street) values (300,'Belgrade',44.8058901,20.4585641,'Serbia','Nemanjina 6');
insert into addresses(id, city, latitude, longitude, state, street) values (400, 'Belgrade', 44.8131367,20.4822037, 'Serbia', 'Zdravka Čelara 12');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (500, 'Belgrade', 44.82730429999999,20.4065613,'Serbia','Pariske komune 22');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (600, 'Belgrade',44.8097207,20.479209,'Serbia','Dalmatinska 66');
insert into addresses(id, city, latitude, longitude, state, street) values (700,'Novi Sad', 45.257674,19.8433991,'Serbia','Svetozara Miletića 43');
insert into addresses(id, city, latitude, longitude, state, street) values (800,'Niš',43.3272899,21.9067072,'Serbia','Nišavska 7');
insert into addresses(id, city, latitude, longitude, state, street) values (900,'Novi Sad', 45.2409824,19.7988723,'Serbia','Ulica Petefi Šandora 7');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (1000, 'Belgrade',44.8141966,20.4251799,'Serbia','Antifašističke borbe 66');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (1100, 'Belgrade',44.7970806,20.4785418,'Serbia','Vojvode Dragomira 22');
insert into addresses(id, city, latitude, longitude, state, street) values (1200,'Novi Sad', 45.2904728,19.8167586,'Serbia','Milice Nikolić 10');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (1300, 'Belgrade',44.8037537,20.4789001,'Serbia','Bulevar kralja Aleksandra 124');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (1400, 'Belgrade', 44.79211069999999,20.4856736,'Serbia','Gospodara Vučića 123');
insert into addresses(id, city, latitude, longitude, state, street) VALUES (1500, 'Beograd',44.817433,20.4181654,'Serbia','Bulevar Zorana Đinđića 123');

-- ZA HESIRANJE KORISCEN JE BCryptPasswordEncoderpromoti
-- SVIM KORISNICIMA SU SIFRE: 12345



insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 100,'2000-01-01 00:00:01',  'vlajkovj@gmail.com', true, 0, 'Jelena', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Vlajkov', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 1100,'2000-01-01 00:00:01',  'zz@gmail.com', true, 0, 'Zoran', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Zoric', 100 );


insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 500, '1986-01-01 00:00:01', 'vlajkovn@gmail.com', true, 0, 'Nadezda','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Vlajkov', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 5500, '1986-01-01 00:00:01', 'vlajkovt@gmail.com', false, 0, 'Tamara','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Vlajkov', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('PharmacyAdmin', 400, '1966-01-01 00:00:01', 'vojvodicd@gmail.com', true, 0, 'Danica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Vojvodic', 300);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Dermatologist', 600,'1966-01-01 00:00:01', 'marko@gmail.com', true, 1, 'Marko', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Stantic', 400);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 300, '1966-01-01 00:00:01', 'lazic@gmail.com', true, 1, 'Lazar', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 12125126, 'Lazic', 500);


insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 200, '1997-01-01 00:00:01', 'danica.vojvodic1234@gmail.com', true, 1, 'Aleksandar', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Ignjatijevic',600);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 1300, '1997-01-01 00:00:01', 'danica123.vojvodic@gmail.com', true, 1, 'Danica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Vojvodic',600);



insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('SysAdmin', 900, '1997-01-01 00:00:01', 'alexignjat@gmail.com', true, 1, 'Admin', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 9999999 ,'Admin', 700);
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('SysAdmin', 905, '1997-01-01 00:00:01', 'alex@gmail.com', false, 1, 'Admin', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 9999999 ,'Admin', 800);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 990, '1997-01-01 00:00:01', 'elit@gmail.com', true, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 900);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 991, '1997-01-01 00:00:01', 'elit1@gmail.com', true, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 1000);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 992, '1997-01-01 00:00:01', 'elit3@gmail.com', false, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 1100);



insert into pharmacies(id, excellent,good,poor,very_good,very_poor, description, email, name, telephone, address_id) values (100, 1,2,3,4,5, 'Prodicna apoteka osnovana od starne pozrtvovane porodice Jankovic. Nas moto je pls kupujte kod nas :D', 'jankovicapoteka@gmail.com' ,'Apoteka Jankovic', 01212432 ,1500);
insert into pharmacies(id, excellent,good,poor,very_good,very_poor, description, email, name, telephone, address_id) VALUES (200, 1,1,1,0,0,'Nmp uleteli smo na trziste ko ludi, kupili veliki broj lokala i sad ono, zatvaramo polako.','zegin@gmail.com','ZEGIN',011123417,1400);
insert into pharmacies(id, excellent,good,poor,very_good,very_poor, description, email, name, telephone, address_id) VALUES (300, 1,2,3,4,5,'Veoma moderna apoteka, nudi usluge i preko donesi.com aplikacije.', 'benu@gmail.com','Apoteka Benu',91236234,1300);
insert into pharmacies(id, excellent,good,poor,very_good,very_poor, description, email, name, telephone, address_id) VALUES (400, 2,2,3,8,0,'Jedna od najstarijih apoteka u Novom Sadu i jedna od poslednjih koja aktivno saradjuje sa vojnom bolnicom.','laurusns@hotmail.com','Apoteka Laurus',0124532,1200);


insert into pharmacy_admins(id, pharmacy_id) values(400,200);

insert into medicalstaff(license_number, id) values ('16657568',100);
insert into medicalstaff(license_number, id) values ('243532',600);
insert into medicalstaff(license_number, id) values ('333666',300);
insert into medicalstaff(license_number, id) values('1241241', 500);
insert into medicalstaff(license_number, id) values('124124341', 5500);
insert into medicalstaff(license_number, id) values('1241255541', 1100);


insert into pharmacists(id,pharmacy_id,excellent,good,poor,very_good,very_poor) values (500,100,1,2,3,4,5);
insert into pharmacists(id,pharmacy_id,excellent,good,poor,very_good,very_poor) values (5500,200,1,1,1,1,1);
-- random character generator will generate verification code that will be added to database
-- when patient activates account, code is removed

insert into patients(enabled, verification_code, id) values (true, null, 200);
insert into patients(enabled, verification_code, id) values (true, null, 1300);


insert into dermatologists(id,excellent,good,poor,very_good,very_poor) values (100,1,2,3,4,5);
insert into dermatologists(id,excellent,good,poor,very_good,very_poor) values (600,1,1,1,1,1);
insert into dermatologists(id,excellent,good,poor,very_good,very_poor) values (300,2,4,6,8,4);
insert into dermatologists(id,excellent,good,poor,very_good,very_poor) values (1100,1,1,1,3,5);


insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100,100);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100,200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (600,200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (300,200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (1100,200);

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

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (100, '2021-05-18', '2021-05-18 12:00:00', '2021-05-18 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (101, '2021-05-19', '2021-05-19 12:00:00', '2021-05-19 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (102, '2021-05-20', '2021-05-20 12:00:00', '2021-05-20 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (103, '2021-05-21', '2021-05-21 12:00:00', '2021-05-21 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (104, '2021-05-22', '2021-05-22 12:00:00', '2021-05-22 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (105, '2021-05-23', '2021-05-23 12:00:00', '2021-05-23 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (107, '2021-05-24', '2021-05-24 12:00:00', '2021-05-24 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (108, '2021-05-25', '2021-05-25 12:00:00', '2021-05-25 15:30:00', 500, 100);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (109, '2021-05-26', '2021-05-26 12:00:00', '2021-05-26 15:30:00', 500, 100);
insert into appointment(type, id, appointment_end_time, appointment_start_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling',100, '2021-02-10 12:00:00', '2021-02-10 12:30:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 200, '2021-02-10 13:00:00', '2021-02-10 13:30:00', 1000.00,false, 100,500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 300, '2021-02-10 13:30:00', '2021-02-10 14:00:00', 1000.00, false, 100,500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 400, '2021-02-10 14:00:00', '2021-02-10 14:30:00', 1000.00, false, 100,100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 500, '2021-02-10 14:30:00', '2021-02-10 15:00:00', 1000.00,false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 600, '2021-02-10 15:30:00', '2021-02-10 16:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 700, '2021-02-10 16:30:00', '2021-02-10 17:00:00', 1000.00, false,100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 800, '2021-02-05 11:00:00', '2021-02-05 11:30:00', 1000.00, false, 100,  100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 900, '2021-02-11 12:00:00', '2021-02-10 12:30:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1000, '2021-02-11 13:00:00', '2021-02-10 13:30:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1100, '2021-02-12 13:30:00', '2021-02-10 14:00:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1200, '2021-02-12 14:00:00', '2021-02-10 14:30:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1300, '2021-02-12 14:30:00', '2021-02-10 15:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1400, '2021-02-13 15:30:00', '2021-02-10 16:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1500, '2021-02-13 16:30:00', '2021-02-10 17:00:00', 1000.00, false, 100,500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1600, '2021-02-13 11:00:00', '2021-02-11 11:30:00', 1000.00, false, 100, 500, 200, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1700, '2021-05-17 12:00:00', '2021-05-17 12:30:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1800, '2021-05-17 13:00:00', '2021-05-17 13:30:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1900, '2021-05-17 13:30:00', '2021-05-17 14:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2000, '2021-02-01 14:00:00', '2021-02-01 14:30:00', 1000.00, false, 100, 100, 200, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 501, '2021-05-18 14:30:00', '2021-05-18 15:00:00', 1000.00,false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 601, '2021-05-19 15:30:00', '2021-05-19 16:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 701, '2021-05-18 16:30:00', '2021-05-18 17:00:00', 1000.00, false,100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 801, '2021-05-18 11:00:00', '2021-05-18 11:30:00', 1000.00, false, 100,  100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 901, '2021-05-19 12:00:00', '2021-05-19 12:30:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1001, '2021-05-20 13:00:00', '2021-05-20 13:30:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1101, '2021-05-19 13:30:00', '2021-05-19 14:00:00', 1000.00, false, 100, 100, 200 , false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1201, '2021-05-19 14:00:00', '2021-02-19 14:30:00', 1000.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1301, '2021-05-20 14:30:00', '2021-05-20 15:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1401, '2021-05-20 15:30:00', '2021-05-20 16:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1501, '2021-05-19 16:30:00', '2021-05-19 17:00:00', 1000.00, false, 100,500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1601, '2021-05-18 11:00:00', '2021-05-18 11:30:00', 1000.00, false, 100, 500, 200, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1702, '2021-05-22 12:00:00', '2021-05-22 12:30:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1802, '2021-05-22 13:00:00', '2021-05-22 13:30:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1903, '2021-05-17 13:30:00', '2021-05-17 14:00:00', 1000.00, false, 100, 500, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2001, '2021-02-01 14:00:00', '2021-02-01 14:30:00', 1000.00, false, 100, 100, 200, false);

insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (100, 'Od ovog leka ce te bole ledja', 123401231 ,'Moguc bol u predelu donjih ledja, nista strasno, nije rak rlx', 2, 1, 1, 0, 'Bromazepam', 'Loncar Doo', 1,2.4, 6);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (15, '2021-02-10', '2021-02-10 12:00:00', '2021-02-10 15:30:00', 100,200);


/*moj test*/

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2100, '2021-05-18 14:00:00', '2021-05-18 14:30:00', 1200.00, false, 100, 100, 200, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2200, '2021-05-22 14:00:00', '2021-05-22 14:30:00', 1200.00, false, 100, 100, 200, false);


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

insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (100, 2, 100, 100, 10);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (200, 1, 100, 200, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (300, 4, 100, 200, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (400, 2, 200, 300, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (500, 3, 200, 300, 10);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (600, 5, 200, 400, 10);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (700, 1, 300, 500, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (800, 1, 300, 600, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (900, 1, 400, 300, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (1000, 4, 400, 200, 7);
insert into prescribed_drugs(id, quantity, eprescription_id, prescribed_medication_id, therapy_days) VALUES (1100, 3, 400, 200, 7);

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

insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (100, '2021-06-28 12:00:00', '2021-02-03 12:00:00', 123, 200, 300, true, 100, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (200, '2021-11-28 12:00:00', '2021-12-28 12:00:00', 1234, 200,300, true, 200, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (300, '2021-12-28 12:00:00', '2021-12-03 12:00:00', 1235, 200, 300, false, 300, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (400, '2021-10-28 12:00:00', '2021-11-03 12:00:00', 1236, 200, 300, false, 400, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (500, '2021-01-28 12:00', '2021-02-03 12:00:00', 1237, 200, 300, false, 500, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (600, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237, 200, 300, true, 500, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (700, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237, 200, 300, true, 500, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (800, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237, 200, 300, true, 500, 10);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (900, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237, 200, 300, true, 500, 10);

insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days) values (1000, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237, 200, 300, false, 500, 10);

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
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (102, '2021-02-19 12:00:00', 0, 3800,8126304 ,700, 991);

insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (101,'2021-02-22 12:00:00', 0, 6000,8126303 ,300, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (103, '2021-02-26 12:00:00', 0, 3230,8126305 ,800, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (104, '2021-02-19 12:00:00', 0, 3800,8126304 ,400, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (105, '2021-02-26 12:00:00', 0, 3230,8126305 ,500, 990);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (106, '2021-02-26 12:00:00', 1, 3230,8126305 ,100, 990);


insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (100, false, 'pharmacy', 'Apoteka je jako ruzna i smara me raspored lekova', 200, 200);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (200, false, 'pharmacy', 'Osecam se uvredjeno jer je boja apoteke ruzna', 100, 200);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (300, true, 'pharmacy', 'Da li je moguce da ova apoteka nema lek koji meni treba??????????????', 300, 200);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (400, false, 'pharmacist', 'HAOS LOS FARMACEUT', 500, 200);


insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (100, 0, 100, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (200, 20, 200, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (300, 0, 300, 100);
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

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (500, 920, '2021-07-12 12:00:00', '2021-01-26 12:00:00', 200, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (600, 600, '2021-07-26 12:00:00', '2021-01-26 12:00:00', 400, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (700, 130, '2021-07-26 12:00:00', '2021-01-26 12:00:00', 600, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (800, 978, '2021-07-26 12:00:00', '2021-01-26 12:00:00', 100, 200);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (900, 123, '2021-05-26 12:00:00', '2021-01-23 12:00:00', 300, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1000, 748, '2021-05-26 12:00:00', '2021-01-12 12:00:00', 400, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1100, 639, '2021-05-26 12:00:00', '2021-01-09 12:00:00', 500, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1200, 826, '2021-05-26 12:00:00', '2021-01-09 12:00:00', 200, 300);

insert into subscriptions(id,patient_id,pharmacy_id) values (100,200,200);
insert into subscriptions(id,patient_id,pharmacy_id) values (200,1300,200);

insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(200,'2021-07-20 12:00:00', '2021-01-23 12:00:00','Xanax na snizenju 200din.',200);
insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(300,'2021-05-26 12:00:00', '2021-01-23 12:00:00','Svi biodermini proizvodi na snizenju -35%.',200);
insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(400,'2021-05-26 12:00:00', '2021-01-23 12:00:00','Srecni uskrsnji praznici!Apoteka ZEGIN vam poklanja do -50% na ceo asortiman.',200);
insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(100,'2021-06-10 12:00:00', '2021-03-08 12:00:00','Povodom 8.marta svim zenama -20%.',200);


insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (100, 1000, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 100, 'Counseling');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (200, 750, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 100, 'Examination');
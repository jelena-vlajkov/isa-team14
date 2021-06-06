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


-- dermatologist
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 100,'1998-09-29 00:00:01',  'vlajkovj@gmail.com', false, 0, 'Jelena', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Vlajkov', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 200,'2000-01-01 00:00:01',  'vlajkovi@gmail.com', false, 0, 'Ivana', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Vlajkov', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 300,'2000-01-01 00:00:01',  'vlajkovm@gmail.com', true, 0, 'Masa', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Vlajkov', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 400,'2000-01-01 00:00:01',  'vlajkovt@gmail.com', true, 0, 'Teodora', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Vlajkov', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 500,'2000-01-01 00:00:01',  'zz@gmail.com', false, 0, 'Zoran', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',123124124, 'Zoric', 100 );

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Dermatologist', 600,'1966-01-01 00:00:01', 'marko@gmail.com', true, 1, 'Marko', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Stantic', 400);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('Dermatologist', 700, '1966-01-01 00:00:01', 'lazic@gmail.com', true, 1, 'Lazar', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 12125126, 'Lazic', 500);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Dermatologist', 800,'1966-01-01 00:00:01', 'marko@gmail.com', true, 1, 'Marko', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Zdrnic', 400);


-- pharmacists
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 900, '1986-01-01 00:00:01', 'vlajkovn@gmail.com', false, 0, 'Nadezda','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Vlajkov', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 1000, '1986-01-01 00:00:01', 'vlajkovg@gmail.com', true, 0, 'Tamara','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Vlajkov', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 1100, '1986-01-01 00:00:01', 'vlajkovz@gmail.com', false, 0, 'Lazo','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Lazic', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 1200, '1986-01-01 00:00:01', 'vlajkovq@gmail.com', false, 0, 'Jela','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Lazic', 200);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Pharmacist', 1300, '1986-01-01 00:00:01', 'vlajkovw@gmail.com', false, 0, 'Nikola','$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',9766098, 'Lazic', 200);

-- pharmacy admnin
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values('PharmacyAdmin', 1400, '1966-01-01 00:00:01', 'vojvodicd@gmail.com', false, 0, 'Danica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe',12125126, 'Vojvodic', 300);

-- patients

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 1500, '1997-01-01 00:00:01', 'stefan.aradjanin@gmail.com', true, 1, 'Aleksandar', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Ignjatijevic',600);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 1600, '1997-01-01 00:00:01', 'danica123.vojvodic@gmail.com', true, 0, 'Danica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Vojvodic',600);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 1700, '1997-01-01 00:00:01', 'stefan.aradjanin3@gmail.com', true, 1, 'Marko', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Ignjatijevic',600);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 1800, '1997-01-01 00:00:01', 'danica123.vojvodic2@gmail.com', true, 0, 'Milica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Vojvodic',600);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 1900, '1997-01-01 00:00:01', 'stefan.aradjanin2@gmail.com', true, 1, 'Nemanja', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Ignjatijevic',600);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('Patient', 2000, '1997-01-01 00:00:01', 'danica123.vojvodic3@gmail.com', true, 0, 'Dragica', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 1241251,'Vojvodic',600);

-- sys admin
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('SysAdmin', 2100, '1997-01-01 00:00:01', 'alexignjat@gmail.com', true, 1, 'Admin', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 9999999 ,'Admin', 700);
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
values ('SysAdmin', 2200, '1997-01-01 00:00:01', 'alex@gmail.com', false, 1, 'Admin', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 9999999 ,'Admin', 800);

-- suppliers
insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 2300, '1997-01-01 00:00:01', 'danica.vojvodic1234@gmail.com', true, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 900);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 2400, '1997-01-01 00:00:01', 'elit1@gmail.com', true, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 1000);

insert into users(role, id, date_of_birth, email, first_time_password, gender, name, password, phone_number, surname, address_id)
VALUES ('Supplier', 2500, '1997-01-01 00:00:01', 'elit3@gmail.com', false, 1, 'Pedjone', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 715128 ,'Predragovic', 1100);

insert into pharmacies(id, description, email, name, telephone, address_id, average_grade) values (100,'Prodicna apoteka osnovana od starne pozrtvovane porodice Jankovic. Nas moto je pls kupujte kod nas :D', 'jankovicapoteka@gmail.com' ,'Apoteka Jankovic', 01212432 ,1500, 2);
insert into pharmacies(id, description, email, name, telephone, address_id, average_grade) VALUES (200,'Nmp uleteli smo na trziste ko ludi, kupili veliki broj lokala i sad ono, zatvaramo polako.','zegin@gmail.com','ZEGIN',011123417,1400, 3);
insert into pharmacies(id, description, email, name, telephone, address_id, average_grade) VALUES (300, 'Veoma moderna apoteka, nudi usluge i preko donesi.com aplikacije.', 'benu@gmail.com','Apoteka Benu',91236234,1300, 4);
insert into pharmacies(id, description, email, name, telephone, address_id, average_grade) VALUES (400, 'Jedna od najstarijih apoteka u Novom Sadu i jedna od poslednjih koja aktivno saradjuje sa vojnom bolnicom.','laurusns@hotmail.com','Apoteka Laurus',0124532,1200, 5);


insert into pharmacy_admins(id, pharmacy_id) values(1400,200);

insert into medicalstaff(license_number, id) values ('16657568', 100);
insert into medicalstaff(license_number, id) values ('243532', 200);
insert into medicalstaff(license_number, id) values ('333666', 300);
insert into medicalstaff(license_number, id) values('1241241', 400);
insert into medicalstaff(license_number, id) values('124124341', 500);
insert into medicalstaff(license_number, id) values('1241255541', 600);
insert into medicalstaff(license_number, id) values('12415541', 700);
insert into medicalstaff(license_number, id) values('12415541', 800);
insert into medicalstaff(license_number, id) values('415541', 900);
insert into medicalstaff(license_number, id) values('415541', 1000);
insert into medicalstaff(license_number, id) values('415541', 1100);
insert into medicalstaff(license_number, id) values('415541', 1200);
insert into medicalstaff(license_number, id) values('415541', 1300);


insert into pharmacists(id, pharmacy_id, average_grade) values (900, 100, 3.0);
insert into pharmacists(id, pharmacy_id, average_grade) values (1000, 200, 5.0);
insert into pharmacists(id, pharmacy_id, average_grade) values (1100, 200, 4.0);
insert into pharmacists(id, pharmacy_id, average_grade) values (1200, 200, 3.0);
insert into pharmacists(id, pharmacy_id, average_grade) values (1300, 200, 2.0);

-- random character generator will generate verification code that will be added to database
-- when patient activates account, code is removed

insert into patients(enabled, verification_code, id) values (true, null, 1500);
insert into patients(enabled, verification_code, id) values (true, null, 1600);
insert into patients(enabled, verification_code, id) values (true, null, 1700);
insert into patients(enabled, verification_code, id) values (true, null, 1800);
insert into patients(enabled, verification_code, id) values (true, null, 1900);
insert into patients(enabled, verification_code, id) values (true, null, 2000);


insert into dermatologists(id, average_grade) values (100, 2);
insert into dermatologists(id, average_grade) values (200, 3);
insert into dermatologists(id, average_grade) values (300, 4);
insert into dermatologists(id, average_grade) values (400, 5);
insert into dermatologists(id, average_grade) values (500, 1);
insert into dermatologists(id, average_grade) values (600, 1);
insert into dermatologists(id, average_grade) values (700, 2);
insert into dermatologists(id, average_grade) values (800, 3);


insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100, 100);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100, 200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (200, 200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (300, 200);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (400, 100);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (100, 300);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (500, 100);
insert into dermatologist_to_pharmacies(dermatologist_id, pharmacy_id) values (400, 200);

insert into sysadmins(id) values(2100);
insert into sysadmins(id) values(2200);

insert into suppliers(firm_name, id) VALUES ('ElitNS', 2300);
insert into suppliers(firm_name, id) VALUES ('ElitNS2', 2400);
insert into suppliers(firm_name, id) VALUES ('ElitNS2', 2500);

insert into authority(id, name) values (1, 'ROLE_PHARMACIST');
insert into authority(id, name) values (2, 'ROLE_DERMATOLOGIST');
insert into authority(id, name) values (3, 'ROLE_PATIENT');
insert into authority(id, name) values (4, 'ROLE_SYSADMIN');
insert into authority(id, name) values (5, 'ROLE_PHARMACYADMIN');
insert into authority(id, name) values (6, 'ROLE_MEDICALSTAFF');
insert into authority(id, name) values (7, 'ROLE_SUPPLIER');

insert into user_authority(user_id, authority_id) values (100, 2);
insert into user_authority(user_id, authority_id) values (200, 2);
insert into user_authority(user_id, authority_id) values (300, 2);
insert into user_authority(user_id, authority_id) values (400, 2);
insert into user_authority(user_id, authority_id) values (500, 2);
insert into user_authority(user_id, authority_id) values (600, 2);
insert into user_authority(user_id, authority_id) values (700, 2);
insert into user_authority(user_id, authority_id) values (800, 2);

insert into user_authority(user_id, authority_id) values (900, 1);
insert into user_authority(user_id, authority_id) values (1000, 1);
insert into user_authority(user_id, authority_id) values (1100, 1);
insert into user_authority(user_id, authority_id) values (1200, 1);
insert into user_authority(user_id, authority_id) values (1300, 1);

insert into user_authority(user_id, authority_id) values (1400, 5);

insert into user_authority(user_id, authority_id) values (1500, 3);
insert into user_authority(user_id, authority_id) values (1600, 3);
insert into user_authority(user_id, authority_id) values (1700, 3);
insert into user_authority(user_id, authority_id) values (1800, 3);
insert into user_authority(user_id, authority_id) values (1900, 3);
insert into user_authority(user_id, authority_id) values (2000, 3);

insert into user_authority(user_id, authority_id) VALUES (2100, 4);
insert into user_authority(user_id, authority_id) VALUES (2200, 4);

insert into user_authority(user_id, authority_id) VALUES (2300, 7);
insert into user_authority(user_id, authority_id) VALUES (2400, 7);
insert into user_authority(user_id, authority_id) VALUES (2500, 7);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (100, '2021-05-31', '2021-05-31 12:00:00', '2021-05-31 15:30:00', 100, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (200, '2021-05-31', '2021-05-31 12:00:00', '2021-05-31 15:30:00', 900, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (300, '2021-06-05', '2021-06-05 12:00:00', '2021-05-31 15:30:00', 900, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (400, '2021-05-31', '2021-05-31 12:00:00', '2021-05-31 15:30:00', 900, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (500, '2021-06-01', '2021-06-01 12:00:00', '2021-06-01 15:30:00', 900, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (800, '2021-06-01', '2021-06-01 12:00:00', '2021-06-01 15:30:00', 100, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (600, '2021-06-02', '2021-06-02 12:00:00', '2021-06-02 15:30:00', 900, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (700, '2021-06-05', '2021-06-05 12:00:00', '2021-06-05 15:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (900, '2021-07-05', '2021-07-05 12:00:00', '2021-07-05 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (1100, '2021-07-05', '2021-07-05 12:00:00', '2021-07-05 17:30:00', 900, 100, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (1200, '2021-06-05', '2021-06-05 10:00:00', '2021-06-05 17:30:00', 100, 100, false);
--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (1600, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 100, 100, false);
--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (1700, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 100, 100, false);
--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (1800, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 100, 100, false);
--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (1900, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 100, 100, false);
--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2000, '2021-06-06', '2021-06-06 10:00:00', '2021-06-06 17:30:00', 100, 100, false);
--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2100, '2021-06-05', '2021-06-05 10:00:00', '2021-06-06 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2200, '2021-06-06', '2021-06-06 10:00:00', '2021-06-06 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2300, '2021-06-06', '2021-06-06 10:00:00', '2021-06-06 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2400, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2500, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2600, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2700, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2800, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (2900, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 900, 100, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3000, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3100, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 900, 100, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3200, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3300, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 900, 100, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3400, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3500, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 900, 100, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3600, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3700, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3800, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (3900, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4000, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 100, 100, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4100, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 900, 100, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4200, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4300, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 1000, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4400, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4500, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 1000, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4600, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4700, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 1000, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4800, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (4900, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 1000, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5000, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5100, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 1000, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5200, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5300, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 1000, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5400, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5500, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 1000, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5600, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5700, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 1000, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5800, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 200, 200, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (5900, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 1000, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6000, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6100, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 1100, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6200, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6300, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 1100, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6400, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6500, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 1100, 300, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6600, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6700, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 1100, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6800, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (6900, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 1100, 300, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7000, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7100, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 1100, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7200, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7300, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 1100, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7400, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7500, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 1100, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7600, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 300, 300, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7700, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 1100, 200, false);



insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7800, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (7900, '2021-06-07', '2021-06-07 10:00:00', '2021-06-07 17:30:00', 1200, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8000, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8200, '2021-06-08', '2021-06-08 10:00:00', '2021-06-08 17:30:00', 1200, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8300, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8400, '2021-06-09', '2021-06-09 10:00:00', '2021-06-09 17:30:00', 1200, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8500, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8600, '2021-06-10', '2021-06-10 10:00:00', '2021-06-10 17:30:00', 1200, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8700, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 300, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8800, '2021-06-11', '2021-06-11 10:00:00', '2021-06-11 17:30:00', 1200, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (8900, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9000, '2021-06-12', '2021-06-12 10:00:00', '2021-06-12 17:30:00', 1200, 200, false);


insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9100, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9200, '2021-06-13', '2021-06-13 10:00:00', '2021-06-13 17:30:00', 1200, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9300, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9400, '2021-06-14', '2021-06-14 10:00:00', '2021-06-14 17:30:00', 1200, 200, false);

insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9500, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 400, 400, false);
insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id, disabled) values (9600, '2021-06-15', '2021-06-15 10:00:00', '2021-06-15 17:30:00', 1200, 200, false);


insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 100, '2021-05-31 12:00:00', '2021-05-31 12:30:00', 1000.00, false, 200, 900, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 200, '2021-06-01 13:00:00', '2021-06-01 13:30:00', 1000.00, false, 100, 900, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 300, '2021-06-01 13:30:00', '2021-06-01 14:00:00', 1000.00, false, 100, 900, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 400, '2021-05-01 14:00:00', '2021-05-01 14:30:00', 1000.00, false, 200, 100, 1600, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 500, '2021-05-28 14:00:00', '2021-05-28 14:30:00', 1000.00, false, 200, 100, 1700, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 600, '2021-05-29 14:00:00', '2021-05-29 14:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 700, '2021-05-29 14:30:00', '2021-05-29 15:00:00', 1000.00, false, 100, 1000, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 800, '2021-05-31 11:00:00', '2021-05-31 11:30:00', 1000.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 900, '2021-05-31 11:30:00', '2021-05-31 12:00:00', 1000.00, false, 200, 100, 1800, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1000, '2021-05-31 12:00:00', '2021-05-31 12:30:00', 1000.00, false, 200, 300, 1900,true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1100, '2021-05-31 11:00:00', '2021-05-31 11:30:00', 1000.00, false, 100, 900, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1200, '2021-05-31 12:00:00', '2021-05-31 12:30:00', 1000.00, false, 100, 1000, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1300, '2021-05-31 13:00:00', '2021-05-31 13:30:00', 1000.00, false, 100, 1100, 1700, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1400, '2021-06-05 13:00:00', '2021-06-05 13:30:00', 1000.00, false, 100, 1100, 1700, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1500, '2021-06-05 14:00:00', '2021-06-05 14:30:00', 1000.00, false, 100, 1100, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1600, '2021-06-06 13:00:00', '2021-06-06 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 1700, '2021-06-06 14:00:00', '2021-06-06 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1800, '2021-06-06 11:00:00', '2021-06-06 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 1900, '2021-06-06 11:30:00', '2021-06-06 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2000, '2021-06-06 12:00:00', '2021-06-06 12:30:00', 1000.00, false, 100, 100, 1700, false);


insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 2100, '2021-06-07 13:00:00', '2021-06-07 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 2200, '2021-06-07 14:00:00', '2021-06-07 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2300, '2021-06-07 11:00:00', '2021-06-07 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2400, '2021-06-07 11:30:00', '2021-06-07 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2500, '2021-06-07 12:00:00', '2021-06-07 12:30:00', 1000.00, false, 100, 100, 1700, false);


insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 2600, '2021-06-08 13:00:00', '2021-06-08 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 2700, '2021-06-08 14:00:00', '2021-06-08 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2800, '2021-06-08 11:00:00', '2021-06-08 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2900, '2021-06-08 11:30:00', '2021-06-08 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 3000, '2021-06-08 12:00:00', '2021-06-08 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 3100, '2021-06-09 13:00:00', '2021-06-09 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 3200, '2021-06-09 14:00:00', '2021-06-09 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 3300, '2021-06-09 11:00:00', '2021-06-09 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 3400, '2021-06-09 11:30:00', '2021-06-09 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 3500, '2021-06-09 12:00:00', '2021-06-09 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 3600, '2021-06-10 13:00:00', '2021-06-10 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 3700, '2021-06-10 14:00:00', '2021-06-10 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 3800, '2021-06-10 11:00:00', '2021-06-10 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 3900, '2021-06-10 11:30:00', '2021-06-10 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 4000, '2021-06-10 12:00:00', '2021-06-10 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 4100, '2021-06-11 13:00:00', '2021-06-11 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 4200, '2021-06-11 14:00:00', '2021-06-11 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 4300, '2021-06-11 11:00:00', '2021-06-11 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 4400, '2021-06-11 11:30:00', '2021-06-11 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 4500, '2021-06-11 12:00:00', '2021-06-11 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 4600, '2021-06-12 13:00:00', '2021-06-12 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 4700, '2021-06-12 14:00:00', '2021-06-12 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 4800, '2021-06-12 11:00:00', '2021-06-12 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 4900, '2021-06-12 11:30:00', '2021-06-12 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 5000, '2021-06-12 12:00:00', '2021-06-12 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 5100, '2021-06-13 13:00:00', '2021-06-13 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 5200, '2021-06-13 14:00:00', '2021-06-13 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 5300, '2021-06-13 11:00:00', '2021-06-13 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 5400, '2021-06-13 11:30:00', '2021-06-13 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 5500, '2021-06-13 12:00:00', '2021-06-13 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 5600, '2021-06-14 13:00:00', '2021-06-14 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 5700, '2021-06-14 14:00:00', '2021-06-14 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 5800, '2021-06-14 11:00:00', '2021-06-14 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 5900, '2021-06-14 11:30:00', '2021-06-14 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 6000, '2021-06-14 12:00:00', '2021-06-14 12:30:00', 1000.00, false, 100, 100, 1700, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 6100, '2021-06-15 13:00:00', '2021-06-15 13:30:00', 1000.00, false, 100, 900, 1800, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 6200, '2021-06-15 14:00:00', '2021-06-15 14:30:00', 1000.00, false, 100, 900, 1900, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 6300, '2021-06-15 11:00:00', '2021-06-15 11:30:00', 1000.00, false, 100, 100, 2000, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 6400, '2021-06-15 11:30:00', '2021-06-15 12:00:00', 1000.00, false, 100, 100, 1500, false);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 6500, '2021-06-15 12:00:00', '2021-06-15 12:30:00', 1000.00, false, 100, 100, 1700, false);


insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 6600, '2021-06-08 14:00:00', '2021-06-08 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 6700, '2021-06-08 11:00:00', '2021-06-07 11:30:00', 1000.00, false, 200, 200, 1800, false);


insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 6800, '2021-06-09 14:00:00', '2021-06-09 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 6900, '2021-06-09 11:00:00', '2021-06-09 11:30:00', 1000.00, false, 200, 200, 2000, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 7000, '2021-06-10 14:00:00', '2021-06-10 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 7100, '2021-06-10 11:00:00', '2021-06-10 11:30:00', 1000.00, false, 200, 200, 1800, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 7200, '2021-06-07 14:00:00', '2021-06-07 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 7300, '2021-06-07 11:00:00', '2021-06-07 11:30:00', 1000.00, false, 200, 200, 1800, false);


insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 7400, '2021-06-11 14:00:00', '2021-06-11 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 7500, '2021-06-11 11:00:00', '2021-06-11 11:30:00', 1000.00, false, 200, 200, 1800, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 7600, '2021-06-12 14:00:00', '2021-06-12 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 7700, '2021-06-12 11:00:00', '2021-06-12 11:30:00', 1000.00, false, 200, 200, 1800, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 7800, '2021-06-13 14:00:00', '2021-06-13 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 7900, '2021-06-13 11:00:00', '2021-06-13 11:30:00', 1000.00, false, 200, 200, 1800, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 8000, '2021-06-14 14:00:00', '2021-06-14 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8100, '2021-06-14 11:00:00', '2021-06-14 11:30:00', 1000.00, false, 200, 200, 1800, false);



insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, pharmacist_id, patient_id, finished) values ('Counseling', 8200, '2021-06-15 14:00:00', '2021-06-15 14:30:00', 1000.00, false, 200, 1000, 1500, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8300, '2021-06-15 11:00:00', '2021-06-15 11:30:00', 1000.00, false, 200, 200, 1800, false);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8400, '2021-02-22 11:00:00', '2021-02-22 11:30:00', 1000.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8500, '2021-02-22 11:00:00', '2021-02-22 11:30:00', 1000.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8600, '2021-02-22 11:00:00', '2021-02-22 11:30:00', 1800.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8700, '2021-02-22 11:00:00', '2021-02-22 11:30:00', 1400.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8800, '2021-02-22 11:00:00', '2021-02-22 11:30:00', 130.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 8900, '2021-04-22 11:00:00', '2021-04-22 11:30:00', 1040.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9000, '2021-04-22 11:00:00', '2021-04-22 11:30:00', 1000.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9100, '2021-04-22 11:00:00', '2021-04-22 11:30:00', 1000.00, false, 200, 300, 1500, true);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9200, '2021-05-31 11:30:00', '2021-05-31 12:00:00', 1000.00, false, 200, 100, 1800, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9300, '2021-05-31 12:00:00', '2021-05-31 12:30:00', 1000.00, false, 200, 300, 1900,true);

insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9400, '2021-01-22 11:00:00', '2021-01-22 11:30:00', 140.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9500, '2021-01-22 11:00:00', '2021-01-22 11:30:00', 560.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9600, '2021-01-22 11:00:00', '2021-01-22 11:30:00', 660.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9700, '2021-01-22 11:00:00', '2021-01-22 11:30:00', 4540.00, false, 200, 300, 1500, true);
insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 9800, '2021-01-22 11:00:00', '2021-01-22 11:30:00', 100.00, false, 200, 300, 1500, true);


insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (100, 'Od ovog leka ce te bole ledja', 123401231 ,'Moguc bol u predelu donjih ledja, nista strasno, nije rak rlx', 2, 1, 1, 0, 'Bromazepam', 'Loncar Doo', 1, 2, 6);

--insert into workdays(id, date, workday_start_time, workday_end_time, medical_staff_id,pharmacy_id) values (15, '2021-02-10', '2021-02-10 12:00:00', '2021-02-10 15:30:00', 100,200);


/*moj test*/

--insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2100, '2021-05-18 14:00:00', '2021-05-18 14:30:00', 1200.00, false, 100, 100, 1500, false);
--insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2200, '2021-05-31 14:00:00', '2021-05-31 14:30:00', 1200.00, false, 100, 100, 1500, false);

--insert into appointment(type, id, appointment_start_time, appointment_end_time, cost, is_canceled, pharmacy_id, dermatologist_id, patient_id, finished) values ('Examination', 2300, '2021-05-31 14:00:00', '2021-05-31 14:30:00', 1200.00, false, 200, 100, 1500, true);



insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (200, 'Lek nije namenjen maloletnim licima',123401232 ,'Moguca pospanost', 2, 1, 1, 1, 'Xanax', 'Pfizer', 1, 3,3);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade,dosage) values (300, null, 123401233,null, 2, 1, 1, 2, 'Eferalgan', 'Galenika a.d.', 0, 1, 800);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade, dosage) values (400, 'Ne piti na prazan stomak', 123401234, null, 2, 1, 1, 3, 'Andol', 'Pliva Hrvatska d.o.o.', 0, 5, 200);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade,dosage) values (500, 'Ne piti na prazan stomak', 123401235, 'Moguc osecaj nesvestice', 2, 1, 1, 0, 'Caffetine', 'Alkaloid AD-Skopje', 0, 5, 250);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing, grade,dosage) values (600, null, 123401236,'Moguca pospanost, umor', 2, 1, 1, 1, 'Brufen', 'Galenika a.d.', 0, 4, 500);

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





insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id, type) values (100, '2021-01-09 12:00:00', 'Aleksandar','Ignjatijevic' , 1500, 100, 'Rejected');
insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id, type) values (200, '2021-01-05 12:00:00', 'Aleksandar','Ignjatijevic' , 1500, 200, 'New');
insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id, type) values (300, '2021-01-04 12:00:00', 'Aleksandar','Ignjatijevic' , 1500, 100, 'New');
insert into e_prescriptions(id, date, name, surname, patient_id, pharmacy_id, type) values (400, '2021-01-01 12:00:00', 'Aleksandar','Ignjatijevic' , 1500, 200, 'Processed');

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


insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (100, '2021-06-28 12:00:00', '2021-02-03 12:00:00', 123555, 1500, 300, true, 100, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (200, '2021-11-28 12:00:00', '2021-12-28 12:00:00', 12345, 1500,300, true, 200, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (300, '2021-12-28 12:00:00', '2021-07-03 12:00:00', 12353, 1500, 300, false, 300, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (400, '2021-10-28 12:00:00', '2021-11-03 12:00:00', 12361, 1500, 300, false, 400, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (500, '2021-01-28 12:00', '2021-02-03 12:00:00', 12373, 1500, 300, false, 500, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (600, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 123757, 1500, 300, true, 500, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (700, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 123778, 1500, 300, true, 500, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (800, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237454, 1500, 300, true, 500, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (900, '2021-01-28 12:00:00', '2021-07-15 12:00:00', 12378234, 1500, 100, false, 500, 10, false,null);
-----jeleninooooo
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3100, '2021-10-28 12:00:00', '2021-11-07 12:00:00', 12361, 1500, 300, false, 400, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3200, '2021-07-28 12:00', '2021-07-03 12:00:00', 12373, 1500, 300, false, 200, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3300, '2021-05-28 12:00:00', '2021-05-11 12:00:00', 123757, 1500, 200, false, 200, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3400, '2021-06-28 12:00:00', '2021-06-03 12:00:00', 123778, 1500, 200, false, 200, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3500, '2021-06-28 12:00:00', '2021-06-15 12:00:00', 1237454, 1500, 100, false, 400, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3600, '2021-07-28 12:00:00', '2021-07-15 12:00:00', 12378234, 1500, 100, false, 400, 10, false,null);
---------------
--danicino ne dirajjjjjjjjjjj!!!!!!!!
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1000, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 123555, 1500, 200, true, 100, 10, false, '2021-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1100, '2021-11-28 12:00:00', '2021-12-28 12:00:00', 12345, 1500,200, false, 200, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1200, '2020-07-01 12:00:00', '2020-07-03 12:00:00', 12353, 1500, 200, true, 300, 10, false, '2020-02-07');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1300, '2021-01-28 12:00:00', '2021-04-03 12:00:00', 12361, 1500, 200, true, 400, 10, false, '2021-04-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1400, '2021-01-28 12:00', '2021-02-03 12:00:00', 12373, 1500, 200, true, 500, 10, false, '2021-02-02');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1500, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 123757, 1500, 200, true, 500, 10, false, '2021-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1600, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 123778, 1500, 200, true, 500, 10, false, '2021-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1700, '2021-01-28 12:00:00', '2021-02-03 12:00:00', 1237454, 1500, 200, true, 500, 10, false, '2021-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1800, '2021-01-28 12:00:00', '2021-07-15 12:00:00', 12378234, 1500, 200, true, 500, 10, false, '2021-02-03');

insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (1900, '2018-01-28 12:00', '2018-02-03 12:00:00', 12373, 1500, 200, true, 500, 10, false, '2018-02-02');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2000, '2018-01-28 12:00:00', '2018-02-03 12:00:00', 123757, 1500, 200, true, 500, 10, false, '2018-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2100, '2019-01-28 12:00:00', '2019-02-03 12:00:00', 123778, 1500, 200, true, 500, 10, false, '2019-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2200, '2019-01-28 12:00:00', '2019-02-03 12:00:00', 1237454, 1500, 200, true, 500, 10, false, '2019-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2300, '2020-01-28 12:00:00', '2020-07-15 12:00:00', 12378234, 1500, 200, true, 500, 10, false, '2020-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2400, '2020-01-28 12:00', '2020-02-03 12:00:00', 12373, 1500, 200, true, 500, 10, false, '2020-02-02');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2500, '2020-01-28 12:00:00', '2020-02-03 12:00:00', 123757, 1500, 200, true, 500, 10, false, '2020-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2600, '2020-01-28 12:00:00', '2020-02-03 12:00:00', 123778, 1500, 200, true, 500, 10, false, '2020-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2700, '2020-01-28 12:00:00', '2020-02-03 12:00:00', 1237454, 1500, 200, true, 500, 10, false, '2020-02-03');
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2800, '2018-01-28 12:00:00', '2018-07-15 12:00:00', 12378234, 1500, 200, true, 500, 10, false, '2018-02-03');
----------------------------danicinooooooo!!!!!
-----------stefanu!
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (2900, '2021-05-28 12:00:00', '2021-05-03 12:00:00', 123555, 1500, 300, false, 100, 10, false,null);
insert into drugreservation (id, reservation_date, expiration_date, unique_identifier, patient_id, pharmacy_id, issued, medication_id, therapy_days, canceled,date_of_issue) values (3000, '2021-03-28 12:00:00', '2021-03-23 12:00:00', 123555, 1500, 300, false, 100, 10, false,null);

insert into medical_record(id, patient_id) values (100, 1500);
insert into medical_record(id, patient_id) values (200, 1600);
insert into medical_record(id, patient_id) values (300, 1700);
insert into medical_record(id, patient_id) values (400, 1800);
insert into medical_record(id, patient_id) values (500, 1900);
insert into medical_record(id, patient_id) values (600, 2000);

insert into medical_record_ingredients(medical_record_id, ingredients_id) values (100, 900);
insert into medical_record_ingredients(medical_record_id, ingredients_id) values (200, 800);
insert into medical_record_ingredients(medical_record_id, ingredients_id) values (300, 700);
insert into medical_record_ingredients(medical_record_id, ingredients_id) values (400, 900);
insert into medical_record_ingredients(medical_record_id, ingredients_id) values (500, 100);

insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (100, 100, 100, 2300);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (200, 200, 75, 2300);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (300, 400, 50, 2300);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (400, 600, 25, 2300);

insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (500, 300, 100, 2400);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (600, 500, 200, 2400);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (700, 200, 50, 2400);
insert into suppliersmedication(id, medication_id, quantity, supplier_id) VALUES (800, 100, 250, 2400);

insert into orders(id, due_date,uniqueidentifier, pharmacy_id,status) VALUES (100, '2021-07-26 12:00:00' ,531282,100,0);
insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (200, '2021-07-25 12:00:00', 531283,100,0);
insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (300, '2021-07-24 12:00:00', 531284,100,1);
insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (400, '2021-07-22 12:00:00',  531285, 100,1);

insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (500, '2021-07-26 12:00:00', 531286,200,0);
insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (600, '2021-07-22 12:00:00',531287 ,200,0);
insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (700, '2021-07-22 12:00:00',531289 ,200,1);
insert into orders(id, due_date, uniqueidentifier, pharmacy_id,status) VALUES (800, '2021-07-27 12:00:00',531288 ,200,1);


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


insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (100,'2021-07-23 12:00:00', 0, 9000, 8126302 ,200, 2400);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (102, '2021-07-19 12:00:00', 0, 3800,8126304 ,700, 2400);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (101,'2021-07-22 12:00:00', 0, 6000,8126303 ,300, 2400);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (103, '2021-06-22 12:00:00', 0, 3230,8126305 ,800, 2400);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (104, '2021-07-19 12:00:00', 0, 3800,8126304 ,400, 2400);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (105, '2021-07-26 12:00:00', 0, 3230,8126305 ,500, 2300);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (106, '2021-07-21 12:00:00', 1, 3000,8126305 ,100, 2300);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (107, '2021-07-24 12:00:00', 0, 6000,8126305 ,800, 2300);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (108, '2021-07-20 12:00:00', 0, 4500,8126305 ,800, 2300);
insert into offers(id, due_delivery, offer_status, price, uniqueidentifier, order_id, supplier_id) VALUES (109, '2021-09-10 12:00:00', 0, 2200,8126305 ,800, 2300);


insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (100, false, 'pharmacy', 'Apoteka je jako ruzna i smara me raspored lekova', 200, 1500);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (200, false, 'pharmacy', 'Osecam se uvredjeno jer je boja apoteke ruzna', 100, 1500);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (300, true, 'pharmacy', 'Da li je moguce da ova apoteka nema lek koji meni treba??????????????', 300, 1500);
insert into complaints(id, is_answered, role, text, usert_to_complain_id, patient_id) VALUES (400, false, 'pharmacist', 'HAOS LOS FARMACEUT', 1000, 1500);


insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (100, 0, 100, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (200, 20, 200, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (300, 0, 300, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (400, 10, 400, 100);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES  (1300, 50, 500, 100);

insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (500, 111, 200, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (600, 66, 400, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (700, 42, 600, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (800, 69, 100, 200);

insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (900, 666, 300, 300);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1000, 90, 400, 300);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1100, 96, 500, 300);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1200, 29, 200, 300);

insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1400, 11, 200, 400);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1500, 11, 100, 400);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1600, 11, 600, 400);

insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1700, 42, 500, 200);
insert into pharmacy_storage(id, quantity, medication_id, pharmacy_id) VALUES (1800, 69, 300, 200);




insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (100, 800, '2022-05-26 12:00:00', '2021-01-26 12:00:00', 100, 100);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (200, 560, '2022-05-26 12:00:00', '2021-01-26 12:00:00', 200, 100);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (300, 330, '2022-05-26 12:00:00', '2021-01-26 12:00:00', 300, 100);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (400, 580, '2022-05-26 12:00:00', '2021-01-26 12:00:00', 400, 100);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (900, 123, '2021-06-26 12:00:00', '2021-01-23 12:00:00', 300, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1000, 748, '2021-06-26 12:00:00', '2021-01-12 12:00:00', 400, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1100, 639, '2021-06-26 12:00:00', '2021-01-09 12:00:00', 500, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1200, 826, '2021-06-26 12:00:00', '2021-01-09 12:00:00', 200, 300);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1300, 90, '2021-10-26 12:00:00', '2021-02-12 12:00:00', 100, 400);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1400, 75, '2021-11-26 12:00:00', '2021-03-09 12:00:00', 600, 400);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1500, 2134, '2021-12-26 12:00:00', '2021-05-09 12:00:00', 200, 400);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (500, 920, '2021-06-05 12:00:00', '2021-01-26 12:00:00', 200, 200);

insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (2300, 920, '2021-12-12 12:00:00', '2021-06-06 12:00:00', 200, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (600, 600, '2021-06-05 12:00:00', '2021-01-26 12:00:00', 400, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1900, 600, '2021-10-26 12:00:00', '2021-06-06 12:00:00', 400, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (700, 130, '2021-06-05 12:00:00', '2021-01-26 12:00:00', 600, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (2000, 130, '2021-10-30 12:00:00', '2021-06-06 12:00:00', 600, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (2100, 978, '2021-10-19 12:00:00', '2021-06-06 12:00:00', 100, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (800, 978, '2021-06-05 12:00:00', '2021-01-26 12:00:00', 100, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1600, 1400, '2021-01-26 12:00:00', '2018-01-06 12:00:00', 500, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1800, 1400, '2022-01-26 12:00:00', '2021-01-27 12:00:00', 500, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (1700, 978, '2021-06-04 12:00:00', '2018-01-26 12:00:00', 300, 200);
insert into pricelists(id, price, end_period, start_period, medication_id, pharmacy_id) VALUES (2200, 978, '2021-10-04 12:00:00', '2021-06-05 12:00:00', 300, 200);

insert into subscriptions(id,patient_id,pharmacy_id) values (100,1500,200);
insert into subscriptions(id,patient_id,pharmacy_id) values (200,1600,200);

insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(200,'2021-07-20 12:00:00', '2021-01-23 12:00:00','Xanax na snizenju 200din.',200);
insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(300,'2021-05-26 12:00:00', '2021-01-23 12:00:00','Svi biodermini proizvodi na snizenju -35%.',200);
insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(400,'2021-05-26 12:00:00', '2021-01-23 12:00:00','Srecni uskrsnji praznici!Apoteka ZEGIN vam poklanja do -50% na ceo asortiman.',200);
insert into promotions(id,promotion_end_time,promotion_start_time,description,pharmacy_id) values(100,'2021-06-10 12:00:00', '2021-03-08 12:00:00','Povodom 8.marta svim zenama -20%.',200);


insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (100, 1000, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 100, 'Counseling');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (200, 750, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 100, 'Examination');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (300, 1200, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 200, 'Counseling');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (400, 850, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 200, 'Examination');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (500, 900, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 300, 'Counseling');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (600, 900, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 300, 'Examination');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (700, 1500, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 400, 'Counseling');
insert into pharmacy_pricelist(id, cost, end_date, start_date, pharmacy_id, type) values (800, 2000, '2022-01-01 12:00:00', '2021-01-01 12:00:00', 400, 'Examination');


insert into druginquiries(id,medication_id,date) values (100,200,'2021-05-26 12:00:00');
insert into druginquiries(id,medication_id,date) values (200,100,'2021-05-20 12:00:00');
insert into druginquiries(id,medication_id,date) values (300,400,'2021-05-15 12:00:00');
insert into druginquiries(id,medication_id,date) values (400,500,'2021-05-03 12:00:00');
insert into druginquiries(id,medication_id,date) values (500,100,'2021-01-26 12:00:00');

insert into vacation_requests(id, end_date, start_date, vacation_reason, medical_staff_id,status, pharmacy_id) VALUES (100,'2021-06-30 12:00:00','2021-05-30 12:00:00','Jako sam umorna i treba mi odmor.',500,2, 100);
insert into vacation_requests(id, end_date, start_date, vacation_reason, medical_staff_id,status, pharmacy_id) VALUES (200,'2021-06-29 12:00:00','2021-05-30 12:00:00','Jako sam umorna i treba mi odmor.',100,2, 100);
insert into vacation_requests(id, end_date, start_date, vacation_reason, medical_staff_id,status, pharmacy_id) VALUES (300,'2021-06-29 12:00:00','2021-05-25 12:00:00','Jako sam umorna i treba mi odmor.',900,2, 100);

insert into vacation_requests(id, end_date, start_date, vacation_reason, medical_staff_id,status, pharmacy_id) VALUES (400,'2021-06-30 12:00:00','2021-05-30 12:00:00','Jako sam umorna i treba mi odmor.',500,2, 200);
insert into vacation_requests(id, end_date, start_date, vacation_reason, medical_staff_id,status, pharmacy_id) VALUES (500,'2021-06-29 12:00:00','2021-05-30 12:00:00','Moram da vodim macku kod veterinara.',100,2, 200);
insert into vacation_requests(id, end_date, start_date, vacation_reason, medical_staff_id,status, pharmacy_id) VALUES (600,'2021-06-29 12:00:00','2021-05-25 12:00:00','Sin mi se zeni.',900,2, 200);


insert into grades (id, grade, grade_type, type, patient_id, pharmacy_id) values (100, 2, 'PharmacyGrade','PharmacyGrade', 1900, 100);
insert into grades (id, grade, grade_type, type, patient_id, pharmacy_id) values (200, 3, 'PharmacyGrade','PharmacyGrade', 1900, 200);
insert into grades (id, grade, grade_type, type, patient_id, pharmacy_id) values (300, 4, 'PharmacyGrade','PharmacyGrade', 1900, 300);
insert into grades (id, grade, grade_type, type, patient_id, pharmacy_id) values (400, 5, 'PharmacyGrade','PharmacyGrade', 1900, 400);

insert into grades (id, grade, grade_type, type, patient_id, medication_id) values (500, 2, 'MedicationGrade','MedicationGrade', 1900, 100);
insert into grades (id, grade, grade_type, type, patient_id, medication_id) values (600, 3, 'MedicationGrade','MedicationGrade', 1900, 200);
insert into grades (id, grade, grade_type, type, patient_id, medication_id) values (700, 1, 'MedicationGrade','MedicationGrade', 1900, 300);
insert into grades (id, grade, grade_type, type, patient_id, medication_id) values (800, 5, 'MedicationGrade','MedicationGrade', 1900, 400);
insert into grades (id, grade, grade_type, type, patient_id, medication_id) values (900, 5, 'MedicationGrade','MedicationGrade', 1900, 500);
insert into grades (id, grade, grade_type, type, patient_id, medication_id) values (1000, 4, 'MedicationGrade','MedicationGrade', 1900, 600);

insert into grades (id, grade, grade_type, type, patient_id, pharmacist_id) values (1100, 3, 'PharmacistGrade','PharmacistGrade', 1900, 900);
insert into grades (id, grade, grade_type, type, patient_id, pharmacist_id) values (1200, 5, 'PharmacistGrade','PharmacistGrade', 1900, 1000);
insert into grades (id, grade, grade_type, type, patient_id, pharmacist_id) values (1300, 4, 'PharmacistGrade','PharmacistGrade', 1900, 1100);
insert into grades (id, grade, grade_type, type, patient_id, pharmacist_id) values (1400, 3, 'PharmacistGrade','PharmacistGrade', 1900, 1200);
insert into grades (id, grade, grade_type, type, patient_id, pharmacist_id) values (1500, 2, 'PharmacistGrade','PharmacistGrade', 1900, 1300);


insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (1600, 2, 'DermatologistGrade','DermatologistGrade', 1900, 100);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (1700, 3, 'DermatologistGrade','DermatologistGrade', 1900, 200);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (1800, 4, 'DermatologistGrade','DermatologistGrade', 1900, 300);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (1900, 5, 'DermatologistGrade','DermatologistGrade', 1900, 400);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (2000, 1, 'DermatologistGrade','DermatologistGrade', 1900, 500);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (2100, 1, 'DermatologistGrade','DermatologistGrade', 1900, 600);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (2200, 2, 'DermatologistGrade','DermatologistGrade', 1900, 700);
insert into grades (id, grade, grade_type, type, patient_id, dermatologist_id) values (2300, 1, 'DermatologistGrade','DermatologistGrade', 1900, 800);
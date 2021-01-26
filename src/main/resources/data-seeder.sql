insert into states(id, name) values (1, 'Serbia');

insert into cities(id, name, state_id) values (1, 'Novi Sad', 1);
insert into cities(id, name, state_id) values (2, 'Beograd', 1);

insert into addresses(id, number, street, city_id) values (1, 2, 'Bulevar Despota Stefana', 1);
insert into addresses(id, number, street, city_id) values (2, 7, 'Bulevar Despota Stefana', 1);
insert into addresses(id, number, street, city_id) values (3, 99, 'Bulevar Revolucije', 2);


insert into users(id, date_of_birth, gender, name, email, password, phone_number, surname, license_number, address_id, role) values (1, '2000-01-01 00:00:01', 0, 'Jelena', 'vlajkovj@gmail.com', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 123124124 ,'Vlajkov', 1, 1, 'Dermatologist');
insert into users(id, date_of_birth, gender, name, email, password, phone_number, surname, license_number, address_id, role) values (5, '1986-01-01 00:00:01', 0, 'Nadezda', 'vlajkovn@gmail.com', '$2y$12$/YLs9Irv4CFIwl4J/JJukuounpOzs0FDtvG.rxaF5f4ZD2sr.VRQe', 123124123 ,'Vlajkov', 1, 1, 'Pharmacist');
--insert into patients(id, date_of_birth, gender, name, phone_number, surname, address_id, role) values (2, '1997-01-01 00:00:01', 1, 'Aleksandar', 'Ignjatijevic', 2, 2, 2);
--insert into users(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id, role) values (3, '1966-01-01 00:00:01', 2, 'Stefan', 12125123, 'Aradjanin', 3, 3, 0);
--insert into users(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id, role) values (4, '1966-01-01 00:00:01', 0, 'Danica', 12125126, 'Vojvodic', 3, 3, 1);

insert into authority(id, name) values (1, 'ROLE_PHARMACIST');
insert into authority(id, name) values (2, 'ROLE_DERMATOLOGIST');
insert into authority(id, name) values (3, 'ROLE_PATIENT');
insert into authority(id, name) values (4, 'ROLE_SYSADMIN');
insert into authority(id, name) values (5, 'ROLE_PHARMACYADMIN');
insert into authority(id, name) values (6, 'ROLE_MEDICALSTAFF');

insert into user_authority(user_id, authority_id) values (1, 2);
insert into user_authority(user_id, authority_id) values (5, 1);

insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (1, '2020-02-10 12:00:00', '2020-02-10 12:30:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (2, '2020-02-10 13:00:00', '2020-02-10 13:30:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (3, '2020-02-10 13:30:00', '2020-02-10 14:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (4, '2020-02-10 14:00:00', '2020-02-10 14:30:00', 1000.00, 'Examination', false, 1);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (5, '2020-02-10 14:30:00', '2020-02-10 15:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (6, '2020-02-10 15:30:00', '2020-02-10 16:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (7, '2020-02-10 16:30:00', '2020-02-10 17:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (8, '2020-02-11 11:00:00', '2020-02-11 11:30:00', 1000.00, 'Examination', false, 1);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (9, '2020-02-11 12:00:00', '2020-02-10 12:30:00', 1000.00, 'Examination', false, 1);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (10, '2020-02-11 13:00:00', '2020-02-10 13:30:00', 1000.00, 'Examination', false, 1);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (11, '2020-02-12 13:30:00', '2020-02-10 14:00:00', 1000.00, 'Examination', false, 1);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (12, '2020-02-12 14:00:00', '2020-02-10 14:30:00', 1000.00, 'Examination', false, 1);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (13, '2020-02-12 14:30:00', '2020-02-10 15:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (14, '2020-02-13 15:30:00', '2020-02-10 16:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (15, '2020-02-13 16:30:00', '2020-02-10 17:00:00', 1000.00, 'Counseling', false, 5);
insert into appointment(id, appointment_start_time, appointment_end_time, cost, type, is_canceled, pharmacist_id) values (16, '2020-02-13 11:00:00', '2020-02-11 11:30:00', 1000.00, 'Counseling', false, 5);

insert into medications(id, additional_notes, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (1, 'Od ovog leka ce te bole ledja', 'Moguc bol u kurcu', 2, 1, 1, 2, 'Borozepam', 'Loncar Doo', 0);
insert into medications(id, additional_notes, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (2, 'Lek nije namenjen maloletnim licima', 'Moguca pospanost', 2, 1, 1, 2, 'Xanax', 'Pfizer', 0);
insert into medications(id, additional_notes, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (3, null, null, 2, 1, 1, 2, 'Eferalgan', 'Galenika a.d.', 0);
insert into medications(id, additional_notes, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (4, 'Ne piti na prazan stomak', null, 2, 1, 1, 2, 'Andol', 'Pliva Hrvatska d.o.o.', 0);
insert into medications(id, additional_notes, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (5, 'Ne piti na prazan stomak', 'Moguc osecaj nesvestice', 2, 1, 1, 2, 'Caffetine', 'Alkaloid AD-Skopje', 0);
insert into medications(id, additional_notes, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (6, null, 'Moguca pospanost, umor', 2, 1, 1, 2, 'Brufen', 'Galenika a.d.', 0);

insert into substitute_medications(original_id, substitute_id) VALUES (1, 3);
insert into substitute_medications(original_id, substitute_id) VALUES (1, 4);
insert into substitute_medications(original_id, substitute_id) VALUES (3, 1);
insert into substitute_medications(original_id, substitute_id) VALUES (3, 5);
insert into substitute_medications(original_id, substitute_id) VALUES (3, 6);
insert into substitute_medications(original_id, substitute_id) VALUES (4, 3);
insert into substitute_medications(original_id, substitute_id) VALUES (4, 5);
insert into substitute_medications(original_id, substitute_id) VALUES (4, 6);
insert into substitute_medications(original_id, substitute_id) VALUES (6, 3);
insert into substitute_medications(original_id, substitute_id) VALUES (6, 4);
insert into substitute_medications(original_id, substitute_id) VALUES (6, 5);
insert into substitute_medications(original_id, substitute_id) VALUES (6, 1);


insert into ingredients(id, name) VALUES (1, 'paracetamol');
insert into ingredients(id, name) VALUES (2, 'kofein');
insert into ingredients(id, name) VALUES (3, 'kodeinfosfat');
insert into ingredients(id, name) VALUES (4, 'seskvidhidrat');
insert into ingredients(id, name) VALUES (5, 'propifenazon');
insert into ingredients(id, name) VALUES (6, 'ibuprofena');
insert into ingredients(id, name) VALUES (7, 'bromazepam');
insert into ingredients(id,name) values (8, 'alprazolam');
insert into ingredients(id,name) values (9, 'acetilsalicilna kiselina');


insert into medication_ingredients(medication_id, ingredient_id) VALUES (1, 7);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (2, 7);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (3, 1);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (4, 9);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (5, 2);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (5, 3);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (5, 4);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (5, 7);
insert into medication_ingredients(medication_id, ingredient_id) VALUES (6, 6);


insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (1, 2, 1);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (2, 1, 2);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (3, 4, 2);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (4, 2, 3);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (5, 3, 3);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (6, 5, 4);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (7, 1, 5);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (8, 1, 6);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (9, 1, 3);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (10, 4, 2);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (11, 3, 2);

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

insert into allergies(id, name) values (1, 'Rash');
insert into allergies(id, name) values (2, 'Red skin');
insert into allergies(id, name) values (3, 'Fainting');
insert into allergies(id, name) values (4, 'Nausea');
insert into allergies(id, name) values (5, 'Vomiting');
insert into allergies(id, name) values (6, 'Diarrhea');


insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (2, 3);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (2, 4);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (2, 5);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (2, 6);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (6, 1);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (6, 2);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (6, 3);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (6, 4);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (6, 5);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (6, 6);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (9, 1);
insert into alergies_to_ingredient(ingredient_id, allergy_id) VALUES (9, 2);


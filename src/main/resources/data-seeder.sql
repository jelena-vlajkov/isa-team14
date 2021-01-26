insert into states(id, name) values (1, 'Serbia');

insert into cities(id, name, state_id) values (1, 'Novi Sad', 1);
insert into cities(id, name, state_id) values (2, 'Beograd', 1);

insert into addresses(id, number, street, city_id) values (1, 2, 'Bulevar Despota Stefana', 1);
insert into addresses(id, number, street, city_id) values (2, 7, 'Bulevar Despota Stefana', 1);
insert into addresses(id, number, street, city_id) values (3, 99, 'Bulevar Revolucije', 2);


insert into dermatologists(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id, role) values (1, '2000-01-01 00:00:01', 0, 'Jelena',123124124 ,'Vlajkov', 1, 1, 4);
insert into pharmacists(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id, role) values (5, '1986-01-01 00:00:01', 0, 'Nadezda',123124123 ,'Vlajkov', 1, 1, 3);
insert into patients(id, date_of_birth, gender, name, phone_number, surname, address_id, role) values (2, '1997-01-01 00:00:01', 1, 'Aleksandar', 'Ignjatijevic', 2, 2, 2);
insert into users(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id, role) values (3, '1966-01-01 00:00:01', 2, 'Stefan', 12125123, 'Aradjanin', 3, 3, 0);
insert into users(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id, role) values (4, '1966-01-01 00:00:01', 0, 'Danica', 12125126, 'Vojvodic', 3, 3, 1);

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



-- insert into ingredients(id, name) VALUES (1, 'paracetamol');
-- insert into ingredients(id, name) VALUES (2, 'kofein');
-- insert into ingredients(id, name) VALUES (3, 'kodeinfosfat');
-- insert into ingredients(id, name) VALUES (4, 'seskvidhidrat');
-- insert into ingredients(id, name) VALUES (5, 'propifenazon');
-- insert into ingredients(id, name) VALUES (6, 'ibuprofena');
-- insert into ingredients(id, name) VALUES (7, 'bromazepam');
-- insert into ingredients(id,name) values (8, 'alprazolam');
-- insert into ingredients(id,name) values (9, 'acetilsalicilna kiselina');


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


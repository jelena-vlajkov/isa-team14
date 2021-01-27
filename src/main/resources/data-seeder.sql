insert into states(id, name) values (1, 'Serbia');

insert into cities(id, name, state_id) values (1, 'Novi Sad', 1);
insert into cities(id, name, state_id) values (2, 'Beograd', 1);

insert into addresses(id, number, street, city_id) values (1, 2, 'Bulevar Despota Stefana', 1);
insert into addresses(id, number, street, city_id) values (2, 7, 'Bulevar Despota Stefana', 1);
insert into addresses(id, number, street, city_id) values (3, 99, 'Bulevar Revolucije', 2);


-- insert into users(dtype, id, date_of_birth, gender, name, phone_number, surname, license_number, address_id) values (1, '2000-01-01 00:00:01', 'Male', 'Pera',123124124 ,'Peric', 1, 1);
-- insert into users(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id) values (2, '1997-01-01 00:00:01', 2, 'Milan', 1212500, 'Milanovic', 2, 2);
-- insert into users(id, date_of_birth, gender, name, phone_number, surname, license_number, address_id) values (3, '1966-01-01 00:00:01', 2, 'Marko', 12125123, 'Markovic', 3, 3);

insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (100, 'Od ovog leka ce te bole ledja', 123401231 ,'Moguc bol u kurcu', 2, 1, 1, 2, 'Borozepam', 'Loncar Doo', 0);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (200, 'Lek nije namenjen maloletnim licima',123401232 ,'Moguca pospanost', 2, 1, 1, 2, 'Xanax', 'Pfizer', 0);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (300, null, 123401233,null, 2, 1, 1, 2, 'Eferalgan', 'Galenika a.d.', 0);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (400, 'Ne piti na prazan stomak', 123401234, null, 2, 1, 1, 2, 'Andol', 'Pliva Hrvatska d.o.o.', 0);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (500, 'Ne piti na prazan stomak', 123401235, 'Moguc osecaj nesvestice', 2, 1, 1, 2, 'Caffetine', 'Alkaloid AD-Skopje', 0);
insert into medications(id, additional_notes, code, contraindications, daily_dose, drug_form, drug_kind, drug_type, name, producer, type_of_prescribing) values (600, null, 123401236,'Moguca pospanost, umor', 2, 1, 1, 2, 'Brufen', 'Galenika a.d.', 0);

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


insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (100, 2, 100);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (200, 1, 200);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (300, 4, 200);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (400, 2, 300);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (500, 3, 300);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (600, 5, 400);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (700, 1, 500);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (800, 1, 600);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (900, 1, 300);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (1000, 4, 200);
insert into prescribed_drugs(id, quantity, prescribed_medication_id) VALUES (1100, 3, 200);

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


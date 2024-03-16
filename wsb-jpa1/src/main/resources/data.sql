insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');

insert into address (id, address_line1, address_line2, city, postal_code)
            values (2, 'nowy addres', 'nowy addres2', 'city123', '34-312');

insert into doctor (id, first_name, last_name, telephone_number, doctor_number, specialization, address_id)
            values (1, 'test', 'pwd', '1234', '4321', 'GP', '1');

insert into patient (id, first_name, last_name, telephone_number, patient_number, date_of_birth, address_id)
            values (1, 'imie_test', 'nazwisko_test', '123556666', '34', '1990-05-15', 2);

insert into visit (id, time)
            values (1, '2024-03-16 10:00:00');

insert into medical_treatment (id, description, type)
            values (1, 'test_description', 'USG');

insert into doctor_visit_entities (doctor_entity_id, visit_entities_id)
            values (1, 1);

insert into patient_visit_entities (patient_entity_id, visit_entities_id)
            values (1, 1);

insert into visit_medical_treatment_entities (medical_treatment_entities_id, visit_entity_id)
            values (1, 1);

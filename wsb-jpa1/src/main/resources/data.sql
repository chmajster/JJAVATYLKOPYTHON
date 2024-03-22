insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');

insert into address (id, address_line1, address_line2, city, postal_code)
            values (2, 'nowy addres', 'nowy addres2', 'city123', '34-312');

insert into doctor (id, first_name, last_name, telephone_number, doctor_number, specialization, address_id)
            values (1, 'test', 'pwd', '1234', '4321', 'GP', '1');

insert into doctor (id, first_name, last_name, telephone_number, doctor_number, specialization, address_id)
            values (2, 'test2', 'pwd2', '12342344', '125', 'GP', '1');

insert into patient (id, first_name, last_name, age, telephone_number, patient_number, date_of_birth, address_id)
            values (1, 'imie_test', 'nazwisko_test', 34, '123556666', '34', '1990-05-15', 2);

insert into visit (id, time, patient_id, doctor_id)
            values (1, '2024-03-16 10:00:00', 1, 1);

insert into visit (id, time, patient_id, doctor_id)
        values (2, '2024-03-22 10:00:00', 1, 2);

insert into medical_treatment (id, description, type)
            values (1, 'test_description', 'USG');

insert into medical_treatment (id, description, type)
            values (2, 'test_description231', 'USG');

insert into visit_medical_treatment_entities (medical_treatment_entities_id, visit_entity_id)
            values (1, 1);

insert into visit_medical_treatment_entities (medical_treatment_entities_id, visit_entity_id)
            values (2, 1);

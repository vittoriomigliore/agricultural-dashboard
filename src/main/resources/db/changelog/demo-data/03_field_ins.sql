--liquibase formatted sql
--changeset vmigliore:202410230100
INSERT INTO field (field_id, company_id, crop_id, size, planting_date, harvest_date, last_irrigation_date)
VALUES (1, 1, 1, 50.75, '2024-03-15', '2024-09-15', '2024-08-01'),
       (2, 1, 3, 30.50, '2024-04-10', '2024-07-30', '2024-07-20');


--liquibase formatted sql
--changeset vmigliore:202410230100
CREATE TABLE field
(
    field_id             INT PRIMARY KEY,
    company_id           INT,
    crop_id              INT,
    size                 DECIMAL(10, 2),
    planting_date        DATE,
    harvest_date         DATE,
    last_irrigation_date DATE,
    FOREIGN KEY (company_id) REFERENCES company (company_id),
    FOREIGN KEY (crop_id) REFERENCES crop (crop_id)
);
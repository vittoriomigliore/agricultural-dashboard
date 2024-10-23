--liquibase formatted sql
--changeset vmigliore:202410230100
CREATE TABLE production
(
    production_id  INT PRIMARY KEY,
    field_id       INT,
    crop_id        INT,
    harvest_date   DATE,
    quantity       DECIMAL(10, 2),
    quality_rating INT,
    FOREIGN KEY (field_id) REFERENCES field (field_id),
    FOREIGN KEY (crop_id) REFERENCES crop (crop_id)
);
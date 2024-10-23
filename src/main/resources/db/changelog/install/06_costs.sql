--liquibase formatted sql
--changeset vmigliore:202410230100
CREATE TABLE costs
(
    cost_id   INT PRIMARY KEY,
    field_id  INT,
    crop_id   INT,
    date      DATE,
    cost_type VARCHAR(255),
    amount    DECIMAL(10, 2),
    FOREIGN KEY (field_id) REFERENCES field (field_id),
    FOREIGN KEY (crop_id) REFERENCES crop (crop_id)
);
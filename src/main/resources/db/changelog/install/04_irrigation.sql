--liquibase formatted sql
--changeset vmigliore:202410230100
CREATE TABLE irrigation
(
    irrigation_id INT PRIMARY KEY,
    field_id      INT,
    date          DATE,
    amount_used   DECIMAL(10, 2),
    method        VARCHAR(255),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);
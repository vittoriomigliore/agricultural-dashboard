--liquibase formatted sql
--changeset vmigliore:202410230100
CREATE TABLE sales
(
    sale_id             INT PRIMARY KEY,
    production_id       INT,
    date                DATE,
    quantity_sold       DECIMAL(10, 2),
    sale_price_per_unit DECIMAL(10, 2),
    buyer               VARCHAR(255),
    FOREIGN KEY (production_id) REFERENCES production (production_id)
);
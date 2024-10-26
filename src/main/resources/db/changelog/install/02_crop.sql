--liquibase formatted sql
--changeset vmigliore:202410230100 runOnChange:true
CREATE TABLE crop
(
    crop_id          INT PRIMARY KEY,
    name             VARCHAR(255),
    type             VARCHAR(255),
    season           VARCHAR(255),
    growth_time      INT,
    ideal_conditions VARCHAR(255)
);
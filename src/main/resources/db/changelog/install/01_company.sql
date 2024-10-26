--liquibase formatted sql
--changeset vmigliore:202410230100 runOnChange:true
CREATE TABLE company
(
    company_id       INT PRIMARY KEY,
    name             VARCHAR(255),
    location         VARCHAR(255),
    owner            VARCHAR(255),
    established_year INT
);
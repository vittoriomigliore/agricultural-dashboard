--liquibase formatted sql
--changeset vmigliore:01_company runOnChange:true
CREATE TABLE IF NOT EXISTS company
(
    company_id       INT PRIMARY KEY,
    name             VARCHAR(255),
    location         VARCHAR(255),
    owner            VARCHAR(255),
    established_year INT
);
--liquibase formatted sql
--changeset vmigliore:02_crop runOnChange:true
CREATE TABLE IF NOT EXISTS crop
(
    crop_id          INT PRIMARY KEY,
    name             VARCHAR(255)
);
--liquibase formatted sql
--changeset vmigliore:05_production runOnChange:true
CREATE TABLE IF NOT EXISTS production
(
    production_id  INT AUTO_INCREMENT PRIMARY KEY,
    field_id       INT,
    crop_id        INT,
    harvest_date   DATE,
    quantity       DECIMAL(10, 2),
    quality_rating INT,
    FOREIGN KEY (field_id) REFERENCES field (field_id),
    FOREIGN KEY (crop_id) REFERENCES crop (crop_id)
);
--liquibase formatted sql
--changeset vmigliore:04_irrigation runOnChange:true
CREATE TABLE irrigation
(
    irrigation_id INT AUTO_INCREMENT PRIMARY KEY,
    field_id      INT,
    date          DATE,
    amount_used   DECIMAL(10, 2),
    method        VARCHAR(255),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);
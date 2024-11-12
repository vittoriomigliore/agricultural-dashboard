--liquibase formatted sql
--changeset vmigliore:04_irrigation runOnChange:true
CREATE TABLE IF NOT EXISTS irrigation
(
    irrigation_id INT AUTO_INCREMENT PRIMARY KEY,
    field_id      INT,
    datetime      DATETIME,
    amount_used   DECIMAL(10, 2),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);
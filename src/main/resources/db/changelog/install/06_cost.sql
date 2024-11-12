--liquibase formatted sql
--changeset vmigliore:06_costs runOnChange:true
CREATE TABLE IF NOT EXISTS cost
(
    cost_id   INT AUTO_INCREMENT PRIMARY KEY,
    field_id  INT,
    date      DATE,
    amount    DECIMAL(10, 2),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);
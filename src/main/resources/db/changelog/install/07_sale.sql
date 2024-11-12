--liquibase formatted sql
--changeset vmigliore:07_sales runOnChange:true
CREATE TABLE IF NOT EXISTS sale
(
    sale_id             INT AUTO_INCREMENT PRIMARY KEY,
    production_id       INT,
    date                DATE,
    quantity_sold       DECIMAL(10, 2),
    sale_price_per_unit DECIMAL(10, 2),
    FOREIGN KEY (production_id) REFERENCES production (production_id)
);
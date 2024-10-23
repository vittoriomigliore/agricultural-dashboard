--liquibase formatted sql
--changeset vmigliore:202410230100
CREATE TABLE weather
(
    weather_id    INT PRIMARY KEY,
    field_id      INT,
    date          DATE,
    temperature   DECIMAL(5, 2),
    precipitation DECIMAL(5, 2),
    humidity      DECIMAL(5, 2),
    wind_speed    DECIMAL(5, 2),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);
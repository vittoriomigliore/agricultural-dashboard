--liquibase formatted sql
--changeset vmigliore:08_weather runOnChange:true
CREATE TABLE weather
(
    weather_id    INT AUTO_INCREMENT PRIMARY KEY,
    field_id      INT,
    datetime      DATETIME,
    temperature   DECIMAL(5, 2),
    precipitation DECIMAL(5, 2),
    humidity      DECIMAL(5, 2),
    wind_speed    DECIMAL(5, 2),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);
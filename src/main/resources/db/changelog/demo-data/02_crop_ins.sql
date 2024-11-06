--liquibase formatted sql
--changeset vmigliore:02_crop_ins runOnChange:true
INSERT INTO crop (crop_id, name, type, season, growth_time, ideal_conditions)
VALUES (1, 'Wheat', 'Cereal', 'Autumn-Summer', 220, 'Well-drained soil, temperate climate'),
       (2, 'Corn', 'Cereal', 'Spring-Summer', 120, 'Deep fertile soil, sufficient irrigation'),
       (3, 'Tomato', 'Vegetable', 'Spring-Summer', 90, 'Well-irrigated soil, warm climate'),
       (4, 'Potato', 'Vegetable', 'Spring-Summer', 110, 'Sandy well-irrigated soil, moderate climate');

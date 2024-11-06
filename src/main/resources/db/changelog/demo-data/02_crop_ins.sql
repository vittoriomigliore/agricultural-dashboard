--liquibase formatted sql
--changeset vmigliore:02_crop_ins runOnChange:true
INSERT INTO crop (crop_id, name, type)
VALUES (1, 'Wheat', 'Cereal'),
       (2, 'Corn', 'Cereal'),
       (3, 'Tomato', 'Vegetable'),
       (4, 'Potato', 'Vegetable');

--liquibase formatted sql
--changeset vmigliore:02_crop_ins runOnChange:true
insert into crop (crop_id, name, type)
select 1,
       'Wheat',
       'Cereal' where not exists
       (select 1 from crop where crop_id = 1);

insert into crop (crop_id, name, type)
select 2,
       'Corn',
       'Cereal' where not exists
       (select 1 from crop where crop_id = 2);

insert into crop (crop_id, name, type)
select 3,
       'Tomato',
       'Vegetable' where not exists
       (select 1 from crop where crop_id = 3);

insert into crop (crop_id, name, type)
select 4,
       'Potato',
       'Vegetable' where not exists
       (select 1 from crop where crop_id = 4);

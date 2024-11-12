--liquibase formatted sql
--changeset vmigliore:02_crop_ins runOnChange:true
-- TODO: move to general data
insert into crop (crop_id, name)
select 1,
       'Wheat' where not exists
       (select 1 from crop where crop_id = 1);

insert into crop (crop_id, name)
select 2,
       'Corn' where not exists
       (select 1 from crop where crop_id = 2);

insert into crop (crop_id, name)
select 3,
       'Tomato' where not exists
       (select 1 from crop where crop_id = 3);

insert into crop (crop_id, name)
select 4,
       'Potato' where not exists
       (select 1 from crop where crop_id = 4);

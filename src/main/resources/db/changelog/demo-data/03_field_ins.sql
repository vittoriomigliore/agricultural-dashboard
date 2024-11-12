--liquibase formatted sql
--changeset vmigliore:03_field_ins runOnChange:true
insert into field (field_id, company_id, crop_id, size)
select 1,
       1,
       1,
       50.75 where not exists
(select 1 from field where field_id = 1);

insert into field (field_id, company_id, crop_id, size)
select 2,
       1,
       2,
       50.75 where not exists
    (select 1 from field where field_id = 2);

insert into field (field_id, company_id, crop_id, size)
select 3,
       1,
       3,
       50.75 where not exists
    (select 1 from field where field_id = 3);

insert into field (field_id, company_id, crop_id, size)
select 4,
       1,
       4,
       50.75 where not exists
    (select 1 from field where field_id = 4);


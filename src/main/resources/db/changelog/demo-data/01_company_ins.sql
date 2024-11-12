--liquibase formatted sql
--changeset vmigliore:01_company_ins runOnChange:true
insert into company(company_id, name)
select 1, 'DemoCompany'
where not exists
              (select 1 from company where company_id = 1);
--liquibase formatted sql
--changeset vmigliore:01_company_ins runOnChange:true
insert into company(company_id, name, location, owner, established_year)
select 1, 'DemoCompany', 'Bologna', 'Vittorio Migliore', 2020
where not exists
              (select 1 from company where company_id = 1);
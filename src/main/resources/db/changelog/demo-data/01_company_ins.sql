--liquibase formatted sql
--changeset vmigliore:202410230100
insert into company(company_id, name, location, owner, established_year)
values (1, 'DemoCompany', 'Bologna', 'Vittorio Migliore', 2020)
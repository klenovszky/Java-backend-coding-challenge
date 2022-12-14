----------------------------
-- Application tables

select * from surValues;

select * from outPayHeader;

select * from policy;

truncate table survalues;
truncate table outPayHeader;
truncate table policy;

----------------------------
-- Liquibase tables

select * from databasechangelog;

select * from databasechangeloglock;

----------------------------
-- Spring batch tables

select * from batch_job_execution;
select * from batch_step_execution;

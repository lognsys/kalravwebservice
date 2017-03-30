#PJD - 21-03-17
# removing user_id from user table wrongly added.
# One -off script which is used to alter user table
#

alter table user drop column user_id;
alter table user add column location varchar(255) not null default '' after phone;

# monika added to  alter user table with new fields 
ALTER TABLE kalrav.user
  ADD address varchar(255);
  ALTER TABLE kalrav.user
  ADD state varchar(60) not null,
  ADD city varchar(60) not null,
  ADD zipcode varchar(8) not null,
  ADD companyname varchar(100) not null,
  ADD device varchar(255)not null;
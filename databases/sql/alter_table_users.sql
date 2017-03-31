#PJD - 21-03-17
# removing user_id from user table wrongly added.
# One -off script which is used to alter user table
#
# CHNAGE LOG :
# monika -  30-03-2017

#alter table user drop column user_id;
alter table users add column location varchar(255) not null default '' after phone;

# monika added to  alter user table with new fields 
ALTER TABLE kalrav.users
  ADD company_name varchar(100) not null default '' after phone, 
  ADD address varchar(255) not null default ''after company_name,
  ADD state varchar(60) not null default '' after phone,
  ADD city varchar(60) not null default '' after state,
  ADD zipcode varchar(8) not null default '' after city,
  ADD device varchar(255) not null default '' after city;
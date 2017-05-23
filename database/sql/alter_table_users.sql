#PJD - 21-03-17
# removing user_id from user table wrongly added.
# One -off script which is used to alter user table
#
# CHNAGE LOG :
# monika -  30-03-2017
# monika -  1-04-2017 added email field
# pjd - 04-04-17 removed email field because username is field as mentioned.
# pjd - disabled alter table command as initial database will be setup by loading rows

#alter table user drop column user_id;
#alter table users add column location varchar(255) not null default '' after phone;

# monika added to  alter user table with new fields 
#ALTER TABLE kalrav.users
#  ADD company_name varchar(100) not null default '', 
#  ADD address varchar(255) not null default '',
#  ADD state varchar(60) not null default '',
#  ADD city varchar(60) not null default '',
#  ADD zipcode varchar(8) not null default '',
#  ADD device varchar(255) not null default '';
  
#ALTER TABLE kalrav.users ADD email varchar(50) not null default '' after device;
alter table users drop column location;
alter table users drop column company_name;
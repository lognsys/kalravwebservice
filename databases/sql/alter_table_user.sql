#PJD - 21-03-17
# removing user_id from user table wrongly added.
# One -off script which is used to alter user table
#

alter table user drop column user_id;
alter table user add column location varchar(255) not null default '' after phone;


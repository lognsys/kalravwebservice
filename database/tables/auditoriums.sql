#
# create auditorium.sql
# 16/3/2017 pjd
# 
#

drop table if exists auditoriums;

CREATE TABLE auditoriums (
  
  #Surrogate primary key  
  id integer not null auto_increment primary key,
  
  #Optional auditorium_name
  auditorium_name varchar(64) not null default '',
  
  #Optional address
  address varchar(255) not null default '',
  
  #Optional lat_lon
  latitude DECIMAL(9,6) not null default 0,

  #Optional lat_lon
  longitude DECIMAL(9,6) not null default 0,
  
  #last_edit
  last_edit TIMESTAMP not null default current_timestamp on update current_timestamp
	 
) engine=InnoDB default CHARSET=utf8;

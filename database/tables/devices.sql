drop table if exists kalrav.devices;

CREATE TABLE IF NOT EXISTS kalrav.devices (
  #Surrogate primary key
	id integer not null auto_increment primary key,
     #Required start cast
	deviceToken varchar(255) not null default '',
	last_edit TIMESTAMP not null default current_timestamp on update current_timestamp
	
) ENGINE=InnoDB default CHARSET=utf8;
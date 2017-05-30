#group.sql 

drop table if exists user_bookings;

CREATE TABLE IF NOT EXISTS user_bookings 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
  
	#foreign key users.id	
	users_id integer not null default -1,
		        
	#Optional birthdate 	
	booking_for_date datetime default null,
    
	#Optional birthdate 	
	booking_made_date datetime default null,
    
	#Optional birthdate 	
	booking_seats_count integer not null default -1,
    
	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
alter table user_bookings add index (users_id);
alter table user_bookings add constraint constr_usersid UNIQUE (users_id);
alter table user_bookings add foreign key (users_id) 
   references users (id) on delete cascade
   			 on update cascade;

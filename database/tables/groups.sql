#group.sql 

drop table if exists groups;

CREATE TABLE IF NOT EXISTS groups 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    	#required group name 
	group_name varchar(32) not null,
	         
	#foreign key users.id	
	user_id integer not null default 0,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	UNIQUE(group_name)

) ENGINE =InnoDB default CHARSET=utf8;



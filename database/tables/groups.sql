#group.sql 

drop table if exists groups;

CREATE TABLE IF NOT EXISTS groups 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key group_type.id	
	group_type_id integer not null,
	         
	#foreign key users.id	
	user_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;



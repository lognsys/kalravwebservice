drop table if exists notifications;

CREATE TABLE IF NOT EXISTS notifications 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key groups.id	
	#users_id integer  default -1,
	         
	#foreign key dramas.id	
	#dramas_id integer  default -1,
	
    #required by spring security enabled =1 , disabled=0 
	notify tinyint  not null default 1,
    
    #message
    message varchar(300) not null,
	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;

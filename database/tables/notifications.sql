drop table if exists notifications;

CREATE TABLE IF NOT EXISTS notifications 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    
    #required by spring security enabled =1 , disabled=0 
	notify tinyint  not null default 1,
    
    #message
    message varchar(300) not null,
    
    #foreign key groups.id	
	userId integer  default -1,
	         
	#foreign key dramas.id	
	dramaId integer  default -1,
	 
     #message
    realname varchar(50) not null,
    #message
    dramaTitle varchar(50) not null,
  
	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
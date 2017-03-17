# drama.sql 
#  
#
#
CREATE TABLE IF NOT EXISTS drama  
(
	#Surrogate primary key
	id integer not null auto_increment primary key,
     
	#Required drama title
        title varchar(64) not null,
	 
	#Optional Genre 
	genre varchar(64) not null default '',

	#Required start cast
	star_cast varchar(255) not null default '',
		   
	#Optional director 
	director varchar(64) not null default '',
		         
	#Optional writer
	writer varchar(64) not null default '',
			       
	#Optional description
	description varchar(45) not null default '',
				       
	#foreign key auditorium.id 
	auditorium_id varchar(45) not null default '',
					    
	#Optional date 		
	date datetime not null default current_timestamp,

	last_edit TIMESTAMP not null default current_timestamp on update current_timestamp

) ENGINE=InnoDB default CHARSET=utf8;

create index drama_title_idx 
on drama (title);

create index drama_director_idx 
on drama (director);

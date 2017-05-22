# dramas.sql 
#  @author - PJD 04/04/17 created table dramas
#  
# CHANGE LOG : MS - 27/04/17 add column drama_length,music
#

DROP TABLE IF EXISTS dramas;

CREATE TABLE dramas  
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

	#Optional avg_rating
	avg_rating double(2,1) not null default 0,

	imageurl varchar(255) not null default '',
	
	drama_length varchar(128) not null default '',
    
	music varchar(128) not null default '',
	drama_language varchar(150) not null default '',

	last_edit TIMESTAMP not null default current_timestamp on update current_timestamp
	
) ENGINE=InnoDB default CHARSET=utf8;

create index dramas_title_idx 
on dramas (title);

create index drama_director_idx 
on dramas (director);

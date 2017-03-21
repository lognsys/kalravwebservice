/**** users table **/
# user.sql
# Use:  User registration.
# 
# Initialization for testing : tbd
#
# Change History: 
# 	3/15/17 pjs make username, realname index  
#

drop table if exists users;

CREATE TABLE IF NOT EXISTS user  
(
	# Surrogate Primary Key	
	id integer auto_increment primary key,
    	
	# oauth id from (Facebook or google)
	auth_id varchar(45) not null default '',
       
    	# username is an email address
	username varchar(64) not null default '',

	# Optional realname
	realname varchar(64) not null default '',

	# Optional phone
	phone varchar(32) not null default '',
	
	#optional provenance
	provenance varchar(16) not null default '',
		  
	#required by spring security enabled =1 , disabled=0 
	enabled tinyint  not null default 0,
		        
	# Optional notification to users 
	notification tinyint not null default 0,
			        
	#Optional birthdate 	
	birthdate varchar(16) not null default '',

	#drama average rating
	avg_rating double(2,1) not null default 0,

	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE = InnoDB default CHARSET=utf8;

#create index users_idx on users(username, realname);

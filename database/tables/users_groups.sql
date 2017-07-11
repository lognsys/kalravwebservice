#group.sql 

drop table if exists users_groups;

CREATE TABLE IF NOT EXISTS users_groups 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key groups.id	
	groups_id integer not null,
	         
	#foreign key users.id	
	users_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	# adding unique constraint to users_id
    CONSTRAINT uc_users_groups UNIQUE (users_id)

) ENGINE =InnoDB default CHARSET=utf8;
alter table users_groups add index (users_id);
alter table users_groups add constraint constr_usersid UNIQUE (users_id);
alter table users_groups add foreign key (users_id) 
   references users (id) on delete cascade
   			 on update cascade;
alter table users_groups add index (groups_id);
alter table users_groups add foreign key (groups_id) 
   references groups (id) on delete cascade
   			 on update cascade;


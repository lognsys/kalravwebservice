drop table if exists sub_groups;

CREATE TABLE IF NOT EXISTS sub_groups 
(
	#Surrogate primary key
   	id integer auto_increment primary key not null,
        
    #foreign key groups.id	
	sub_groups_name varchar(60) not null default '',
	         
	#foreign key dramas.id	
	 group_id integer not null default 0,

	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
alter table  sub_groups add index (group_id);
alter table  sub_groups add foreign key (group_id) 
   references  groups (id) on delete cascade
   			 on update cascade;
     
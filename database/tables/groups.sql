# group.sql 
# @author pdoshi
#
#
drop table if exists groups;

CREATE TABLE IF NOT EXISTS groups
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #required group name 
	group_name varchar(32) not null,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	UNIQUE(group_name)

) ENGINE =InnoDB default CHARSET=utf8;

create index groups_grousp_name_idx on groups(group_name);
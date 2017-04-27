drop table if exists dramas_groups;

CREATE TABLE IF NOT EXISTS dramas_groups 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key groups.id	
	groups_id integer not null,
	         
	#foreign key dramas.id	
	 dramas_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
alter table  dramas_groups add index (dramas_id);
alter table  dramas_groups add index (groups_id);
alter table  dramas_groups add constraint constr_dramasid UNIQUE (dramas_id);
alter table  dramas_groups add foreign key (dramas_id) 
   references  dramas (id) on delete cascade
   			 on update cascade;


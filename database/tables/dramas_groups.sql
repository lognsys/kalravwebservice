# author : monika 
# delete : if we delete by group_id && dramas_id in drama_group than that drama will be removed from  that drama_group





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
#alter table  dramas_groups add constraint constr_dramasid UNIQUE (dramas_id);
alter table  dramas_groups add constraint constr_groupssid UNIQUE (groups_id);

alter table  dramas_groups add foreign key (dramas_id) 
   references  dramas (id) on delete cascade
   			 on update cascade;
alter table  dramas_groups add foreign key (groups_id) 
   references  groups (id) on delete cascade
   			 on update cascade;

ALTER TABLE `kalrav`.`dramas_groups` 
DROP INDEX `constr_groupssid` ;

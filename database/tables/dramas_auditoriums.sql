drop table if exists dramas_auditoriums;

CREATE TABLE IF NOT EXISTS dramas_auditoriums 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key groups.id	
	auditoriums_id integer not null,
	         
	#foreign key dramas.id	
	 dramas_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
alter table  dramas_auditoriums add index (dramas_id);
alter table  dramas_auditoriums add index (auditoriums_id);
alter table  dramas_auditoriums add constraint constr_dramasid UNIQUE (dramas_id);
alter table  dramas_auditoriums add foreign key (dramas_id) 
   references  dramas (id) on delete cascade
   			 on update cascade;
             alter table  dramas_auditoriums add foreign key (auditoriums_id) 
   references  auditoriums (id) on delete cascade
   			 on update cascade;
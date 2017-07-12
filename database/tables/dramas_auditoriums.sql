drop table if exists dramas_auditoriums;

CREATE TABLE IF NOT EXISTS dramas_auditoriums 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key groups.id	
	auditoriums_id integer not null,
	         
	#foreign key dramas.id	
	 dramas_id integer not null default -1,
	 
	#drama_date
    date DATE NOT NULL,
	
    #drama_time
    time TIME NOT NULL,
	
    last_edit timestamp not null default current_timestamp on update current_timestamp,

	CONSTRAINT uc_drama_audtoriums UNIQUE (dramas_id)
	
) ENGINE =InnoDB default CHARSET=utf8;

create index dramas_autditorium_dramas_id_idx on dramas_auditoriums(dramas_id);
create index dramas_autditorium_auditoriums_id_idx on dramas_auditoriums(auditoriums_id);
alter table  dramas_auditoriums add foreign key (dramas_id) 
   references  dramas (id) on delete cascade
   			 on update cascade;
alter table  dramas_auditoriums add foreign key (auditoriums_id) 
   references  auditoriums (id) on delete cascade
   			 on update cascade;
             
ALTER TABLE `kalrav`.`dramas_auditoriums` 
DROP FOREIGN KEY `dramas_auditoriums_ibfk_1`;
ALTER TABLE `kalrav`.`dramas_auditoriums` 
ADD CONSTRAINT `dramas_auditoriums_ibfk_1`
  FOREIGN KEY (`dramas_id`)
  REFERENCES `kalrav`.`dramas` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  ALTER TABLE `kalrav`.`dramas_auditoriums` 
DROP INDEX `uc_drama_audtoriums` ,
ADD INDEX `uc_drama_audtoriums` (`dramas_id` ASC);

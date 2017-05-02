DROP TABLE IF EXISTS confirmation_codes;
CREATE TABLE confirmation_codes
(  
   # Surrogate primary key
   id integer auto_increment primary key,
  
  
   #required  
   confirmation_code_number VARCHAR(100) NOT NULL DEFAULT '',
            
	#foreign key dramas.id	
	 dramas_id integer not null default -1,

	date timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
alter table  confirmation_codes add constraint constr_dramasid UNIQUE (dramas_id);
alter table  confirmation_codes add foreign key (dramas_id) 
   references  dramas (id) on delete cascade
   			 on update cascade;
select * from  confirmation_codes;

 ALTER TABLE confirmation_codes
    ADD group_id INTEGER,
    ADD CONSTRAINT FOREIGN KEY(group_id) REFERENCES groups(id);

 ALTER TABLE confirmation_codes
    ADD user_id INTEGER,
    ADD CONSTRAINT FOREIGN KEY(user_id) REFERENCES users(id);
       
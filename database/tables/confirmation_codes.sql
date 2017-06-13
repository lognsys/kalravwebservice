DROP TABLE IF EXISTS confirmation_codes;
CREATE TABLE confirmation_codes
(  
   # Surrogate primary key
   id integer auto_increment primary key,
    
   #required  
   confirmation_code_number VARCHAR(100) NOT NULL DEFAULT '',
            
   #foreign key dramas.id	
   dramas_id integer not null default 0,

   #foreign key dramas.id	
   user_id integer not null default 0,
   
   date timestamp not null default current_timestamp on update current_timestamp

) ENGINE =InnoDB default CHARSET=utf8;
alter table  confirmation_codes add constraint constr_dramasid UNIQUE (dramas_id);
alter table  confirmation_codes add foreign key (dramas_id) references  dramas (id);
alter table  confirmation_codes add foreign key(user_id) references users(id);
       
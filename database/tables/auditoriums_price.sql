drop table if exists auditoriums_price;

CREATE TABLE IF NOT EXISTS auditoriums_price 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key groups.id	
	auditoriums_id integer not null,
	#foreign key dramas.id	
	 dramas_id integer not null default -1,
	         
	#price	
	 price double not null default 0,
	 
	#iend
     istart int not null default 0,
	
    #iend
     iend int not null default 0,
	
    last_edit timestamp not null default current_timestamp on update current_timestamp
	
) ENGINE =InnoDB default CHARSET=utf8;
create index auditoriums_price_dramasid_idx on auditoriums_price(dramas_id);

create index auditoriums_price_auditoriumsid_idx on auditoriums_price(auditoriums_id);
alter table  auditoriums_price add foreign key (dramas_id) 
   references  dramas (id) on delete cascade
   			 on update cascade;
alter table  auditoriums_price add foreign key (auditoriums_id) 
   references  auditoriums (id) on delete cascade
   			 on update cascade;
             

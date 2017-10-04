# admob_adverts.sql
# @author - PJD 01-04-17
#
#

drop table if exists admob_adverts;

CREATE TABLE IF NOT EXISTS admob_adverts 
( 
  # surrogate primary key
  id integer not null,

  # Required no of days to run adverst
  duration_days integer not null,
  
  #Required name of the customer hosting add
  customer_name varchar(45) not null default '',
  
  #Optional depending on the add 
  headline varchar(25) not null default '',
  
  #Optional depending on the add
  text1 varchar(35) not null default '',
  
  #Optional depending on the add
  text2 varchar(35) not null default '',
  
  #Optional depending on the add
  image_url varchar(164) not null default '',
  
  #Optional depending on the add
  video_url varchar(164) not null default '',
  
  #Foreign key adverts_types.id
  adverts_type_id integer not null,

  #Foreign key users.id
  users_id integer not null,
    
  last_edit TIMESTAMP not null default CURRENT_TIMESTAMP on update current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table admob_adverts add foreign key (adverts_type_id)
  references adverts_types(id) on update cascade on delete cascade;


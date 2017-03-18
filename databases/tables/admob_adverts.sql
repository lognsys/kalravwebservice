# admob_adverts.sql
#
#
drop table if exists admob_adverts;

CREATE TABLE IF NOT EXISTS admob_adverts 
(
  id integer not null,

  adv_type_id integer not null,
  
  duration_days integer not null default 0,
  
  customer_name varchar(45) not null default '',
  
  user_id integer not null default 0,
  
  headline varchar(25) not null default '',
  
  text1 varchar(35) not null default '',
  
  text2 varchar(35) not null default '',
  
  image_url varchar(128) not null default '',
  
  last_edit TIMESTAMP not null default CURRENT_TIMESTAMP on update current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


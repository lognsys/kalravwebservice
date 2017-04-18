#ratings.sql
#
# CHANGE LOG:
# PJD - 04/04/17 
#	1. add users_id foreign key
#	2. add dramas_id foreign key
# 

drop table if exists users_reviews;

CREATE TABLE IF NOT EXISTS users_reviews 
(
  # Surrogate primary key	
  id integer not null auto_increment primary key,
  
  #optional rating 
  comments varchar(5000) not null default 0,
  
  #optional date
  comments_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  
  #foreign key users.id
  users_id integer not null default 0,
  
  #foreign key dramas.id
  dramas_id integer not null default 0,
  
  last_edit timestamp not null default current_timestamp on update current_timestamp
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index ratings_drama_id_idx on ratings(drama_id);
create index ratings_rating_date_idx on ratings(rating_date);


#ratings.sql
#
# CHANGE LOG:
# PJD - 04/04/17 
#	1. add users_id foreign key
#	2. add dramas_id foreign key
# 

drop table if exists ratings;

CREATE TABLE IF NOT EXISTS ratings 
(
  # Surrogate primary key	
  id integer not null auto_increment primary key,
  
  #optional rating 
  rating integer not null default 0,
  
  #optional date
  rating_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  
  #foreign key users.id
  users_id integer not null default 0,
  
  #foreign key dramas.id
  dramas_id integer not null default 0,
  
  last_edit timestamp not null default current_timestamp on update current_timestamp
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index ratings_dramas_id_idx on ratings(dramas_id);
create index ratings_date_idx on ratings(rating_date);

ALTER TABLE `kalrav`.`ratings` 
CHANGE COLUMN `rating` `rating` DOUBLE NOT NULL DEFAULT '0' ;

ALTER TABLE `kalrav`.`ratings` 
ADD CONSTRAINT `dramas_id`
  FOREIGN KEY (`dramas_id`)
  REFERENCES `kalrav`.`dramas` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `kalrav`.`ratings` 
ADD INDEX `users_id_idx` (`users_id` ASC);
ALTER TABLE `kalrav`.`ratings` 
DROP FOREIGN KEY `users_id`;
ALTER TABLE `kalrav`.`ratings` 
ADD CONSTRAINT `users_id`
  FOREIGN KEY (`users_id`)
  REFERENCES `kalrav`.`users` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;

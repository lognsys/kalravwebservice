#
# @author - PJD 24/03/17
# 
#

DROP TABLE IF EXISTS advert_types;

CREATE TABLE advert_types 
(
   id integer auto_increment primary key,
   advert_name VARCHAR(45) NOT NULL DEFAULT '',
   last_edit TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

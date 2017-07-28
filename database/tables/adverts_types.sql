#
# @author - PJD 24/03/17
# 
#
DROP TABLE IF EXISTS adverts_types;
CREATE TABLE adverts_types
(  
   # Surrogate primary key
   id integer auto_increment primary key,
   
   #required  
   advert_name VARCHAR(45) NOT NULL DEFAULT '',
   
   last_edit TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 insert into adverts_types (advert_name)
 values
 ('interstitial'),
 ('banner');
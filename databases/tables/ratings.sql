#ratings.sql
#
# 

CREATE TABLE IF NOT EXISTS ratings 
(
  id integer not null auto_increment primary key,
  rating integer not null default 0,
  rating_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  last_edit timestamp not null default current_timestamp on update current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

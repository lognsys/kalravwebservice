#databaase tables records Testing

mysql kalrav -e "delete from users where id =1";
mysql kalrav -e "delete from users where username="pdoshi@gmail.com" ";

mysql kalrav -e "delete from dramas_groups where groups_id =1 and dramas_id =1";
mysql kalrav -e "update dramas_groups set groups_id= 1 ,dramas_id=1 where id=2";


mysql kalrav -e "delete from dramas_auditoriums where dramas_id =1 and auditoriums_id =1 and date='2017-10-17'";
mysql kalrav -e "update dramas_auditoriums set auditoriums_id= 1 ,dramas_id=1,  date='2017-10-17',time='11:20 am' where id=2";

mysql kalrav -e "'delete from auditoriums_price where id=1";
mysql kalrav -e "delete from auditoriums_price where id=2";
mysql kalrav -e "delete from auditoriums_price where id=3";

mysql kalrav -e "update auditoriums_price set price= 100 ,auditoriums_id=2 where id=4";
mysql kalrav -e "update auditoriums_price set price= 300  where id=5";
mysql kalrav -e "update auditoriums_price set price= 400  where id=6";



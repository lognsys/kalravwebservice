select * from  groups;
select * from  dramas_groups;

select * from  sub_groups;
SET FOREIGN_KEY_CHECKS = 0; 
Truncate groups;
SET FOREIGN_KEY_CHECKS = 1;

insert into groups values(1,"None",'2017-05-05 00:00:00');
insert into groups values(2,"Youth",'2017-05-05 00:00:00');
insert into groups values(3,"Ladies",'2017-05-05 00:00:00');

insert into sub_groups values(1,"Girls",2,'2017-05-05 00:00:00');
insert into sub_groups values(2,"Boys",2,'2017-05-05 00:00:00');
insert into sub_groups values(3,"Woman",3,'2017-05-05 00:00:00');


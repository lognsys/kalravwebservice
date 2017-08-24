
select * from dramas;
select * from auditoriums_price;

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(1,1,'2017-09-17',"11:25 am");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(1,2,'2017-08-18',"12:30 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(1,3,'2017-08-19',"1:25 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(1,4,'2017-08-20',"4:25 pm");
insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(1,6,'2017-08-22',"6:25 am");


insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(2,1,'2017-09-17',"7:25 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(2,2,'2017-08-21',"12:30 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(2,16,'2017-08-23',"1:25 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(3,1,'2017-08-19',"10:25 am");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(3,2,'2017-08-21',"5:30 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(3,5,'2017-08-21',"1:25 pm");

insert into dramas_auditoriums (auditoriums_id,dramas_id,date,time)
values(3,5,'2017-08-16',"1:25 pm");
update dramas_auditoriums set time="01:25 pm"
where id=13
# sp_drama_list
#
# 10/05/2017 pjd
#
# Select dramas.id, dramas.title, dramas.date, group_name, auditorium_name,  
# from tables dramas_groups, dramas_auditorium, dramas table  
#
# Input: None
#
# Output: dramas_groups, drama_audi
#
# Processing:
#
# Change history:
#   NONE

drop procedure if exists sp_drama_list

delimiter //
create procedure sp_drama_list(out drama_id int, 
			       out drama_title varchar(64),   
			       out drama_date date,
			       out group_name varchar(32),
			       out auditorium_name varchar(64))
	
    begin

    select d.id, title, drama_date, group_name, auditorium_name into 
    
    from dramas d inner join


    end;
//
			  


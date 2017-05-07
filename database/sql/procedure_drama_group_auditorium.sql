
# drop procedure
DROP PROCEDURE drama_data;

#create procedure using drama, group , and auditorium
CREATE PROCEDURE drama_data()
Select
drama.id,
drama.title,
grp.group_name,
a.auditorium_name,
drama.date
from  
dramas as drama
INNER join groups as grp
on
drama.group_id=grp.id
INNER join auditoriums as  a
on
drama.auditorium_id=a.id;

# to call  drama data procedure
CALL drama_data() ;

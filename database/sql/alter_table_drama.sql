/**Monika - 27-04-17
#adding drama_length,music column to drama table 
*/

ALTER TABLE kalrav.dramas
  ADD imageurl varchar(150) not null default '', 
 
  
/*ALTER TABLE kalrav.dramas
 ADD drama_length varchar(150) not null default '', 
 
  
ALTER TABLE kalrav.dramas
  ADD music varchar(150) not null default '', 
*/
 ALTER TABLE dramas
    ADD group_id INTEGER not null default '',
    ADD CONSTRAINT FOREIGN KEY(group_id) REFERENCES groups(id);
    
    
     ALTER TABLE dramas
    ADD group_name varchar(100);
  
  ALTER TABLE dramas
    ADD auditorium_id INTEGER,
    ADD CONSTRAINT FOREIGN KEY(auditorium_id) REFERENCES auditoriums(id);
    
	ALTER TABLE dramas
    ADD auditorium_name varchar(100) not null default '' ;
   

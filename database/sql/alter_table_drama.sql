#Monika - 27-04-17
# adding drama_length,music column to drama table 

ALTER TABLE kalrav.drama
  ADD imageurl varchar(150) not null default '' , 
 
  
ALTER TABLE kalrav.dramas
  ADD drama_length varchar(150) not null default '' , 
 
  
ALTER TABLE kalrav.dramas
  ADD music varchar(150) not null default '' 
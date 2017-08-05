insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 1, 2);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 2, 1);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 3, 1);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 4, 1);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 5, 1);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 6, 1);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 7, 1);
insert into row_seat(row_num, row_name, seat_num, auditoriums_id) VALUES (1, 'Z', 8, 1);

insert into refer_seat_status(seat_status_code,seat_status_description) values("Not Available", "Already Booked");
insert into refer_seat_status(seat_status_code,seat_status_description) values("Available", "Not Booked Yet");

truncate row_seat
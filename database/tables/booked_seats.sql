
drop table if exists booked_seats;

CREATE TABLE `kalrav`.`booked_seats` (
  `id` INT NOT NULL  auto_increment,
  `booking_id` INT NOT NULL,
  `row_seats_id` INT NOT NULL,
  `seat_status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_booking_id` (`booking_id` ASC),
  INDEX `fk_row_seats_id` (`row_seats_id` ASC),
  CONSTRAINT `fk_booking_id`
    FOREIGN KEY (`booking_id`)
    REFERENCES `kalrav`.`booking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_row_seats_id`
    FOREIGN KEY (`row_seats_id`)
    REFERENCES `kalrav`.`row_seat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 );
 ALTER TABLE `kalrav`.`booked_seats` 
DROP FOREIGN KEY `fk_row_seats_id`;
ALTER TABLE `kalrav`.`booked_seats` 
DROP INDEX `fk_row_seats_id` ,
ADD INDEX `fk_row_seats_id1` (`row_seats_id` ASC);
ALTER TABLE `kalrav`.`booked_seats` 
ADD CONSTRAINT `fk_row_seats_id1`
  FOREIGN KEY (`row_seats_id`)
  REFERENCES `kalrav`.`row_seat` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
 
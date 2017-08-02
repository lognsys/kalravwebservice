
drop table if exists booked_seats;

CREATE TABLE `kalrav`.`booked_seats` (
  `id` INT NOT NULL,
  `booking_id` INT NOT NULL,
  `row_seats_id` INT NOT NULL,
  `refer_seat_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_booking_id` (`booking_id` ASC),
  INDEX `fk_row_seats_id` (`row_seats_id` ASC),
  INDEX `fk_refer_seats_id` (`refer_seat_id` ASC),
  CONSTRAINT `fk_booking_id`
    FOREIGN KEY (`booking_id`)
    REFERENCES `kalrav`.`booking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_row_seats_id`
    FOREIGN KEY (`row_seats_id`)
    REFERENCES `kalrav`.`row_seat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_refer_seats_id`
    FOREIGN KEY (`refer_seat_id`)
    REFERENCES `kalrav`.`refer_seat_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

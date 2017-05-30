CREATE TABLE IF NOT EXISTS `kalrav`.`booked_dramas_seats` (
  `bds_audi_id` INT NOT NULL,
  `bds_row_number` VARCHAR(15) NOT NULL,
  `seat_number` VARCHAR(15) NOT NULL,
  `drama_date` DATETIME NOT NULL,
  `bds_drama_id` INT NOT NULL,
  `bds_booking_id` INT NOT NULL,
  PRIMARY KEY (`seat_number`),
  INDEX `audi_id_idx` (`bds_audi_id` ASC),
  INDEX `row_number_idx` (`bds_row_number` ASC),
  INDEX `drama_id_idx` (`bds_drama_id` ASC),
  INDEX `booking_id_idx` (`bds_booking_id` ASC),
  CONSTRAINT `bds_audi_id`
    FOREIGN KEY (`bds_audi_id`)
    REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `bds_row_number`
    FOREIGN KEY (`bds_row_number`)
    REFERENCES `kalrav`.`auditorium_row_seats` (`row_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `bds_drama_id`
    FOREIGN KEY (`bds_drama_id`)
    REFERENCES `kalrav`.`dramas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `bds_booking_id`
    FOREIGN KEY (`bds_booking_id`)
    REFERENCES `kalrav`.`user_bookings` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
CREATE TABLE IF NOT EXISTS `kalrav`.`all_dramas_seats` (
  `audi_id` INT NOT NULL,
  `row_number` VARCHAR(15) NOT NULL,
  `seat_number` VARCHAR(15) NOT NULL,
  `seat_status_code` INT NULL,
  `drama_date` DATETIME NOT NULL,
  `drama_id` INT NOT NULL,
  `booking_id` INT NOT NULL,
  PRIMARY KEY (`seat_number`),
  INDEX `auditorium_id_idx` (`audi_id` ASC),
  INDEX `row_number_idx` (`row_number` ASC),
  INDEX `drama_id_idx` (`drama_id` ASC),
  INDEX `seat_status_code_idx` (`seat_status_code` ASC),
  INDEX `booking_id_idx` (`booking_id` ASC),
  CONSTRAINT `audi_id`
    FOREIGN KEY (`audi_id`)
    REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `row_number`
    FOREIGN KEY (`row_number`)
    REFERENCES `kalrav`.`auditorium_row_seats` (`row_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `kalrav`.`dramas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `seat_status_code`
    FOREIGN KEY (`seat_status_code`)
    REFERENCES `kalrav`.`refer_seat_status` (`seats_status_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `booking_id`
    FOREIGN KEY (`booking_id`)
    REFERENCES `kalrav`.`user_bookings` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
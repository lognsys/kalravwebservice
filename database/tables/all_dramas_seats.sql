drop table if exists all_dramas_seats;

CREATE TABLE IF NOT EXISTS `kalrav`.`all_dramas_seats` (
  # Surrogate Primary Key	
	id integer auto_increment primary key,
	
  `audi_id` INT NOT NULL,
  `row_seat_id` INT NOT NULL,
  `seat_number` VARCHAR(15) NOT NULL,
  `refer_seat_status_id` INT NOT NULL default 0,
  `drama_date` DATETIME NOT NULL,
  `booking_id` INT NOT NULL default 0,
  `drama_id` INT NOT NULL,
  INDEX `audi_id_idx` (`audi_id` ASC),
  INDEX `row_seat_id_idx` (`row_seat_id` ASC),
  INDEX `refer_seat_status_id_idx` (`refer_seat_status_id` ASC),
  INDEX `booking_id_idx` (`booking_id` ASC),
  INDEX `drama_id_idx` (`drama_id` ASC),
  CONSTRAINT `audi_id`
    FOREIGN KEY (`audi_id`)
    REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `row_seat_id`
    FOREIGN KEY (`row_seat_id`)
    REFERENCES `kalrav`.`row_seat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `refer_seat_status_id`
    FOREIGN KEY (`refer_seat_status_id`)
    REFERENCES `kalrav`.`refer_seat_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `booking_id`
    FOREIGN KEY (`booking_id`)
    REFERENCES `kalrav`.`booking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `kalrav`.`dramas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB default CHARSET=utf8;
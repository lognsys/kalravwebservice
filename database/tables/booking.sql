drop table if exists booking;

CREATE TABLE IF NOT EXISTS `kalrav`.`booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `booking_date` DATETIME NOT NULL,
  `users_id` INT(11) NOT NULL,
  `booking_seatcount` INT NOT NULL,
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dramas_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `dramas_id`),
  INDEX `fk_booking_users1_idx` (`users_id` ASC),
  INDEX `fk_booking_dramas1_idx` (`dramas_id` ASC),
  CONSTRAINT `fk_booking_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `kalrav`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_dramas1`
    FOREIGN KEY (`dramas_id`)
    REFERENCES `kalrav`.`dramas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

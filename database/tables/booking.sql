
drop table if exists booking;

CREATE TABLE `kalrav`.`booking` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `booking_date` VARCHAR(45) NOT NULL,
  `confirmation_no` VARCHAR(65) NOT NULL,
  `users_id` INT(11) NOT NULL,
  `booking_seatcount` INT(11) NOT NULL,
  `dramas_id` INT(11) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `auditoriums_id` INT(11) NOT NULL,
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_busers_id` (`users_id` ASC),
  INDEX `fk_bdramas_id` (`dramas_id` ASC),
  INDEX `fk_bauditoriums_id` (`auditoriums_id` ASC),
  CONSTRAINT `fk_busers_id`
    FOREIGN KEY (`users_id`)
    REFERENCES `kalrav`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bdramas_id`
    FOREIGN KEY (`dramas_id`)
    REFERENCES `kalrav`.`dramas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bauditoriums_id`
    FOREIGN KEY (`auditoriums_id`)
    REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `kalrav`.`booking` 
ADD COLUMN `price` DOUBLE NOT NULL AFTER `last_edit`;

ALTER TABLE `kalrav`.`booking` 
DROP COLUMN `booking_seatcount`;


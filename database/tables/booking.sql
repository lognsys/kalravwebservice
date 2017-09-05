#
# @author - pdoshi
# Description : This is used for booking confirmration
# CHANGE LOG :
# Updated Foreign key dramas_id to dramas_auditoriums_id
#
drop table if exists booking;

CREATE TABLE IF NOT EXISTS `kalrav`.`booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `booking_date` DATETIME NOT NULL,
  `confirmation_no` VARCHAR(64) NOT NULL,
  `users_id` INT(11) NOT NULL,
  `booking_seatcount` INT NOT NULL,
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dramas_auditoriums_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_booking_users1_idx` (`users_id` ASC),
  INDEX `fk_booking_dramas_auditoriums_id_idx` (`dramas_auditoriums_id` ASC),
  CONSTRAINT `fk_booking_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `kalrav`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_dramas_auditoriums_id`
    FOREIGN KEY (`dramas_auditoriums_id`)
    REFERENCES `kalrav`.`dramas_auditoriums` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


ALTER TABLE `kalrav`.`booking` 
ADD COLUMN `status` VARCHAR(45) NOT NULL ;
alter  table booking  modify booking_date varchar(20);
  
CREATE TABLE IF NOT EXISTS `kalrav`.`refer_seat_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seat_status_code` VARCHAR(15) NOT NULL,
  `seat_status_description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `seat_status_code_UNIQUE` (`seat_status_code` ASC))
ENGINE = InnoDB default CHARSET=utf8;
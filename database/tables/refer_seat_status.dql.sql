CREATE TABLE IF NOT EXISTS `kalrav`.`refer_seat_status` (
  `seats_status_code` INT NOT NULL,
  `seats_status_description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`seats_status_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
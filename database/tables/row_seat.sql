CREATE TABLE IF NOT EXISTS `kalrav`.`row_seat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `row_num` VARCHAR(16) NULL,
  `seat_num` VARCHAR(16) NULL,
  `seat_count` INT NULL,
  `auditoriums_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_row_seat_auditoriums1_idx` (`auditoriums_id` ASC),
  CONSTRAINT `fk_row_seat_auditoriums1`
    FOREIGN KEY (`auditoriums_id`)
    REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB default CHARSET=utf8;
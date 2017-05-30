
CREATE TABLE IF NOT EXISTS `kalrav`.`auditorium_row_seats` (
  `auditorium_id` INT NOT NULL,
  `row_number` VARCHAR(15) NOT NULL,
  `seat_count` INT NOT NULL,
  PRIMARY KEY (`row_number`),
  INDEX `auditorium_id_idx` (`auditorium_id` ASC),
  CONSTRAINT `auditorium_id`
    FOREIGN KEY (`auditorium_id`)
    REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
drop  table row_seat;


CREATE TABLE IF NOT EXISTS `kalrav`.`row_seat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `row_num` INT(11) NOT NULL DEFAULT 0,
  `row_name` VARCHAR(4) NOT NULL DEFAULT "",
  `seat_num` INT(16) NOT NULL DEFAULT 0,
  `auditoriums_id` INT(11) NOT NULL,
  last_edit timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (`id`),
  INDEX `fk_row_seat_auditoriums__idx` (`auditoriums_id` ASC),
  CONSTRAINT `fk_row_seat_auditoriums__` FOREIGN KEY (`auditoriums_id`) REFERENCES `kalrav`.`auditoriums` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB default CHARSET=utf8;

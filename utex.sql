-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema utex
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `utex` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `utex` ;

-- -----------------------------------------------------
-- Table `utex`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utex`.`person` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `image` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `utex`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utex`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `person` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_person` (`person` ASC) VISIBLE,
  CONSTRAINT `fk_user_person`
    FOREIGN KEY (`person`)
    REFERENCES `utex`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


--------------------------------------
-- inserta en tabla person
------------------------------
INSERT INTO `utex`.`person` (`name`, `lastname`, `age`, `email`, `phone`, `birthday`, `image`)
VALUES ('Lizbeth', 'Santibañez', 19, 'lizbethsantibañez@example.com', '2455942', '2004-05-13', NULL);

INSERT INTO `utex`.`person` (`name`, `lastname`, `age`, `email`, `phone`, `birthday`, `image`)
VALUES ('Karol', 'Cruz', 14, 'karolcruz@example.com', '3858592', '2008-09-20', NULL);

INSERT INTO `utex`.`person` (`name`, `lastname`, `age`, `email`, `phone`, `birthday`, `image`)
VALUES ('Rodrigo', 'Valadez', 23, 'rodrigovaladez@example.com', '9257813', '2004-05-13', NULL);

INSERT INTO `utex`.`person` (`name`, `lastname`, `age`, `email`, `phone`, `birthday`, `image`)
VALUES ('Luisa', 'Soto', 43, 'luisasoto@example.com', '6202992', '1978-04-14', NULL);

INSERT INTO `utex`.`person` (`name`, `lastname`, `age`, `email`, `phone`, `birthday`, `image`)
VALUES ('Ismael', 'Basa', 44, 'ismaelbasa@example.com', '1400201', '1979-02-05', NULL);
----------------------------------- 
-- Insert en tabla user
------------------------------------
INSERT INTO `utex`.`user` (`username`, `password`, `role`, `person`)
VALUES ( 'Lizbeth', 'michifus7', 'admin', 38);

INSERT INTO `utex`.`user` (`username`, `password`, `role`, `person`)
VALUES ( 'Karolsan7', 'michifus7', 'servicio', 39);

INSERT INTO `utex`.`user` (`username`, `password`, `role`, `person`)
VALUES ( 'Luisa14', 'michifus7', 'admin', 41);

select * from person;
select * from user;
SELECT * FROM person JOIN user ON person.id=user.person;
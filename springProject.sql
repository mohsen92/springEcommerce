-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema springProject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema springProject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `springProject` DEFAULT CHARACTER SET latin1 ;
USE `springProject` ;

-- -----------------------------------------------------
-- Table `springProject`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(50) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `image_id` INT(11) NULL DEFAULT NULL,
  `quantityInStock` INT(11) NULL DEFAULT NULL,
  `product_category` INT(11) NULL DEFAULT NULL,
  `disable` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  INDEX `product_category` (`product_category` ASC),
  CONSTRAINT `categoryFK`
    FOREIGN KEY (`product_category`)
    REFERENCES `springProject`.`category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `fname` VARCHAR(50) NOT NULL,
  `lname` VARCHAR(50) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `zip` VARCHAR(5) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `user_email_verified` TINYINT(1) NOT NULL DEFAULT '0',
  `country` VARCHAR(50) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `registrationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `credit` DOUBLE NOT NULL,
  `confirmToken` VARCHAR(50) NOT NULL,
  `isAdmin` TINYINT(1) NOT NULL DEFAULT '0',
  `passwordResetToken` VARCHAR(50) NULL DEFAULT NULL,
  `expirationDate` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`cartItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`cartItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  INDEX `product_cart_idx` (`product_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `product_cart`
    FOREIGN KEY (`product_id`)
    REFERENCES `springProject`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_cart`
    FOREIGN KEY (`user_id`)
    REFERENCES `springProject`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`chargecard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`chargecard` (
  `creditCard_id` INT(11) NOT NULL AUTO_INCREMENT,
  `amonut` DOUBLE NOT NULL,
  `number` MEDIUMTEXT NOT NULL,
  `printed` TINYINT(1) NULL DEFAULT '0',
  `charged` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`creditCard_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`order` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `shipAddress` VARCHAR(50) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  `country` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `zip` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `productorder_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `springProject`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`orderItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`orderItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `quantityOrdered` INT(11) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `product_id` (`product_id` ASC),
  CONSTRAINT `orderdetails_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `springProject`.`order` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `orderdetails_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `springProject`.`product` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `springProject`.`productImage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springProject`.`productImage` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(200) NULL DEFAULT NULL,
  `productId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_images_product_idx` (`productId` ASC),
  CONSTRAINT `fk_product_images_product`
    FOREIGN KEY (`productId`)
    REFERENCES `springProject`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

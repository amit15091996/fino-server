/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.35 : Database - finoclient
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`finoclient` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `finoclient`;

/*Table structure for table `bank_transaction_details` */

DROP TABLE IF EXISTS `bank_transaction_details`;

CREATE TABLE `bank_transaction_details` (
  `bank_transaction_id` bigint NOT NULL,
  `balance_amount` decimal(20,2) DEFAULT NULL,
  `bank_transaction_date` date NOT NULL,
  `cash_amount` decimal(20,2) DEFAULT NULL,
  `collected_by` varchar(50) NOT NULL,
  `collection_amount` decimal(20,2) DEFAULT NULL,
  `online_amount` decimal(20,2) DEFAULT NULL,
  `recieved_from` varchar(50) NOT NULL,
  `remarks` varchar(5000) NOT NULL,
  PRIMARY KEY (`bank_transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bank_transaction_details` */

insert  into `bank_transaction_details`(`bank_transaction_id`,`balance_amount`,`bank_transaction_date`,`cash_amount`,`collected_by`,`collection_amount`,`online_amount`,`recieved_from`,`remarks`) values 
(1001,177.00,'2024-05-21',200.00,'9124783922',500.00,123.00,'kotak bank','partial payment'),
(1002,177.00,'2024-05-21',200.00,'9124783922',567.00,123.00,'kotak bank','partial payment'),
(1003,177.00,'2024-05-21',200.00,'9124783922',567.00,123.00,'kotak bank','partial payment'),
(1004,177.00,'2024-05-21',200.00,'9124783922',567.00,123.00,'kotak bank','partial payment'),
(1005,187.00,'2021-05-24',0.00,'9124783922',199.00,12.00,'kkbfr','gdadghas'),
(1006,567.00,'2025-05-24',0.00,'9124783922',567.00,0.00,'fcfch','dfgdfh');

/*Table structure for table `bank_transaction_sequence` */

DROP TABLE IF EXISTS `bank_transaction_sequence`;

CREATE TABLE `bank_transaction_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bank_transaction_sequence` */

insert  into `bank_transaction_sequence`(`sequence_name`,`next_val`) values 
('bank_transaction_details',1006);

/*Table structure for table `cms_transaction_details` */

DROP TABLE IF EXISTS `cms_transaction_details`;

CREATE TABLE `cms_transaction_details` (
  `cms_transaction_id` bigint NOT NULL,
  `balance_amount` decimal(20,2) DEFAULT NULL,
  `cash_amount` decimal(20,2) DEFAULT NULL,
  `cms_transaction_date` date NOT NULL,
  `collected_by` varchar(50) NOT NULL,
  `collection_amount` decimal(20,2) DEFAULT NULL,
  `online_amount` decimal(20,2) DEFAULT NULL,
  `recieved_from` varchar(50) NOT NULL,
  `remarks` varchar(5000) NOT NULL,
  PRIMARY KEY (`cms_transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cms_transaction_details` */

insert  into `cms_transaction_details`(`cms_transaction_id`,`balance_amount`,`cash_amount`,`cms_transaction_date`,`collected_by`,`collection_amount`,`online_amount`,`recieved_from`,`remarks`) values 
(1001,45.00,56.00,'2024-05-16','9124783922',113.00,12.00,'kkbk','test'),
(1002,228.00,0.00,'2024-05-20','9124783922',234.00,6.00,'hjk','test1'),
(1003,123.00,0.00,'2017-05-24','9124783922',123.00,0.00,'sffs','sdfsdf'),
(1004,77.00,34.00,'2021-05-05','9124783922',123.00,12.00,'awddd','sdsdv'),
(1005,345.00,0.00,'2014-05-16','9124783922',345.00,0.00,'cbcv','adasdas');

/*Table structure for table `cms_transaction_sequence` */

DROP TABLE IF EXISTS `cms_transaction_sequence`;

CREATE TABLE `cms_transaction_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cms_transaction_sequence` */

insert  into `cms_transaction_sequence`(`sequence_name`,`next_val`) values 
('cms_transaction_details',1005);

/*Table structure for table `fino_user_role_sequence` */

DROP TABLE IF EXISTS `fino_user_role_sequence`;

CREATE TABLE `fino_user_role_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_user_role_sequence` */

insert  into `fino_user_role_sequence`(`sequence_name`,`next_val`) values 
('fino_users_roles_details',2081);

/*Table structure for table `fino_user_sequence` */

DROP TABLE IF EXISTS `fino_user_sequence`;

CREATE TABLE `fino_user_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_user_sequence` */

insert  into `fino_user_sequence`(`sequence_name`,`next_val`) values 
('fino_users_details',234575);

/*Table structure for table `fino_users_details` */

DROP TABLE IF EXISTS `fino_users_details`;

CREATE TABLE `fino_users_details` (
  `fino_user_id` bigint NOT NULL,
  `password` varchar(1000) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_id` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `mobile_number` varchar(10) NOT NULL,
  PRIMARY KEY (`fino_user_id`),
  UNIQUE KEY `UK_3woqnl85yq7ewoec95h4dfhv1` (`mobile_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_users_details` */

insert  into `fino_users_details`(`fino_user_id`,`password`,`date_of_birth`,`email_id`,`first_name`,`last_name`,`mobile_number`) values 
(234568,'$2a$10$k.JdUlVPh1mFKi2QTYHiLu0APFK7fGQdxUTzaKMrbcrMiGAz6Gepu','1997-07-07','priya@gmail.com','Priyabrat','Panda','9124783911'),
(234570,'$2a$10$DcdiEalk5C8REXf6i2slB.ffDE6OaPqlLbhtNjql2m8p7ddmBCInK','1997-07-07','priyapanda@gmail.com','Priyabrat','Panda','9583963532'),
(234573,'$2a$10$Sch27.Y/LNzU/NQpBQeibOsxgi5jl9pIRkW3qMo4BhNZThIUTHT.K','1997-07-07','youknowme070797@gmail.com','Saipada','Panda','9124783922'),
(234574,'$2a$10$E/TBFQH7d2FC38nL5sugSOlPEx0QjKOuBS67JExSHTMPeoASpIQZ.','2024-05-02','panda@gmail.com','jack','ryan','9124783933'),
(234575,'$2a$10$EIRZ9BlLc9/KT/k936YdV.uEjL9DDVt5zoDFAUmy5YTNTPY4HZWle','2024-05-08','cvcv','ad','fsf','9124675643');

/*Table structure for table `fino_users_roles_details` */

DROP TABLE IF EXISTS `fino_users_roles_details`;

CREATE TABLE `fino_users_roles_details` (
  `fino_user_roles_id` bigint NOT NULL,
  `role_description` varchar(500) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `fino_user_details_fino_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`fino_user_roles_id`),
  KEY `FKdfwxycv9f2y7vodur5ajd5l5v` (`fino_user_details_fino_user_id`),
  CONSTRAINT `FKdfwxycv9f2y7vodur5ajd5l5v` FOREIGN KEY (`fino_user_details_fino_user_id`) REFERENCES `fino_users_details` (`fino_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_users_roles_details` */

insert  into `fino_users_roles_details`(`fino_user_roles_id`,`role_description`,`role_name`,`fino_user_details_fino_user_id`) values 
(2002,'This is USER Role','USER',234568),
(2023,'This is a ADMINRole','ADMIN',234568),
(2044,'This is a CLIENTRole','CLIENT',234570),
(2055,'This is a CLIENT Role','CLIENT',234573),
(2063,'This is a ADMIN Role','ADMIN',234574),
(2064,'This is a CLIENT Role','CLIENT',234575),
(2065,'This is a ADMIN Role','ADMIN',234573),
(2067,'this is USER Role','USER',234573);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

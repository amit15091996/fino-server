/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.33 : Database - finoclient
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
(1002,15.00,'2024-05-16',33.00,'9124783911',123.00,75.00,'p panda','remarks'),
(1003,79.00,'2023-05-10',33.00,'9124783911',123.00,11.00,'kkbk','test remarks 1');

/*Table structure for table `bank_transaction_sequence` */

DROP TABLE IF EXISTS `bank_transaction_sequence`;

CREATE TABLE `bank_transaction_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bank_transaction_sequence` */

insert  into `bank_transaction_sequence`(`sequence_name`,`next_val`) values 
('bank_transaction_details',1004);

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
(1001,38.00,457.00,'2024-05-15','9124783911',678.00,183.00,'kkbk','partialy paid  1'),
(1002,123.00,0.00,'2024-05-08','9124783911',123.00,0.00,'szv','svsvs');

/*Table structure for table `cms_transaction_sequence` */

DROP TABLE IF EXISTS `cms_transaction_sequence`;

CREATE TABLE `cms_transaction_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cms_transaction_sequence` */

insert  into `cms_transaction_sequence`(`sequence_name`,`next_val`) values 
('cms_transaction_details',1003);

/*Table structure for table `fino_diesel_tank_one` */

DROP TABLE IF EXISTS `fino_diesel_tank_one`;

CREATE TABLE `fino_diesel_tank_one` (
  `diesel_tank_one_id` bigint NOT NULL,
  `actual_sale_of_diesel_tank_one` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_four` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_one` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_three` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_two` varchar(255) DEFAULT NULL,
  `diesel_tank_one_date` date NOT NULL,
  `dip_of_diesel_tank_one` varchar(255) DEFAULT NULL,
  `inward_of_diesel_tank_one` varchar(255) DEFAULT NULL,
  `density` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `testing` varchar(255) DEFAULT NULL,
  `water_dip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`diesel_tank_one_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_diesel_tank_one` */

/*Table structure for table `fino_diesel_tank_one_sequence` */

DROP TABLE IF EXISTS `fino_diesel_tank_one_sequence`;

CREATE TABLE `fino_diesel_tank_one_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_diesel_tank_one_sequence` */

insert  into `fino_diesel_tank_one_sequence`(`sequence_name`,`next_val`) values 
('fino_diesel_tank_one',1);

/*Table structure for table `fino_diesel_tank_two` */

DROP TABLE IF EXISTS `fino_diesel_tank_two`;

CREATE TABLE `fino_diesel_tank_two` (
  `diesel_tank_two_id` bigint NOT NULL,
  `actual_sale_of_diesel_tank_two` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_one` varchar(255) DEFAULT NULL,
  `diesel_tank_two_date` date NOT NULL,
  `dip_of_diesel_tank_two` varchar(255) DEFAULT NULL,
  `inward_of_diesel_tank_two` varchar(255) DEFAULT NULL,
  `density` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `testing` varchar(255) DEFAULT NULL,
  `water_dip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`diesel_tank_two_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_diesel_tank_two` */

/*Table structure for table `fino_diesel_tank_two_sequence` */

DROP TABLE IF EXISTS `fino_diesel_tank_two_sequence`;

CREATE TABLE `fino_diesel_tank_two_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_diesel_tank_two_sequence` */

insert  into `fino_diesel_tank_two_sequence`(`sequence_name`,`next_val`) values 
('fino_diesel_tank_two',1);

/*Table structure for table `fino_petrol_tank_one` */

DROP TABLE IF EXISTS `fino_petrol_tank_one`;

CREATE TABLE `fino_petrol_tank_one` (
  `petrol_tank_one_id` bigint NOT NULL,
  `actual_sale_of_petrol_tank_one` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_one` varchar(255) DEFAULT NULL,
  `closing_reading_of_nozzle_two` varchar(255) DEFAULT NULL,
  `dip_of_petrol_tank_one` varchar(255) DEFAULT NULL,
  `inward_of_petrol_tank_one` varchar(255) DEFAULT NULL,
  `petrol_tank_one_date` date NOT NULL,
  `density` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `testing` varchar(255) DEFAULT NULL,
  `water_dip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`petrol_tank_one_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_petrol_tank_one` */

/*Table structure for table `fino_petrol_tank_one_sequence` */

DROP TABLE IF EXISTS `fino_petrol_tank_one_sequence`;

CREATE TABLE `fino_petrol_tank_one_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_petrol_tank_one_sequence` */

insert  into `fino_petrol_tank_one_sequence`(`sequence_name`,`next_val`) values 
('fino_petrol_tank_one',1);

/*Table structure for table `fino_user_role_sequence` */

DROP TABLE IF EXISTS `fino_user_role_sequence`;

CREATE TABLE `fino_user_role_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_user_role_sequence` */

insert  into `fino_user_role_sequence`(`sequence_name`,`next_val`) values 
('fino_users_roles_details',2111);

/*Table structure for table `fino_user_sequence` */

DROP TABLE IF EXISTS `fino_user_sequence`;

CREATE TABLE `fino_user_sequence` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_user_sequence` */

insert  into `fino_user_sequence`(`sequence_name`,`next_val`) values 
('fino_users_details',234580);

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
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`fino_user_id`),
  UNIQUE KEY `UK_3woqnl85yq7ewoec95h4dfhv1` (`mobile_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fino_users_details` */

insert  into `fino_users_details`(`fino_user_id`,`password`,`date_of_birth`,`email_id`,`first_name`,`last_name`,`mobile_number`,`is_active`) values 
(234568,'$2a$10$0IFXRv6iyIG/DikXLFA8zeql7B7JeYCXz.WvRrj9d8m4pIH6HmtGq','1997-07-16','priyabratpanda022@gmail.com','Priyabrat','Panda','9124783911',1),
(234570,'$2a$10$9IOhcRZwTWMYEhdyKUgZouvgmbb836Dl6yO.0N4WZIdKQkIZzoEzC','1995-07-07','priyabrat34022@gmail.com','Saipada','Panda','9583963532',1),
(234571,'$2a$10$MSBys1Lk3CKAuI8GeH7Zl.UHUwPpqKdd7j5kICe5Sfsbz5dU0gU2q','1997-01-08','lockscreen1997@gmail.com','priya','sahoo','9124783933',1),
(234572,'$2a$10$qlngpyoxbby.zEzMGBpeJ.j5LqtegDpQLsLsDzyLWp0yWEH/mZ40e','2024-05-05','xscszc','ccxz','asad','9124783944',1),
(234573,'$2a$10$CStc1GpF1XJUYL4mjhKnsOMLxLPOATOyfXst1E3wgC/wRK5e/419u','2024-05-06','cxzv','vdxdv','vcxcv','9124783955',1),
(234574,'$2a$10$eeEfUvMdnzSgH861v2QUWe/LQwNGqXhwgtPSeQlOJasQ4ij3hWheS','2024-05-05','xdvdxv','vxv','xcvc','9124783966',1),
(234575,'$2a$10$C4dTmbTi/zOSXg1hjlAzYe03g8/hLpcOjoW97ti74sUR/j5vESXDa','2024-05-13','x x','scsac','scscsa','9124783977',1),
(234576,'$2a$10$7K4oRF8kzdHc/2b/UQO9keY.i025TJNDno38/.GvlOHhS4Q0fxLrO','2024-05-02','9124783988','c c','cvcvc','9124783988',1),
(234577,'$2a$10$4IIkgXT9U5DHwVWePJkMtuBnP2WlN4U.kSdPARZyJuTYiy/kI14PO','2024-05-01','jackcrowe@gmail.com','jack','crowe','9124783999',1),
(234578,'$2a$10$.qWltmTZP7ntjuI93pI1COEpBkAGKbNnxtnYiQ86BQxWcgYadHKsy','2024-05-08','9124783900','9124783900','9124783900','9124783900',1),
(234579,'$2a$10$eC31w5TfBIcLQPCmQHRK3ejeofY6pYm92m0jelrxV2.3tECjUskK2','2024-06-06','ppanda@gmail.com','cdddd','wqdw','9124567432',1),
(234580,'$2a$10$WpQnvrKMO2O4d3X3Pf/4yuErNM8utB15YqdJT/Croc6oOPydnVQt6','2024-12-31','xcvb','asd','szxcvb','9876565432',1);

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
(2004,'This is a CLIENT Role','CLIENT',234570),
(2009,'admin role','ADMIN',234568),
(2013,'This is a USER Role','USER',234571),
(2023,'This is a ADMIN Role','ADMIN',234572),
(2073,'This is a ADMIN Role','ADMIN',234577),
(2084,'This is a USERRole','USER',234573),
(2085,'This is a ADMINRole','ADMIN',234574),
(2087,'This is a MANAGERRole','MANAGER',234575),
(2089,'This is a MANAGERRole','MANAGER',234576),
(2090,'This is a CLIENTRole','CLIENT',234578),
(2093,'This is a USER Role','USER',234579),
(2094,'This is a USER Role','USER',234580);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

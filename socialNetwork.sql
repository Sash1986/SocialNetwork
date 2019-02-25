/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - social_network
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`social_network` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `social_network`;

/*Table structure for table `friend_request` */

DROP TABLE IF EXISTS `friend_request`;

CREATE TABLE `friend_request` (
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `action` enum('ACCEPT','REJECT','SEND') DEFAULT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `friend_request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `friend_request_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `friend_request` */

insert  into `friend_request`(`user_id`,`friend_id`,`action`) values (8,9,'ACCEPT'),(8,11,'ACCEPT'),(9,8,'ACCEPT'),(9,10,'ACCEPT'),(9,11,'ACCEPT'),(9,12,'ACCEPT'),(10,9,'ACCEPT'),(10,12,'ACCEPT'),(12,9,'ACCEPT');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `created_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `friend_name` varchar(255) NOT NULL,
  `attachedFile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `friend_id` (`friend_id`),
  KEY `message_ibfk_1` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`text`,`created_date`,`user_id`,`user_name`,`friend_id`,`friend_name`,`attachedFile`) values (18,'Barev Sasha','2019-02-26 02:33:32',9,'Laura',8,'Sasha','1551134012964_emoticons-kissing-vector-7557237.jpg'),(19,'Inchpes es?','2019-02-26 02:33:49',9,'Laura',8,'Sasha',NULL),(20,'lav em, du inchpes es?','2019-02-26 02:36:02',8,'Sasha',9,'Laura','1551134162123_fb599c3e14d13d5d054690ee6eca223a.jpg'),(21,'shutov khandipenq!!!','2019-02-26 02:38:09',8,'Sasha',9,'Laura',NULL),(22,'Ani jan, shnorhavor','2019-02-26 02:43:51',9,'Laura',10,'Ani','1551134631353_95d5797927411eaae919be5cd6322522.jpg'),(23,'Shnorhakalutyun','2019-02-26 02:44:39',10,'Ani',9,'Laura','1551134679049_wink.png');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`email`,`password`,`avatar`) values (8,'Sasha','Grigoryan','sasha@mail.ru','sasha','1551132866251_1cf39c85bdb14517a53bfae7626f0a85.jpg'),(9,'Laura','Charkhifalakyan','laura@mail.ru','laura','1551133004180_32654177debaee66385f5eeb90a27ef1.jpg'),(10,'Ani','Ghalachyan','ani','ani','1551133043927_square-profile.jpg'),(11,'Admin','Administrator','admin','admin','1551133085216_4klm-1-e1525766338351.jpg'),(12,'Armen','Hambardzumyan','armen','armen','1551133131375_indx.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

CREATE DATABASE  IF NOT EXISTS `linkshortener`;
USE `linkshortener`;



DROP TABLE IF EXISTS `shortlink`;

CREATE TABLE `shortlink` (
  `id` int NOT NULL AUTO_INCREMENT,
  `actual_link`varchar(1000) DEFAULT NULL,
  `short_link` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


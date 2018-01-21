-- --------------------------------------------------------
-- Сервер:                       127.0.0.1
-- Версія сервера:               5.7.17-log - MySQL Community Server (GPL)
-- ОС сервера:                   Win64
-- HeidiSQL Версія:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for phraselist
CREATE DATABASE IF NOT EXISTS `phraselist` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `phraselist`;

-- Dumping structure for таблиця phraselist.items
CREATE TABLE IF NOT EXISTS `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `original_id` int(11) DEFAULT NULL,
  `translation_id` int(11) DEFAULT NULL,
  `comment` text,
  `date_of_creation` date DEFAULT NULL,
  `date_of_edition` date DEFAULT NULL,
  `original_language` int(11) DEFAULT NULL,
  `translation_language` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_items_user` (`user_id`),
  KEY `FK_items_original_words` (`original_id`),
  KEY `FK_items_translations` (`translation_id`),
  KEY `FK_items_original_languages` (`original_language`),
  KEY `FK_items_translated_languages` (`translation_language`),
  CONSTRAINT `FK_items_original_languages` FOREIGN KEY (`original_language`) REFERENCES `original_languages` (`id`),
  CONSTRAINT `FK_items_original_words` FOREIGN KEY (`original_id`) REFERENCES `original_words` (`id`),
  CONSTRAINT `FK_items_translated_languages` FOREIGN KEY (`translation_language`) REFERENCES `translated_languages` (`id`),
  CONSTRAINT `FK_items_translations` FOREIGN KEY (`translation_id`) REFERENCES `translations` (`id`),
  CONSTRAINT `FK_items_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
-- Dumping structure for таблиця phraselist.original_languages
CREATE TABLE IF NOT EXISTS `original_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
-- Dumping structure for таблиця phraselist.original_words
CREATE TABLE IF NOT EXISTS `original_words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
-- Dumping structure for таблиця phraselist.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
-- Dumping structure for таблиця phraselist.translated_languages
CREATE TABLE IF NOT EXISTS `translated_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tlanguage` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
-- Dumping structure for таблиця phraselist.translations
CREATE TABLE IF NOT EXISTS `translations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tword` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
-- Dumping structure for таблиця phraselist.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` text,
  `email` text,
  `name` text,
  `lastname` text,
  `pass` text,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role` (`role_id`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дані для експорту не вибрані
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

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

-- Dumping data for table phraselist.items: ~25 rows (приблизно)
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
REPLACE INTO `items` (`id`, `user_id`, `original_id`, `translation_id`, `comment`, `date_of_creation`, `date_of_edition`, `original_language`, `translation_language`) VALUES
	(1, 1, 1, 1, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(2, 1, 2, 2, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(3, 1, 3, 3, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(4, 1, 4, 4, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(5, 1, 5, 5, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(6, 1, 6, 6, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(7, 1, 7, 7, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(8, 1, 8, 8, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(9, 1, 9, 9, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(10, 1, 10, 10, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(11, 1, 11, 11, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(12, 1, 12, 12, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(13, 1, 13, 13, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(14, 1, 14, 14, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(15, 1, 15, 15, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(16, 1, 16, 16, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(17, 1, 17, 17, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(18, 1, 18, 18, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(19, 1, 19, 19, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(20, 1, 20, 20, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(21, 1, 21, 21, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(22, 1, 22, 22, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(41, 3, 1, 25, 'none', '2018-01-08', '2018-01-08', 1, 1),
	(46, 2, 31, 32, 'none', '2018-01-17', '2018-01-17', 1, 1);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;

-- Dumping structure for таблиця phraselist.original_languages
CREATE TABLE IF NOT EXISTS `original_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table phraselist.original_languages: ~0 rows (приблизно)
/*!40000 ALTER TABLE `original_languages` DISABLE KEYS */;
REPLACE INTO `original_languages` (`id`, `language`) VALUES
	(1, 'english');
/*!40000 ALTER TABLE `original_languages` ENABLE KEYS */;

-- Dumping structure for таблиця phraselist.original_words
CREATE TABLE IF NOT EXISTS `original_words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Dumping data for table phraselist.original_words: ~31 rows (приблизно)
/*!40000 ALTER TABLE `original_words` DISABLE KEYS */;
REPLACE INTO `original_words` (`id`, `word`) VALUES
	(1, 'Love'),
	(2, 'Cup'),
	(3, 'Book'),
	(4, 'You'),
	(5, 'Phone'),
	(6, 'Software'),
	(7, 'List'),
	(8, 'Development'),
	(9, 'Travel'),
	(10, 'Read'),
	(11, 'Network'),
	(12, 'Keyboard'),
	(13, 'Date'),
	(14, 'Search'),
	(15, 'Blog'),
	(16, 'Counter'),
	(17, 'Window'),
	(18, 'Door'),
	(19, 'Heart'),
	(20, 'Original'),
	(21, 'Nature'),
	(22, 'Trash'),
	(23, 'Useless'),
	(24, 'My'),
	(25, 'cat'),
	(26, 'Hint'),
	(27, 'Phrase'),
	(28, 'fish'),
	(29, 'name'),
	(30, 'table'),
	(31, 'world');
/*!40000 ALTER TABLE `original_words` ENABLE KEYS */;

-- Dumping structure for таблиця phraselist.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table phraselist.roles: ~2 rows (приблизно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
REPLACE INTO `roles` (`id`, `role`) VALUES
	(1, 'client'),
	(2, 'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for таблиця phraselist.translated_languages
CREATE TABLE IF NOT EXISTS `translated_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tlanguage` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table phraselist.translated_languages: ~0 rows (приблизно)
/*!40000 ALTER TABLE `translated_languages` DISABLE KEYS */;
REPLACE INTO `translated_languages` (`id`, `tlanguage`) VALUES
	(1, 'russian');
/*!40000 ALTER TABLE `translated_languages` ENABLE KEYS */;

-- Dumping structure for таблиця phraselist.translations
CREATE TABLE IF NOT EXISTS `translations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tword` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Dumping data for table phraselist.translations: ~32 rows (приблизно)
/*!40000 ALTER TABLE `translations` DISABLE KEYS */;
REPLACE INTO `translations` (`id`, `tword`) VALUES
	(1, 'Люблю'),
	(2, 'Чашка'),
	(3, 'Книга'),
	(4, 'Ты'),
	(5, 'Телефон'),
	(6, 'Программное'),
	(7, 'Список'),
	(8, 'Разработка'),
	(9, 'Путешествие'),
	(10, 'Читать'),
	(11, 'Сеть'),
	(12, 'Клавиатура'),
	(13, 'Дата'),
	(14, 'Поиск'),
	(15, 'Блог'),
	(16, 'Счетчик'),
	(17, 'Окно'),
	(18, 'Дверь'),
	(19, 'Сердце'),
	(20, 'Оригинал'),
	(21, 'Природа'),
	(22, 'Мусор'),
	(23, 'Бесполезный'),
	(24, 'Моя'),
	(25, 'Любовь'),
	(26, 'кот'),
	(27, 'Подсказка'),
	(28, 'Фраза'),
	(29, 'рыба'),
	(30, 'имя'),
	(31, 'стол'),
	(32, 'мир');
/*!40000 ALTER TABLE `translations` ENABLE KEYS */;

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

-- Dumping data for table phraselist.users: ~3 rows (приблизно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`id`, `login`, `email`, `name`, `lastname`, `pass`, `role_id`) VALUES
	(1, 'kolr', 'kolrlrs@gmail.com', 'Rodion', 'Kolomoiets', 'qwerty123456', 2),
	(2, 'vicoolya', 'vicoolyastr@gmail.com', 'Viktoriia', 'Obukhova', 'parislife123', 2),
	(3, 'test', 'test@test.com', 'test', 'test', 'qwerty123456', 2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

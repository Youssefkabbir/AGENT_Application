-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 29, 2022 at 05:07 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sma_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `table_professeur_etudiant`
--

DROP TABLE IF EXISTS `table_professeur_etudiant`;
CREATE TABLE IF NOT EXISTS `table_professeur_etudiant` (
  `id` int(25) NOT NULL AUTO_INCREMENT,
  `expediteur` varchar(40) NOT NULL,
  `destinataire` varchar(40) NOT NULL,
  `conntenu` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `table_professeur_etudiant`
--

INSERT INTO `table_professeur_etudiant` (`id`, `expediteur`, `destinataire`, `conntenu`, `date`, `time`) VALUES
(1, 'Youssef', 'Rim', 'salam ma niece cava', '2022-12-29', '2022-12-29 16:49:30'),
(2, 'omar', 'khadija', 'slm khadija hanya', '2022-12-29', '2022-12-29 18:03:56');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

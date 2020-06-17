-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 17 juin 2020 à 20:45
-- Version du serveur :  5.7.29-log
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bdd_ouicreches`
--

-- --------------------------------------------------------

--
-- Structure de la table `ouverture_semaine`
--

DROP TABLE IF EXISTS `ouverture_semaine`;
CREATE TABLE IF NOT EXISTS `ouverture_semaine` (
  `IdSiteCreche` int(11) DEFAULT NULL,
  `Id_jour` int(11) NOT NULL,
  `Etat_site` tinyint(1) NOT NULL,
  KEY `fk_siteCreche` (`IdSiteCreche`),
  KEY `fk_jour_id` (`Id_jour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ouverture_semaine`
--

INSERT INTO `ouverture_semaine` (`IdSiteCreche`, `Id_jour`, `Etat_site`) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
(1, 6, 1),
(1, 7, 0),
(2, 1, 1),
(2, 2, 1),
(2, 3, 1),
(2, 4, 1),
(2, 5, 1),
(2, 6, 0),
(2, 7, 0),
(3, 1, 1),
(3, 2, 1),
(3, 3, 1),
(3, 4, 1),
(3, 5, 1),
(3, 6, 0),
(3, 7, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ouverture_semaine`
--
ALTER TABLE `ouverture_semaine`
  ADD CONSTRAINT `fk_jour_id` FOREIGN KEY (`Id_jour`) REFERENCES `jours_semaines` (`Id_jour_semaine`),
  ADD CONSTRAINT `fk_siteCreche` FOREIGN KEY (`IdSiteCreche`) REFERENCES `site_creche` (`IdCrèche`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

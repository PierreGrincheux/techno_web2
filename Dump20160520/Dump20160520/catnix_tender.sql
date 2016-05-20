-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: catnix
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tender`
--

DROP TABLE IF EXISTS `tender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) NOT NULL,
  `activity_area` varchar(25) DEFAULT NULL,
  `contact_name` varchar(25) NOT NULL,
  `contact_phone` varchar(10) NOT NULL,
  `contact_email` varchar(40) DEFAULT NULL,
  `origin` varchar(25) DEFAULT NULL,
  `accepted` varchar(3) DEFAULT NULL,
  `name_cp` varchar(40) DEFAULT NULL,
  `assigned` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tender`
--

LOCK TABLES `tender` WRITE;
/*!40000 ALTER TABLE `tender` DISABLE KEYS */;
INSERT INTO `tender` VALUES (12,'Un titre','Un zone d\'acitivité','Nicolas Fritsch','0628400380','keopkepokeo@ss.fr','Une origine','yes',NULL,NULL),(16,'Un autre titre','Un zone d\'acitivité','Nicolas Fritsch','0628400380','keopkepokeo@ss.fr','Une origine','yes','Mathey Mélanie','yes');
/*!40000 ALTER TABLE `tender` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-20  8:54:06

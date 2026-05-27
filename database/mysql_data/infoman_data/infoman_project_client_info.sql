-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: infoman_project
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client_info`
--

DROP TABLE IF EXISTS `client_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_info` (
  `reference_code` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `birth_place` varchar(40) DEFAULT NULL,
  `marital_status` char(1) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `contact_number` char(11) DEFAULT NULL,
  `email_address` varchar(30) DEFAULT NULL,
  `religion` varchar(15) DEFAULT NULL,
  `ethnicity` varchar(15) DEFAULT NULL,
  `language_spoken` varchar(10) DEFAULT NULL,
  `osca_id_num` varchar(15) DEFAULT NULL,
  `gsis_sss_number` varchar(15) DEFAULT NULL,
  `tin_num` varchar(15) DEFAULT NULL,
  `philhealth_num` varchar(15) DEFAULT NULL,
  `sc_association_id` varchar(15) DEFAULT NULL,
  `other_gov_id` varchar(15) DEFAULT NULL,
  `travel_capability` char(1) DEFAULT NULL,
  `job` varchar(15) DEFAULT NULL,
  `current_pension` varchar(10) DEFAULT NULL,
  `highest_educational_attainment` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-27 15:57:17

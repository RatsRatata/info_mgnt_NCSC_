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
-- Table structure for table `client_hr_profile`
--

DROP TABLE IF EXISTS `client_hr_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_hr_profile` (
  `skill_id` int NOT NULL AUTO_INCREMENT,
  `reference_code` varchar(20) NOT NULL,
  `technical_skills` varchar(20) DEFAULT NULL,
  `community_service` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`skill_id`,`reference_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_hr_profile`
--

LOCK TABLES `client_hr_profile` WRITE;
/*!40000 ALTER TABLE `client_hr_profile` DISABLE KEYS */;
INSERT INTO `client_hr_profile` VALUES (1,'0-0-001-3618-XYZ','Teaching','Friendly Visits'),(1,'0-0-002-3642-XYZ','Evangelization','Friendly Visits'),(1,'0-0-003-8445-XYZ','Evangelization','Friendly Visits'),(1,'0-0-004-4595-XYZ','Sports Coach','Barangay Volunteer'),(1,'0-0-005-9672-XYZ','Sports Coach','Religious'),(1,'0-0-006-6187-XYZ','Cooking','Friendly Visits'),(1,'0-0-007-8203-XYZ','Carpentry','Religious'),(1,'0-0-008-7218-XYZ','Cooking','Religious'),(1,'0-0-009-8949-XYZ','Tailor','Barangay Volunteer'),(1,'0-0-010-2402-XYZ','Evangelization','Friendly Visits'),(1,'0-0-011-4759-XYZ','Sports Coach','Religious'),(1,'0-0-012-5743-XYZ','Tailor','Community / Organization Leader'),(1,'0-0-013-4072-XYZ','Sports Coach','Community / Organization Leader'),(1,'0-0-014-4479-XYZ','Teaching','Friendly Visits'),(1,'0-0-015-6150-XYZ','Teaching','Barangay Volunteer'),(1,'0-0-016-1351-XYZ','Cooking','Religious'),(1,'0-0-017-2510-XYZ','Cooking','Community / Organization Leader'),(1,'0-0-018-1333-XYZ','Cooking','Community / Organization Leader'),(1,'0-0-019-2020-XYZ','Teaching','Barangay Volunteer'),(1,'0-0-020-4750-XYZ','Cooking','Barangay Volunteer'),(1,'0-0-021-2693-XYZ','Carpentry','Community / Organization Leader'),(1,'0-0-022-4808-XYZ','Evangelization','Friendly Visits'),(1,'0-0-023-2542-XYZ','Evangelization','Religious'),(1,'0-0-024-8421-XYZ','Teaching','Religious'),(1,'0-0-025-4863-XYZ','Teaching','Community / Organization Leader'),(1,'0-0-026-5172-XYZ','Tailor','Community / Organization Leader'),(1,'0-0-027-7906-XYZ','Sports Coach','Religious'),(1,'0-0-028-2817-XYZ','Evangelization','Friendly Visits'),(1,'0-0-029-2655-XYZ','Sports Coach','Religious'),(1,'0-0-030-6783-XYZ','Tailor','Community / Organization Leader'),(1,'0-0-031-5884-XYZ','Sports Coach','Religious'),(1,'0-0-032-3073-XYZ','Evangelization','Friendly Visits'),(1,'0-0-033-4460-XYZ','Teaching','Friendly Visits'),(1,'0-0-034-6505-XYZ','Teaching','Religious'),(1,'0-0-035-4894-XYZ','Evangelization','Barangay Volunteer'),(1,'0-0-036-3883-XYZ','Cooking','Religious'),(1,'0-0-037-3192-XYZ','Evangelization','Barangay Volunteer'),(1,'0-0-038-2850-XYZ','Sports Coach','Community / Organization Leader'),(1,'0-0-039-8194-XYZ','Teaching','Barangay Volunteer'),(1,'0-0-040-8682-XYZ','Tailor','Barangay Volunteer'),(1,'0-0-041-4568-XYZ','Tailor','Religious'),(1,'0-0-042-9124-XYZ','Tailor','Religious'),(1,'0-0-043-4364-XYZ','Cooking','Friendly Visits'),(1,'0-0-044-5008-XYZ','Sports Coach','Community / Organization Leader'),(1,'0-0-045-3579-XYZ','Tailor','Religious'),(1,'0-0-046-2035-XYZ','Teaching','Community / Organization Leader'),(1,'0-0-047-3810-XYZ','Carpentry','Community / Organization Leader'),(1,'0-0-048-4084-XYZ','Evangelization','Friendly Visits'),(1,'0-0-049-2813-XYZ','Cooking','Friendly Visits'),(1,'0-0-050-8147-XYZ','Teaching','Religious'),(1,'0-0-051-1879-XYZ','Evangelization','Friendly Visits'),(1,'0-0-052-7473-XYZ','Cooking','Religious'),(1,'0-0-053-8616-XYZ','Cooking','Friendly Visits'),(1,'0-0-054-4462-XYZ','Sports Coach','Religious'),(1,'0-0-055-7511-XYZ','Sports Coach','Friendly Visits'),(1,'0-0-056-6049-XYZ','Teaching','Community / Organization Leader'),(1,'0-0-057-5060-XYZ','Evangelization','Barangay Volunteer'),(1,'0-0-058-2230-XYZ','Evangelization','Religious'),(1,'0-0-059-7457-XYZ','Tailor','Community / Organization Leader'),(1,'0-0-060-5145-XYZ','Tailor','Religious'),(1,'0-0-061-8668-XYZ','Cooking','Religious'),(1,'0-0-062-6530-XYZ','Teaching','Barangay Volunteer'),(1,'0-0-063-1161-XYZ','Sports Coach','Community / Organization Leader'),(1,'0-0-064-8848-XYZ','Teaching','Community / Organization Leader'),(1,'0-0-065-8074-XYZ','Tailor','Barangay Volunteer'),(1,'0-0-066-6616-XYZ','Evangelization','Friendly Visits'),(1,'0-0-067-7908-XYZ','Tailor','Friendly Visits'),(1,'0-0-068-9866-XYZ','Teaching','Barangay Volunteer'),(1,'0-0-069-8337-XYZ','Tailor','Religious'),(1,'0-0-070-3953-XYZ','Tailor','Friendly Visits'),(1,'0-0-071-7166-XYZ','Sports Coach','Community / Organization Leader'),(1,'0-0-072-7212-XYZ','Sports Coach','Religious'),(1,'0-0-073-6085-XYZ','Carpentry','Community / Organization Leader'),(1,'0-0-074-6335-XYZ','Carpentry','Community / Organization Leader'),(1,'0-0-075-4786-XYZ','Tailor','Community / Organization Leader'),(1,'0-0-076-4132-XYZ','Sports Coach','Barangay Volunteer'),(1,'0-0-077-2158-XYZ','Teaching','Friendly Visits'),(1,'0-0-078-6590-XYZ','Cooking','Friendly Visits'),(1,'0-0-079-7508-XYZ','Teaching','Barangay Volunteer'),(1,'0-0-080-8156-XYZ','Carpentry','Barangay Volunteer'),(1,'0-0-081-9722-XYZ','Sports Coach','Barangay Volunteer'),(1,'0-0-082-4889-XYZ','Cooking','Barangay Volunteer'),(1,'0-0-083-7370-XYZ','Cooking','Friendly Visits'),(1,'0-0-084-2128-XYZ','Tailor','Religious'),(1,'0-0-085-1744-XYZ','Cooking','Religious'),(1,'0-0-086-3885-XYZ','Carpentry','Community / Organization Leader'),(1,'0-0-087-2595-XYZ','Teaching','Community / Organization Leader'),(1,'0-0-088-9663-XYZ','Carpentry','Friendly Visits'),(1,'0-0-089-5651-XYZ','Sports Coach','Community / Organization Leader'),(1,'0-0-090-4879-XYZ','Carpentry','Community / Organization Leader'),(1,'0-0-091-8150-XYZ','Teaching','Religious'),(1,'0-0-092-4429-XYZ','Sports Coach','Friendly Visits'),(1,'0-0-093-8360-XYZ','Evangelization','Friendly Visits'),(1,'0-0-094-7770-XYZ','Sports Coach','Religious'),(1,'0-0-095-2385-XYZ','Cooking','Religious'),(1,'0-0-096-1055-XYZ','Cooking','Friendly Visits'),(1,'0-0-097-3925-XYZ','Cooking','Friendly Visits'),(1,'0-0-098-2002-XYZ','Cooking','Barangay Volunteer'),(1,'0-0-099-2420-XYZ','Cooking','Religious'),(1,'0-0-100-1996-XYZ','Teaching','Community / Organization Leader');
/*!40000 ALTER TABLE `client_hr_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-01 12:23:39

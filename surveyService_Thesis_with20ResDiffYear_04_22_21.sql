-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: surveyService
-- ------------------------------------------------------
-- Server version	8.0.24

CREATE DATABASE  IF NOT EXISTS `surveyService`;
USE `surveyService`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer` (
  `answer_type` varchar(31) NOT NULL,
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `answer_section_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  `attachment_id` bigint DEFAULT NULL,
  `answer_index` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jmd2cwyk9f0l6v6hhh28ahha` (`answer_section_id`),
  KEY `FK8frr4bcabmmeyyu60qt7iiblo` (`question_id`),
  KEY `FK8y73nf73k9d808k0c7wn3glm0` (`attachment_id`),
  CONSTRAINT `FK1jmd2cwyk9f0l6v6hhh28ahha` FOREIGN KEY (`answer_section_id`) REFERENCES `answer_section` (`id`),
  CONSTRAINT `FK8frr4bcabmmeyyu60qt7iiblo` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK8y73nf73k9d808k0c7wn3glm0` FOREIGN KEY (`attachment_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES ('RATING',18,'<p>12312</p>\n',3,NULL,17,15,NULL,0),('RATING',21,'<p>12312</p>\n',4,NULL,20,15,NULL,0),('MULTIPLE_CHOICE',24,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,23,3,NULL,0),('RATING',25,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',4,NULL,23,4,NULL,1),('RATING',26,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',3,NULL,23,5,NULL,2),('RATING',27,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,23,6,NULL,3),('RATING',28,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,23,7,NULL,4),('RATING',29,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',2,NULL,23,8,NULL,5),('RATING',30,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',2,NULL,23,9,NULL,6),('RANKING',31,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,23,11,NULL,7),('MULTIPLE_CHOICE',32,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,23,12,NULL,8),('TEXT',33,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',23,10,NULL,9),('MULTIPLE_CHOICE',36,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,35,3,NULL,0),('RATING',37,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',4,NULL,35,4,NULL,1),('RATING',38,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',2,NULL,35,5,NULL,2),('RATING',39,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,35,6,NULL,3),('RATING',40,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',4,NULL,35,7,NULL,4),('RATING',41,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',4,NULL,35,8,NULL,5),('RATING',42,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',3,NULL,35,9,NULL,6),('RANKING',43,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,35,11,NULL,7),('MULTIPLE_CHOICE',44,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,35,12,NULL,8),('TEXT',45,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',35,10,NULL,9),('MULTIPLE_CHOICE',48,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,47,3,NULL,0),('RATING',49,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',4,NULL,47,4,NULL,1),('RATING',50,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',2,NULL,47,5,NULL,2),('RATING',51,'<p>Communicate effectively in a variety of professional contexts.</p>\n',3,NULL,47,6,NULL,3),('RATING',52,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',2,NULL,47,7,NULL,4),('RATING',53,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,47,8,NULL,5),('RATING',54,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',2,NULL,47,9,NULL,6),('RANKING',55,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,47,11,NULL,7),('MULTIPLE_CHOICE',56,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,47,12,NULL,8),('TEXT',57,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'nah',47,10,NULL,9),('MULTIPLE_CHOICE',60,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,59,3,NULL,0),('RATING',61,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',5,NULL,59,4,NULL,1),('RATING',62,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',3,NULL,59,5,NULL,2),('RATING',63,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,59,6,NULL,3),('RATING',64,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',5,NULL,59,7,NULL,4),('RATING',65,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,59,8,NULL,5),('RATING',66,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',3,NULL,59,9,NULL,6),('RANKING',67,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,59,11,NULL,7),('MULTIPLE_CHOICE',68,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,59,12,NULL,8),('TEXT',69,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'just opinion',59,10,NULL,9),('MULTIPLE_CHOICE',72,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,71,3,NULL,0),('RATING',73,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',4,NULL,71,4,NULL,1),('RATING',74,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',3,NULL,71,5,NULL,2),('RATING',75,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,71,6,NULL,3),('RATING',76,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',5,NULL,71,7,NULL,4),('RATING',77,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',3,NULL,71,8,NULL,5),('RATING',78,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',3,NULL,71,9,NULL,6),('RANKING',79,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,71,11,NULL,7),('MULTIPLE_CHOICE',80,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,71,12,NULL,8),('TEXT',81,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',71,10,NULL,9),('MULTIPLE_CHOICE',84,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,83,3,NULL,0),('RATING',85,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',4,NULL,83,4,NULL,1),('RATING',86,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',3,NULL,83,5,NULL,2),('RATING',87,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,83,6,NULL,3),('RATING',88,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',5,NULL,83,7,NULL,4),('RATING',89,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',3,NULL,83,8,NULL,5),('RATING',90,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',3,NULL,83,9,NULL,6),('RANKING',91,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,83,11,NULL,7),('MULTIPLE_CHOICE',92,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,83,12,NULL,8),('TEXT',93,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',83,10,NULL,9),('MULTIPLE_CHOICE',96,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,95,3,NULL,0),('RATING',97,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',5,NULL,95,4,NULL,1),('RATING',98,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',3,NULL,95,5,NULL,2),('RATING',99,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,95,6,NULL,3),('RATING',100,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',4,NULL,95,7,NULL,4),('RATING',101,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',2,NULL,95,8,NULL,5),('RATING',102,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,95,9,NULL,6),('RANKING',103,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,95,11,NULL,7),('MULTIPLE_CHOICE',104,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,95,12,NULL,8),('TEXT',105,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',95,10,NULL,9),('MULTIPLE_CHOICE',108,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,107,3,NULL,0),('RATING',109,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',3,NULL,107,4,NULL,1),('RATING',110,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',1,NULL,107,5,NULL,2),('RATING',111,'<p>Communicate effectively in a variety of professional contexts.</p>\n',5,NULL,107,6,NULL,3),('RATING',112,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',5,NULL,107,7,NULL,4),('RATING',113,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',3,NULL,107,8,NULL,5),('RATING',114,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',1,NULL,107,9,NULL,6),('RANKING',115,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,107,11,NULL,7),('MULTIPLE_CHOICE',116,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,107,12,NULL,8),('TEXT',117,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'aaaa',107,10,NULL,9),('MULTIPLE_CHOICE',120,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,119,3,NULL,0),('RATING',121,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,119,4,NULL,1),('RATING',122,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,119,5,NULL,2),('RATING',123,'<p>Communicate effectively in a variety of professional contexts.</p>\n',4,NULL,119,6,NULL,3),('RATING',124,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,119,7,NULL,4),('RATING',125,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',2,NULL,119,8,NULL,5),('RATING',126,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,119,9,NULL,6),('RANKING',127,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,119,11,NULL,7),('MULTIPLE_CHOICE',128,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,119,12,NULL,8),('TEXT',129,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',119,10,NULL,9),('MULTIPLE_CHOICE',132,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,131,3,NULL,0),('RATING',133,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,131,4,NULL,1),('RATING',134,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,131,5,NULL,2),('RATING',135,'<p>Communicate effectively in a variety of professional contexts.</p>\n',2,NULL,131,6,NULL,3),('RATING',136,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',4,NULL,131,7,NULL,4),('RATING',137,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',3,NULL,131,8,NULL,5),('RATING',138,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',2,NULL,131,9,NULL,6),('RANKING',139,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,131,11,NULL,7),('MULTIPLE_CHOICE',140,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,131,12,NULL,8),('TEXT',141,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',131,10,NULL,9),('MULTIPLE_CHOICE',144,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,143,3,NULL,0),('RATING',145,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,143,4,NULL,1),('RATING',146,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,143,5,NULL,2),('RATING',147,'<p>Communicate effectively in a variety of professional contexts.</p>\n',3,NULL,143,6,NULL,3),('RATING',148,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',2,NULL,143,7,NULL,4),('RATING',149,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,143,8,NULL,5),('RATING',150,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',3,NULL,143,9,NULL,6),('RANKING',151,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,143,11,NULL,7),('MULTIPLE_CHOICE',152,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,143,12,NULL,8),('TEXT',153,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',143,10,NULL,9),('MULTIPLE_CHOICE',156,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,155,3,NULL,0),('RATING',157,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',1,NULL,155,4,NULL,1),('RATING',158,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,155,5,NULL,2),('RATING',159,'<p>Communicate effectively in a variety of professional contexts.</p>\n',3,NULL,155,6,NULL,3),('RATING',160,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',2,NULL,155,7,NULL,4),('RATING',161,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,155,8,NULL,5),('RATING',162,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,155,9,NULL,6),('RANKING',163,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,155,11,NULL,7),('MULTIPLE_CHOICE',164,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,155,12,NULL,8),('TEXT',165,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'bbbbb',155,10,NULL,9),('MULTIPLE_CHOICE',168,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,167,3,NULL,0),('RATING',169,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',3,NULL,167,4,NULL,1),('RATING',170,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',2,NULL,167,5,NULL,2),('RATING',171,'<p>Communicate effectively in a variety of professional contexts.</p>\n',5,NULL,167,6,NULL,3),('RATING',172,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,167,7,NULL,4),('RATING',173,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',4,NULL,167,8,NULL,5),('RATING',174,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,167,9,NULL,6),('RANKING',175,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,167,11,NULL,7),('MULTIPLE_CHOICE',176,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,167,12,NULL,8),('TEXT',177,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',167,10,NULL,9),('MULTIPLE_CHOICE',180,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,179,3,NULL,0),('RATING',181,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,179,4,NULL,1),('RATING',182,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,179,5,NULL,2),('RATING',183,'<p>Communicate effectively in a variety of professional contexts.</p>\n',3,NULL,179,6,NULL,3),('RATING',184,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,179,7,NULL,4),('RATING',185,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,179,8,NULL,5),('RATING',186,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',3,NULL,179,9,NULL,6),('RANKING',187,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,179,11,NULL,7),('MULTIPLE_CHOICE',188,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,179,12,NULL,8),('TEXT',189,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',179,10,NULL,9),('MULTIPLE_CHOICE',192,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,191,3,NULL,0),('RATING',193,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',3,NULL,191,4,NULL,1),('RATING',194,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',2,NULL,191,5,NULL,2),('RATING',195,'<p>Communicate effectively in a variety of professional contexts.</p>\n',5,NULL,191,6,NULL,3),('RATING',196,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',4,NULL,191,7,NULL,4),('RATING',197,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,191,8,NULL,5),('RATING',198,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',4,NULL,191,9,NULL,6),('RANKING',199,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,191,11,NULL,7),('MULTIPLE_CHOICE',200,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,191,12,NULL,8),('TEXT',201,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',191,10,NULL,9),('MULTIPLE_CHOICE',204,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,203,3,NULL,0),('RATING',205,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,203,4,NULL,1),('RATING',206,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,203,5,NULL,2),('RATING',207,'<p>Communicate effectively in a variety of professional contexts.</p>\n',4,NULL,203,6,NULL,3),('RATING',208,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,203,7,NULL,4),('RATING',209,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',5,NULL,203,8,NULL,5),('RATING',210,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',4,NULL,203,9,NULL,6),('RANKING',211,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,203,11,NULL,7),('MULTIPLE_CHOICE',212,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,203,12,NULL,8),('TEXT',213,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'hhhhh',203,10,NULL,9),('MULTIPLE_CHOICE',216,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,215,3,NULL,0),('RATING',217,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,215,4,NULL,1),('RATING',218,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',5,NULL,215,5,NULL,2),('RATING',219,'<p>Communicate effectively in a variety of professional contexts.</p>\n',4,NULL,215,6,NULL,3),('RATING',220,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,215,7,NULL,4),('RATING',221,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',2,NULL,215,8,NULL,5),('RATING',222,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,215,9,NULL,6),('RANKING',223,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,215,11,NULL,7),('MULTIPLE_CHOICE',224,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,215,12,NULL,8),('TEXT',225,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',215,10,NULL,9),('MULTIPLE_CHOICE',228,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,227,3,NULL,0),('RATING',229,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',3,NULL,227,4,NULL,1),('RATING',230,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',2,NULL,227,5,NULL,2),('RATING',231,'<p>Communicate effectively in a variety of professional contexts.</p>\n',5,NULL,227,6,NULL,3),('RATING',232,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,227,7,NULL,4),('RATING',233,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',2,NULL,227,8,NULL,5),('RATING',234,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,227,9,NULL,6),('RANKING',235,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,227,11,NULL,7),('MULTIPLE_CHOICE',236,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,227,12,NULL,8),('TEXT',237,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'ttttt',227,10,NULL,9),('MULTIPLE_CHOICE',240,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,239,3,NULL,0),('RATING',241,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',2,NULL,239,4,NULL,1),('RATING',242,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',4,NULL,239,5,NULL,2),('RATING',243,'<p>Communicate effectively in a variety of professional contexts.</p>\n',5,NULL,239,6,NULL,3),('RATING',244,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',3,NULL,239,7,NULL,4),('RATING',245,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',2,NULL,239,8,NULL,5),('RATING',246,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',5,NULL,239,9,NULL,6),('RANKING',247,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,239,11,NULL,7),('MULTIPLE_CHOICE',248,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,239,12,NULL,8),('TEXT',249,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',239,10,NULL,9),('MULTIPLE_CHOICE',252,'<p>Are you Faculty, Student, or Alumni?</p>\n',NULL,NULL,251,3,NULL,0),('RATING',253,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',3,NULL,251,4,NULL,1),('RATING',254,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',2,NULL,251,5,NULL,2),('RATING',255,'<p>Communicate effectively in a variety of professional contexts.</p>\n',5,NULL,251,6,NULL,3),('RATING',256,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',5,NULL,251,7,NULL,4),('RATING',257,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',4,NULL,251,8,NULL,5),('RATING',258,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',4,NULL,251,9,NULL,6),('RANKING',259,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',NULL,NULL,251,11,NULL,7),('MULTIPLE_CHOICE',260,'<p>What of the following suggestions do you like?</p>\n',NULL,NULL,251,12,NULL,8),('TEXT',261,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',NULL,'',251,10,NULL,9);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_ranking_selection`
--

DROP TABLE IF EXISTS `answer_ranking_selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer_ranking_selection` (
  `answer_id` bigint NOT NULL,
  `selection_index_rank` int DEFAULT NULL,
  `ranking` int NOT NULL,
  PRIMARY KEY (`answer_id`,`ranking`),
  CONSTRAINT `FKne0x4nlgf1p5g64prjj2x5x22` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_ranking_selection`
--

LOCK TABLES `answer_ranking_selection` WRITE;
/*!40000 ALTER TABLE `answer_ranking_selection` DISABLE KEYS */;
INSERT INTO `answer_ranking_selection` VALUES (31,0,1),(31,1,2),(31,2,3),(31,3,4),(31,4,5),(43,2,1),(43,1,2),(43,0,3),(43,3,4),(43,4,5),(55,3,1),(55,4,2),(55,1,3),(55,0,4),(55,2,5),(67,3,1),(67,4,2),(67,1,3),(67,2,4),(67,0,5),(79,2,1),(79,1,2),(79,0,3),(79,3,4),(79,4,5),(91,2,1),(91,1,2),(91,0,3),(91,3,4),(91,4,5),(103,3,1),(103,4,2),(103,0,3),(103,1,4),(103,2,5),(115,1,1),(115,4,2),(115,0,3),(115,3,4),(115,2,5),(127,3,1),(127,0,2),(127,2,3),(127,4,4),(127,1,5),(139,2,1),(139,1,2),(139,0,3),(139,3,4),(139,4,5),(151,2,1),(151,3,2),(151,0,3),(151,1,4),(151,4,5),(163,2,1),(163,0,2),(163,3,3),(163,4,4),(163,1,5),(175,3,1),(175,4,2),(175,0,3),(175,1,4),(175,2,5),(187,4,1),(187,0,2),(187,1,3),(187,2,4),(187,3,5),(199,0,1),(199,4,2),(199,1,3),(199,2,4),(199,3,5),(211,3,1),(211,0,2),(211,1,3),(211,2,4),(211,4,5),(223,0,1),(223,2,2),(223,3,3),(223,4,4),(223,1,5),(235,3,1),(235,0,2),(235,4,3),(235,1,4),(235,2,5),(247,3,1),(247,2,2),(247,0,3),(247,4,4),(247,1,5),(259,4,1),(259,1,2),(259,0,3),(259,2,4),(259,3,5);
/*!40000 ALTER TABLE `answer_ranking_selection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_section`
--

DROP TABLE IF EXISTS `answer_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer_section` (
  `id` bigint NOT NULL,
  `description` longtext,
  `response_id` bigint DEFAULT NULL,
  `response_section_index` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb5nvaw1fvmk0nvmmecx2kaq0t` (`response_id`),
  CONSTRAINT `FKb5nvaw1fvmk0nvmmecx2kaq0t` FOREIGN KEY (`response_id`) REFERENCES `survey_responses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_section`
--

LOCK TABLES `answer_section` WRITE;
/*!40000 ALTER TABLE `answer_section` DISABLE KEYS */;
INSERT INTO `answer_section` VALUES (17,'',16,0),(20,'',19,0),(23,'',22,0),(35,'',34,0),(47,'',46,0),(59,'',58,0),(71,'',70,0),(83,'',82,0),(95,'',94,0),(107,'',106,0),(119,'',118,0),(131,'',130,0),(143,'',142,0),(155,'',154,0),(167,'',166,0),(179,'',178,0),(191,'',190,0),(203,'',202,0),(215,'',214,0),(227,'',226,0),(239,'',238,0),(251,'',250,0);
/*!40000 ALTER TABLE `answer_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_selection`
--

DROP TABLE IF EXISTS `answer_selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer_selection` (
  `answer_id` bigint NOT NULL,
  `selection` int DEFAULT NULL,
  KEY `FK2m108eua3qjm6eptjf2qoexgv` (`answer_id`),
  CONSTRAINT `FK2m108eua3qjm6eptjf2qoexgv` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_selection`
--

LOCK TABLES `answer_selection` WRITE;
/*!40000 ALTER TABLE `answer_selection` DISABLE KEYS */;
INSERT INTO `answer_selection` VALUES (24,0),(32,0),(32,1),(36,0),(44,0),(44,2),(48,1),(56,1),(60,2),(68,0),(68,1),(68,2),(68,3),(72,0),(80,0),(80,2),(84,0),(92,0),(92,2),(96,0),(104,0),(104,1),(104,2),(108,0),(116,1),(116,2),(120,0),(128,0),(128,1),(128,3),(132,1),(140,0),(140,1),(140,2),(140,3),(144,2),(152,1),(152,2),(156,0),(164,0),(164,3),(168,2),(176,0),(176,1),(180,2),(188,2),(188,3),(192,1),(200,0),(200,1),(204,2),(212,1),(212,2),(216,1),(224,0),(224,1),(224,2),(228,2),(236,1),(236,2),(240,1),(248,0),(248,1),(252,0),(260,1),(260,3);
/*!40000 ALTER TABLE `answer_selection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `file` (
  `id` bigint NOT NULL,
  `date` datetime(6) NOT NULL,
  `file_data` longblob,
  `name` varchar(255) NOT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (340),(340),(340),(340),(340),(340),(340),(340),(340),(340),(340),(340),(340);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question` (
  `question_type` varchar(31) NOT NULL,
  `id` bigint NOT NULL,
  `description` text NOT NULL,
  `max_selections` int NOT NULL DEFAULT '0',
  `min_selections` int NOT NULL DEFAULT '0',
  `rating_scale` int NOT NULL DEFAULT '5',
  `attachment_allowed` tinyint(1) NOT NULL DEFAULT '0',
  `text_length` int DEFAULT NULL,
  `question_section_id` bigint DEFAULT NULL,
  `question_index` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaqddxaempr1yfudlve9rcsokg` (`question_section_id`),
  CONSTRAINT `FKaqddxaempr1yfudlve9rcsokg` FOREIGN KEY (`question_section_id`) REFERENCES `question_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('MULTIPLE_CHOICE',3,'<p>Are you Faculty, Student, or Alumni?</p>\n',1,1,5,0,NULL,2,0),('RATING',4,'<p>Analyze a complex computing problem and to apply principles of computing and other relevant disciplines to identify solutions.</p>\n',0,0,5,0,NULL,2,1),('RATING',5,'<p>Design, implement, and evaluate a computing-based solution to meet a given set of computing requirements in the context of the program’s discipline.</p>\n',0,0,5,0,NULL,2,2),('RATING',6,'<p>Communicate effectively in a variety of professional contexts.</p>\n',0,0,5,0,NULL,2,3),('RATING',7,'<p>Recognize professional responsibilities and make informed judgments in computing practice based on legal and ethical principles.</p>\n',0,0,5,0,NULL,2,4),('RATING',8,'<p>Function effectively as a member or leader of a team engaged in activities appropriate to the program’s discipline.</p>\n',0,0,5,0,NULL,2,5),('RATING',9,'<p>Apply computer science theory and software development fundamentals to produce computing-based solutions.</p>\n',0,0,5,0,NULL,2,6),('TEXT',10,'<p>(Optional) Please elaborate on the reasons behind your ratings, and leave any additional comments you want to share with us:</p>\n',0,0,5,0,-1,2,9),('RANKING',11,'<p>Ranking the following shows in order of preference - 1 being your favorite and 5 being your least favorite</p>\n',0,0,5,0,NULL,2,7),('MULTIPLE_CHOICE',12,'<p>What of the following suggestions do you like?</p>\n',4,1,5,0,NULL,2,8),('RATING',15,'<p>12312</p>\n',0,0,5,0,NULL,14,0);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_attachments`
--

DROP TABLE IF EXISTS `question_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question_attachments` (
  `question_id` bigint NOT NULL,
  `attachments_id` bigint NOT NULL,
  UNIQUE KEY `UK_8obl5sftq913x2rqv2nf0rrlh` (`attachments_id`),
  KEY `FKmy3lhpgw8k3rxk6wntxsqe6wi` (`question_id`),
  CONSTRAINT `FKmy3lhpgw8k3rxk6wntxsqe6wi` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FKro9jrkkotepd8lc8knrjbt6h7` FOREIGN KEY (`attachments_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_attachments`
--

LOCK TABLES `question_attachments` WRITE;
/*!40000 ALTER TABLE `question_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_choices`
--

DROP TABLE IF EXISTS `question_choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question_choices` (
  `question_id` bigint NOT NULL,
  `choice` varchar(3000) DEFAULT NULL,
  `choice_index` int NOT NULL,
  PRIMARY KEY (`question_id`,`choice_index`),
  CONSTRAINT `FKifc0cyjdk3ijjhtju0fual7a6` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_choices`
--

LOCK TABLES `question_choices` WRITE;
/*!40000 ALTER TABLE `question_choices` DISABLE KEYS */;
INSERT INTO `question_choices` VALUES (3,'Faculty',0),(3,'Student',1),(3,'Alumni',2),(12,'More live demos in class',0),(12,'Homework with longer deadline',1),(12,'Slides come with more examples',2),(12,'More office hours',3);
/*!40000 ALTER TABLE `question_choices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_ranking_choices`
--

DROP TABLE IF EXISTS `question_ranking_choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question_ranking_choices` (
  `question_id` bigint NOT NULL,
  `ranking_choice` varchar(3000) DEFAULT NULL,
  `ranking_choice_index` int NOT NULL,
  PRIMARY KEY (`question_id`,`ranking_choice_index`),
  CONSTRAINT `FK1fpjmc9vql074od32k6bwgnsu` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_ranking_choices`
--

LOCK TABLES `question_ranking_choices` WRITE;
/*!40000 ALTER TABLE `question_ranking_choices` DISABLE KEYS */;
INSERT INTO `question_ranking_choices` VALUES (11,'CS 3220',0),(11,'CS 1220',1),(11,'CS 3220',2),(11,'CS 5112',3),(11,'CS 5990',4);
/*!40000 ALTER TABLE `question_ranking_choices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_result_summary`
--

DROP TABLE IF EXISTS `question_result_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question_result_summary` (
  `question_type` varchar(31) NOT NULL,
  `id` bigint NOT NULL,
  `total_responses` int NOT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n55rtqdtoi3x708c1ox5fjnl2` (`question_id`),
  CONSTRAINT `FK4r875vxemuq2ig6s1fq5cvvel` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_result_summary`
--

LOCK TABLES `question_result_summary` WRITE;
/*!40000 ALTER TABLE `question_result_summary` DISABLE KEYS */;
INSERT INTO `question_result_summary` VALUES ('MULTIPLE_CHOICE',262,20,3),('RATING',266,20,4),('RATING',272,20,5),('RATING',278,20,6),('RATING',284,20,7),('RATING',290,20,8),('RATING',296,20,9),('RANKING',302,20,11),('MULTIPLE_CHOICE',328,20,12),('TEXT',333,20,10);
/*!40000 ALTER TABLE `question_result_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_section`
--

DROP TABLE IF EXISTS `question_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question_section` (
  `id` bigint NOT NULL,
  `description` longtext,
  `survey_id` bigint DEFAULT NULL,
  `section_index` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ow64hpsjrr1k9gv9p0j0lrka` (`survey_id`),
  CONSTRAINT `FK5ow64hpsjrr1k9gv9p0j0lrka` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_section`
--

LOCK TABLES `question_section` WRITE;
/*!40000 ALTER TABLE `question_section` DISABLE KEYS */;
INSERT INTO `question_section` VALUES (2,'',1,0),(14,'',13,0);
/*!40000 ALTER TABLE `question_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking_responses`
--

DROP TABLE IF EXISTS `ranking_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ranking_responses` (
  `id` bigint NOT NULL,
  `response_group_id` bigint NOT NULL,
  `rankingn_responses_key` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`rankingn_responses_key`),
  KEY `FK9n724nn8cdolkr6n8706awfsg` (`response_group_id`),
  CONSTRAINT `FK30fdv5fe9odfcvlt8a5tjwxls` FOREIGN KEY (`id`) REFERENCES `question_result_summary` (`id`),
  CONSTRAINT `FK9n724nn8cdolkr6n8706awfsg` FOREIGN KEY (`response_group_id`) REFERENCES `response_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking_responses`
--

LOCK TABLES `ranking_responses` WRITE;
/*!40000 ALTER TABLE `ranking_responses` DISABLE KEYS */;
INSERT INTO `ranking_responses` VALUES (302,303,'1:0'),(302,304,'1:1'),(302,305,'2:0'),(302,306,'1:2'),(302,307,'2:1'),(302,308,'3:0'),(302,309,'1:3'),(302,310,'2:2'),(302,311,'3:1'),(302,312,'4:0'),(302,313,'1:4'),(302,314,'2:3'),(302,315,'3:2'),(302,316,'4:1'),(302,317,'5:0'),(302,318,'2:4'),(302,319,'3:3'),(302,320,'4:2'),(302,321,'5:1'),(302,322,'3:4'),(302,323,'4:3'),(302,324,'5:2'),(302,325,'4:4'),(302,326,'5:3'),(302,327,'5:4');
/*!40000 ALTER TABLE `ranking_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating_responses`
--

DROP TABLE IF EXISTS `rating_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rating_responses` (
  `id` bigint NOT NULL,
  `response_group_id` bigint NOT NULL,
  `rating_responses_key` int NOT NULL,
  PRIMARY KEY (`id`,`rating_responses_key`),
  UNIQUE KEY `UK_8x8p7u56kvmc7m4s9kthaplyp` (`response_group_id`),
  CONSTRAINT `FKc02adaksmxo6fj7loqmb9tfw3` FOREIGN KEY (`response_group_id`) REFERENCES `response_group` (`id`),
  CONSTRAINT `FKmanpld14q9o2fft52w61935pt` FOREIGN KEY (`id`) REFERENCES `question_result_summary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating_responses`
--

LOCK TABLES `rating_responses` WRITE;
/*!40000 ALTER TABLE `rating_responses` DISABLE KEYS */;
INSERT INTO `rating_responses` VALUES (266,267,1),(266,268,2),(266,269,3),(266,270,4),(266,271,5),(272,273,1),(272,274,2),(272,275,3),(272,276,4),(272,277,5),(278,279,1),(278,280,2),(278,281,3),(278,282,4),(278,283,5),(284,285,1),(284,286,2),(284,287,3),(284,288,4),(284,289,5),(290,291,1),(290,292,2),(290,293,3),(290,294,4),(290,295,5),(296,297,1),(296,298,2),(296,299,3),(296,300,4),(296,301,5);
/*!40000 ALTER TABLE `rating_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response_group`
--

DROP TABLE IF EXISTS `response_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `response_group` (
  `id` bigint NOT NULL,
  `group_by` varchar(255) DEFAULT NULL,
  `group_type` int DEFAULT NULL,
  `grouped_value` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response_group`
--

LOCK TABLES `response_group` WRITE;
/*!40000 ALTER TABLE `response_group` DISABLE KEYS */;
INSERT INTO `response_group` VALUES (263,'mulChoiceSelection',NULL,'Faculty','',NULL),(264,'mulChoiceSelection',NULL,'Student','',NULL),(265,'mulChoiceSelection',NULL,'Alumni','',NULL),(267,'ratingOption',NULL,'1','',NULL),(268,'ratingOption',NULL,'2','',NULL),(269,'ratingOption',NULL,'3','',NULL),(270,'ratingOption',NULL,'4','',NULL),(271,'ratingOption',NULL,'5','',NULL),(273,'ratingOption',NULL,'1','',NULL),(274,'ratingOption',NULL,'2','',NULL),(275,'ratingOption',NULL,'3','',NULL),(276,'ratingOption',NULL,'4','',NULL),(277,'ratingOption',NULL,'5','',NULL),(279,'ratingOption',NULL,'1','',NULL),(280,'ratingOption',NULL,'2','',NULL),(281,'ratingOption',NULL,'3','',NULL),(282,'ratingOption',NULL,'4','',NULL),(283,'ratingOption',NULL,'5','',NULL),(285,'ratingOption',NULL,'1','',NULL),(286,'ratingOption',NULL,'2','',NULL),(287,'ratingOption',NULL,'3','',NULL),(288,'ratingOption',NULL,'4','',NULL),(289,'ratingOption',NULL,'5','',NULL),(291,'ratingOption',NULL,'1','',NULL),(292,'ratingOption',NULL,'2','',NULL),(293,'ratingOption',NULL,'3','',NULL),(294,'ratingOption',NULL,'4','',NULL),(295,'ratingOption',NULL,'5','',NULL),(297,'ratingOption',NULL,'1','',NULL),(298,'ratingOption',NULL,'2','',NULL),(299,'ratingOption',NULL,'3','',NULL),(300,'ratingOption',NULL,'4','',NULL),(301,'ratingOption',NULL,'5','',NULL),(303,'rankOption',NULL,'1:CS 3220','',NULL),(304,'rankOption',NULL,'1:CS 1220','',NULL),(305,'rankOption',NULL,'2:CS 3220','',NULL),(306,'rankOption',NULL,'1:CS 3220','',NULL),(307,'rankOption',NULL,'2:CS 1220','',NULL),(308,'rankOption',NULL,'3:CS 3220','',NULL),(309,'rankOption',NULL,'1:CS 5112','',NULL),(310,'rankOption',NULL,'2:CS 3220','',NULL),(311,'rankOption',NULL,'3:CS 1220','',NULL),(312,'rankOption',NULL,'4:CS 3220','',NULL),(313,'rankOption',NULL,'1:CS 5990','',NULL),(314,'rankOption',NULL,'2:CS 5112','',NULL),(315,'rankOption',NULL,'3:CS 3220','',NULL),(316,'rankOption',NULL,'4:CS 1220','',NULL),(317,'rankOption',NULL,'5:CS 3220','',NULL),(318,'rankOption',NULL,'2:CS 5990','',NULL),(319,'rankOption',NULL,'3:CS 5112','',NULL),(320,'rankOption',NULL,'4:CS 3220','',NULL),(321,'rankOption',NULL,'5:CS 1220','',NULL),(322,'rankOption',NULL,'3:CS 5990','',NULL),(323,'rankOption',NULL,'4:CS 5112','',NULL),(324,'rankOption',NULL,'5:CS 3220','',NULL),(325,'rankOption',NULL,'4:CS 5990','',NULL),(326,'rankOption',NULL,'5:CS 5112','',NULL),(327,'rankOption',NULL,'5:CS 5990','',NULL),(329,'mulChoiceSelection',NULL,'More live demos in class','',NULL),(330,'mulChoiceSelection',NULL,'Homework with longer deadline','',NULL),(331,'mulChoiceSelection',NULL,'Slides come with more examples','',NULL),(332,'mulChoiceSelection',NULL,'More office hours','',NULL),(334,'textOption',NULL,'answered','',NULL),(335,'textOption',NULL,'skipped','',NULL),(336,'surveyId;questionId;mulChoiceSelectionIndex',3,'1;3;0','Faculty','6340d981-46c8-4c84-a4e3-2a9050da929b'),(337,'surveyId;questionId;mulChoiceSelectionIndex',3,'1;3;1','Student','6340d981-46c8-4c84-a4e3-2a9050da929b'),(338,'surveyId;questionId;mulChoiceSelectionIndex',3,'1;3;2','Alumni','6340d981-46c8-4c84-a4e3-2a9050da929b');
/*!40000 ALTER TABLE `response_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response_group_responses`
--

DROP TABLE IF EXISTS `response_group_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `response_group_responses` (
  `response_group_id` bigint NOT NULL,
  `responses_id` bigint NOT NULL,
  KEY `FK9dc9y3p6gv28c9k0koujg0jio` (`responses_id`),
  KEY `FKg1hn9yimr2flyq1no2o83ogyn` (`response_group_id`),
  CONSTRAINT `FK9dc9y3p6gv28c9k0koujg0jio` FOREIGN KEY (`responses_id`) REFERENCES `survey_responses` (`id`),
  CONSTRAINT `FKg1hn9yimr2flyq1no2o83ogyn` FOREIGN KEY (`response_group_id`) REFERENCES `response_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response_group_responses`
--

LOCK TABLES `response_group_responses` WRITE;
/*!40000 ALTER TABLE `response_group_responses` DISABLE KEYS */;
INSERT INTO `response_group_responses` VALUES (336,22),(336,34),(336,70),(336,82),(336,94),(336,106),(336,118),(336,154),(336,250),(337,46),(337,130),(337,190),(337,214),(337,238),(338,58),(338,142),(338,166),(338,178),(338,202),(338,226),(263,22),(263,34),(263,70),(263,82),(263,94),(263,106),(263,118),(263,154),(263,250),(264,46),(264,130),(264,190),(264,214),(264,238),(265,58),(265,142),(265,166),(265,178),(265,202),(265,226),(267,154),(268,118),(268,130),(268,142),(268,178),(268,202),(268,214),(268,238),(269,106),(269,166),(269,190),(269,226),(269,250),(270,22),(270,34),(270,46),(270,70),(270,82),(271,58),(271,94),(273,106),(274,34),(274,46),(274,166),(274,190),(274,226),(274,250),(275,22),(275,58),(275,70),(275,82),(275,94),(276,238),(277,118),(277,130),(277,142),(277,154),(277,178),(277,202),(277,214),(280,22),(280,34),(280,58),(280,70),(280,82),(280,94),(280,130),(281,46),(281,142),(281,154),(281,178),(282,118),(282,202),(282,214),(283,106),(283,166),(283,190),(283,226),(283,238),(283,250),(286,46),(286,142),(286,154),(287,22),(287,118),(287,166),(287,178),(287,202),(287,214),(287,226),(287,238),(288,34),(288,94),(288,130),(288,190),(289,58),(289,70),(289,82),(289,106),(289,250),(292,22),(292,94),(292,118),(292,214),(292,226),(292,238),(293,70),(293,82),(293,106),(293,130),(294,34),(294,166),(294,250),(295,46),(295,58),(295,142),(295,154),(295,178),(295,190),(295,202),(297,106),(298,22),(298,46),(298,130),(299,34),(299,58),(299,70),(299,82),(299,142),(299,178),(300,190),(300,202),(300,250),(301,94),(301,118),(301,154),(301,166),(301,214),(301,226),(301,238),(303,22),(303,190),(303,214),(304,106),(306,34),(306,70),(306,82),(306,130),(306,142),(306,154),(309,46),(309,58),(309,94),(309,118),(309,166),(309,202),(309,226),(309,238),(313,178),(313,250),(305,118),(305,154),(305,178),(305,202),(305,226),(307,22),(307,34),(307,70),(307,82),(307,130),(307,250),(310,214),(310,238),(314,142),(318,46),(318,58),(318,94),(318,106),(318,166),(318,190),(308,34),(308,70),(308,82),(308,94),(308,106),(308,130),(308,142),(308,166),(308,238),(308,250),(311,46),(311,58),(311,178),(311,190),(311,202),(315,22),(315,118),(319,154),(319,214),(322,226),(312,46),(316,94),(316,142),(316,166),(316,226),(320,58),(320,178),(320,190),(320,202),(320,250),(323,22),(323,34),(323,70),(323,82),(323,106),(323,130),(325,118),(325,154),(325,214),(325,238),(317,58),(321,118),(321,154),(321,214),(321,238),(324,46),(324,94),(324,106),(324,166),(324,226),(326,178),(326,190),(326,250),(327,22),(327,34),(327,70),(327,82),(327,130),(327,142),(327,202),(329,22),(329,34),(329,58),(329,70),(329,82),(329,94),(329,118),(329,130),(329,154),(329,166),(329,190),(329,214),(329,238),(330,22),(330,46),(330,58),(330,94),(330,106),(330,118),(330,130),(330,142),(330,166),(330,190),(330,202),(330,214),(330,226),(330,238),(330,250),(331,34),(331,58),(331,70),(331,82),(331,94),(331,106),(331,130),(331,142),(331,178),(331,202),(331,214),(331,226),(332,58),(332,118),(332,130),(332,154),(332,178),(332,250),(334,46),(334,58),(334,106),(334,154),(334,202),(334,226),(335,22),(335,34),(335,70),(335,82),(335,94),(335,118),(335,130),(335,142),(335,166),(335,178),(335,190),(335,214),(335,238),(335,250);
/*!40000 ALTER TABLE `response_group_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_responses`
--

DROP TABLE IF EXISTS `s_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `s_responses` (
  `id` bigint NOT NULL,
  `response_group_id` bigint NOT NULL,
  `selection_responses_key` int NOT NULL,
  PRIMARY KEY (`id`,`selection_responses_key`),
  UNIQUE KEY `UK_2j89cq81to2un15o5ajevkv9j` (`response_group_id`),
  CONSTRAINT `FKfwj8c1e6nd564gcqu94sfy4gw` FOREIGN KEY (`response_group_id`) REFERENCES `response_group` (`id`),
  CONSTRAINT `FKnfnly2184yefocbsy8dgwf360` FOREIGN KEY (`id`) REFERENCES `question_result_summary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_responses`
--

LOCK TABLES `s_responses` WRITE;
/*!40000 ALTER TABLE `s_responses` DISABLE KEYS */;
INSERT INTO `s_responses` VALUES (262,263,0),(262,264,1),(262,265,2),(328,329,0),(328,330,1),(328,331,2),(328,332,3);
/*!40000 ALTER TABLE `s_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `survey` (
  `id` bigint NOT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  `close_date` datetime(6) DEFAULT NULL,
  `closed` bit(1) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  `publish_date` datetime(6) DEFAULT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'6340d981-46c8-4c84-a4e3-2a9050da929b',NULL,_binary '\0','2021-04-16 23:16:01.199000','This is a survey for the students, faculty, and alumni in Computer Science Program at Cal. State LA. Please help us continue to improve this program by taking a few minutes to complete the survey.\n\nThis survey deals with Student Outcomes that describes what we are expected to know and be able to do by the time of graduation.\n\nABET Computing Accreditation Commission have defined SIX Student Outcomes  that relate to the knowledge, skills, and behaviors that students acquire as they progress through the program. \n\nPlease rate your satisfaction with the following outcomes, i.e. how well you think these outcomes have been achieved, with 1 being not satisfied and 5 being completely satisfied.\n\nYour comments and feedback are important to us and much appreciated.','2020-2022 CSULA Computer Science Program Survey','2021-04-16 23:37:46.000000',0),(13,'6340d981-46c8-4c84-a4e3-2a9050da929b',NULL,_binary '\0','2021-04-17 00:29:44.360000','','test submit response','2021-04-17 00:29:57.000000',0);
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_chart`
--

DROP TABLE IF EXISTS `survey_chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `survey_chart` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbex04exl8i30aa1c941tva3h` (`question_id`),
  CONSTRAINT `FKdbex04exl8i30aa1c941tva3h` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_chart`
--

LOCK TABLES `survey_chart` WRITE;
/*!40000 ALTER TABLE `survey_chart` DISABLE KEYS */;
INSERT INTO `survey_chart` VALUES (339,'Chart 1','qAdvChart',4);
/*!40000 ALTER TABLE `survey_chart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_chart_res_groups`
--

DROP TABLE IF EXISTS `survey_chart_res_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `survey_chart_res_groups` (
  `survey_chart_id` bigint NOT NULL,
  `res_groups_id` bigint NOT NULL,
  KEY `FKolsvsqr47s729oceef8sroxnw` (`res_groups_id`),
  KEY `FK9vxun4xdtd4sovjojxnombt7y` (`survey_chart_id`),
  CONSTRAINT `FK9vxun4xdtd4sovjojxnombt7y` FOREIGN KEY (`survey_chart_id`) REFERENCES `survey_chart` (`id`),
  CONSTRAINT `FKolsvsqr47s729oceef8sroxnw` FOREIGN KEY (`res_groups_id`) REFERENCES `response_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_chart_res_groups`
--

LOCK TABLES `survey_chart_res_groups` WRITE;
/*!40000 ALTER TABLE `survey_chart_res_groups` DISABLE KEYS */;
INSERT INTO `survey_chart_res_groups` VALUES (339,336),(339,337),(339,338);
/*!40000 ALTER TABLE `survey_chart_res_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_responses`
--

DROP TABLE IF EXISTS `survey_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `survey_responses` (
  `id` bigint NOT NULL,
  `date` datetime(6) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `survey_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp0gml0ba3fdu10y089635nsii` (`survey_id`),
  CONSTRAINT `FKp0gml0ba3fdu10y089635nsii` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_responses`
--

LOCK TABLES `survey_responses` WRITE;
/*!40000 ALTER TABLE `survey_responses` DISABLE KEYS */;
INSERT INTO `survey_responses` VALUES (16,'2016-04-17 02:12:54.369000',_binary '\0',13),(19,'2016-04-17 02:22:25.737000',_binary '\0',13),(22,'2020-04-17 02:27:53.900000',_binary '\0',1),(34,'2017-04-17 03:09:04.601000',_binary '\0',1),(46,'2017-04-17 03:11:11.986000',_binary '\0',1),(58,'2017-04-17 03:25:40.779000',_binary '\0',1),(70,'2015-04-17 03:30:22.682000',_binary '\0',1),(82,'2016-04-17 03:41:17.989000',_binary '\0',1),(94,'2019-04-17 03:42:04.155000',_binary '\0',1),(106,'2021-04-17 03:42:27.799000',_binary '\0',1),(118,'2021-04-17 03:42:50.175000',_binary '\0',1),(130,'2021-04-17 03:43:10.270000',_binary '\0',1),(142,'2020-04-17 03:43:30.113000',_binary '\0',1),(154,'2021-04-17 03:44:00.004000',_binary '\0',1),(166,'2017-04-17 03:44:19.552000',_binary '\0',1),(178,'2021-04-17 03:44:41.818000',_binary '\0',1),(190,'2018-04-17 03:45:03.783000',_binary '\0',1),(202,'2021-04-17 03:45:26.526000',_binary '\0',1),(214,'2021-04-17 03:45:49.801000',_binary '\0',1),(226,'2016-04-17 03:46:22.014000',_binary '\0',1),(238,'2020-04-17 03:46:42.268000',_binary '\0',1),(250,'2019-04-17 03:47:06.073000',_binary '\0',1);
/*!40000 ALTER TABLE `survey_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_responses`
--

DROP TABLE IF EXISTS `text_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `text_responses` (
  `id` bigint NOT NULL,
  `response_group_id` bigint NOT NULL,
  `text_responses_key` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`text_responses_key`),
  UNIQUE KEY `UK_1wfrc3t0vey5uidnsq390pv6c` (`response_group_id`),
  CONSTRAINT `FK8oo33o80ioygtwvgm5jjwb6ej` FOREIGN KEY (`id`) REFERENCES `question_result_summary` (`id`),
  CONSTRAINT `FKh0bn93ciflamo6jrmm8gt1jps` FOREIGN KEY (`response_group_id`) REFERENCES `response_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_responses`
--

LOCK TABLES `text_responses` WRITE;
/*!40000 ALTER TABLE `text_responses` DISABLE KEYS */;
INSERT INTO `text_responses` VALUES (333,334,'answered'),(333,335,'skipped');
/*!40000 ALTER TABLE `text_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-22 23:29:37

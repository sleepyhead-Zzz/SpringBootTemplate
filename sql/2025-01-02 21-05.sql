-- MySQL dump 10.13  Distrib 8.2.0, for Linux (x86_64)
--
-- Host: localhost    Database: springboottemplate
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父部门id',
  `ancestors` text NOT NULL COMMENT '祖级列表',
  `dept_name` varchar(64) NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `leader_id` bigint DEFAULT NULL,
  `leader_name` varchar(64) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '部门状态（0停用 1启用）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept`
VALUES (1, 0, '0', 'AgileBoot科技', 0, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (2, 1, '0,1', '深圳总公司', 1, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (3, 1, '0,1', '长沙分公司', 2, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (4, 2, '0,1,2', '研发部门', 1, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (5, 2, '0,1,2', '市场部门', 2, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 0,
        NULL, '2022-05-21 08:30:54', 1, '2023-07-20 22:46:41', 0),
       (6, 2, '0,1,2', '测试部门', 3, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (7, 2, '0,1,2', '财务部门', 4, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (8, 2, '0,1,2', '运维部门', 5, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 1,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0),
       (9, 3, '0,1,3', '市场部!', 1, NULL, 'Sleepyhead!!', '15888188888', 'valarc1hie@163.com', 0,
        NULL, '2022-05-21 08:30:54', 1, '2023-07-20 22:33:31', 0),
       (10, 3, '0,1,3', '财务部门', 2, NULL, 'Sleepyhead', '15888888888', 'Sleepyhead@163.com', 0,
        NULL, '2022-05-21 08:30:54', NULL, NULL, 0);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_login_info`
--

DROP TABLE IF EXISTS `sys_login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_login_info` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户账号',
  `ip_address` varchar(128) NOT NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) NOT NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) NOT NULL DEFAULT '' COMMENT '浏览器类型',
  `operation_system` varchar(50) NOT NULL DEFAULT '' COMMENT '操作系统',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
  `msg` varchar(255) NOT NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_login_info`
--

LOCK TABLES `sys_login_info` WRITE;
/*!40000 ALTER TABLE `sys_login_info` DISABLE KEYS */;
INSERT INTO `sys_login_info` VALUES (1,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-23 15:31:37',0),(2,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-23 15:33:10',0),(3,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-23 15:34:19',0),(4,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-23 15:34:33',0),(5,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-23 15:35:08',0),(6,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-23 16:17:55',0),(7,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:05:03',0),(8,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:24:01',0),(9,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:52:51',0),(10,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:53:15',0),(11,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:55:21',0),(12,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:58:29',0),(13,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:58:32',0),(14,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:58:33',0),(15,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:58:37',0),(16,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:58:38',0),(17,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 09:59:12',0),(18,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 10:00:25',0),(19,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 10:01:34',0),(20,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 10:02:01',0),(21,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 10:04:05',0),(22,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 10:05:33',0),(23,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-24 10:07:11',0),(24,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-25 15:31:16',0),(25,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-27 09:09:15',0),(26,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-27 12:27:52',0),(27,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-27 12:28:40',0),(28,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-27 13:35:44',0),(29,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-27 14:17:43',0),(30,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-09-30 09:15:55',0),(31,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-08 09:31:03',0),(32,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-08 13:55:48',0),(33,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-08 15:27:40',0),(34,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-08 21:55:01',0),(35,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-08 23:12:15',0),(36,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-10 10:45:30',0),(37,'sleepyhead@outlook.com','127.0.0.1','','Safari','iPhone',1,'登录成功','2024-10-10 14:28:57',0),(38,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-11 15:31:17',0),(39,'admin','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-11 15:35:47',0),(40,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-12 10:57:47',0),(41,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-25 15:02:22',0),(42,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-10-28 13:32:01',0),(43,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-11-12 09:56:20',0),(44,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-11-12 13:44:14',0),(45,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-11-13 10:16:20',0),(46,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-11-18 10:27:39',0),(47,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-11-20 09:21:43',0),(48,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-11-20 12:37:53',0),(49,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-13 09:54:16',0),(50,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-13 15:18:21',0),(51,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-16 12:13:00',0),(52,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-18 10:00:42',0),(53,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-18 15:29:59',0),(54,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-18 15:30:28',0),(55,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-18 15:30:31',0),(56,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-18 15:31:48',0),(57,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-18 15:32:11',0),(58,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-19 09:07:52',0),(59,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-19 09:07:53',0),(60,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-19 09:07:55',0),(61,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-19 09:08:24',0),(62,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:10:02',0),(63,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:12:00',0),(64,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:12:33',0),(65,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:12:54',0),(66,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:14:43',0),(67,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:15:00',0),(68,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:15:31',0),(69,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:18:04',0),(70,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:18:18',0),(71,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:19:49',0),(72,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:21:00',0),(73,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:22:16',0),(74,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:24:27',0),(75,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:25:27',0),(76,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:28:08',0),(77,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-19 14:50:46',0),(78,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 09:33:30',0),(79,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 09:35:22',0),(80,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:17:03',0),(81,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:22:25',0),(82,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:23:45',0),(83,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:24:22',0),(84,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:37:04',0),(85,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:39:04',0),(86,'sleepyhead@outlook.com','172.17.0.3','','Firefox','Linux',2,'退出成功','2024-12-22 10:39:36',0),(87,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 10:40:09',0),(88,'sleepyhead@outlook.com','172.17.0.3','','Firefox','Linux',2,'退出成功','2024-12-22 10:40:16',0),(89,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 14:45:22',0),(90,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-22 15:12:51',0),(91,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 16:29:02',0),(92,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-22 18:55:14',0),(93,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-23 10:07:29',0),(94,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-23 13:43:26',0),(95,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-24 10:53:41',0),(96,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-24 11:27:50',0),(97,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-24 12:08:31',0),(98,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-24 14:00:25',0),(99,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-25 09:07:25',0),(100,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-25 10:49:30',0),(101,'sleepyhead@outlook.com','172.17.0.3','','Firefox','Linux',2,'退出成功','2024-12-25 11:18:48',0),(102,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-25 11:19:49',0),(103,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-25 12:23:43',0),(104,'sleepyhead@outlook.com','172.17.0.3','','Firefox','Linux',2,'退出成功','2024-12-25 12:25:17',0),(105,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-25 12:25:19',0),(106,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-25 13:46:50',0),(107,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-26 09:31:30',0),(108,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Linux',1,'登录成功','2024-12-26 10:34:28',0),(109,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-26 10:35:54',0),(110,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-26 12:09:37',0),(111,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-26 13:45:34',0),(112,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-26 15:12:47',0),(113,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-28 12:33:57',0),(114,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-28 17:28:20',0),(115,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-28 20:22:57',0),(116,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-30 09:14:19',0),(117,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-30 12:23:59',0),(118,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-30 12:45:49',0),(119,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-30 13:36:56',0),(120,'sleepyhead@outlook.com','172.17.0.3','','Firefox','Linux',2,'退出成功','2024-12-30 14:09:20',0),(121,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-30 14:09:23',0),(122,'sleepyhead@outlook.com','172.17.0.3','','Firefox','Linux',2,'退出成功','2024-12-30 14:18:37',0),(123,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-30 14:18:39',0),(124,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-31 09:05:36',0),(125,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-31 09:53:49',0),(126,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-31 12:16:30',0),(127,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-31 15:02:37',0),(128,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2024-12-31 17:26:42',0),(129,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2025-01-01 09:38:51',0),(130,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2025-01-01 10:12:22',0),(131,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2025-01-01 11:28:40',0),(132,'sleepyhead@outlook.com','192.168.3.189','','Firefox','Linux',1,'登录成功','2025-01-01 14:11:19',0),(133,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Windows 10 or Windows Server 2016',1,'登录成功','2025-01-02 17:10:19',0),(134,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Windows 10 or Windows Server 2016',1,'登录成功','2025-01-02 19:13:29',0),(135,'sleepyhead@outlook.com','127.0.0.1','','Firefox','Windows 10 or Windows Server 2016',1,'登录成功','2025-01-02 19:13:31',0);
/*!40000 ALTER TABLE `sys_login_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT '0',
  `name` varchar(120) DEFAULT NULL,
  `menu_type` int DEFAULT NULL,
  `component` varchar(120) DEFAULT NULL,
  `path` varchar(120) DEFAULT NULL,
  `redirect` varchar(120) DEFAULT NULL,
  `meta` varchar(254) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT '0',
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (2,0,'Dashboard',2,'BasicLayout','/',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"page.dashboard.title\"}',NULL,'概览',4,'2024-12-28 20:44:19',4,'2024-12-30 14:37:11',0,1),(3,2,'Analytics',1,'/dashboard/analytics/index','/analytics',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:area-chart\",\"ignoreAccess\":false,\"title\":\"page.dashboard.analytics\"}',NULL,'分析页',4,'2024-12-28 20:52:19',4,'2024-12-30 14:38:30',0,1),(5,0,'Demos',2,'BasicLayout','/demos',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"ic:baseline-view-in-ar\",\"ignoreAccess\":false,\"keepAlive\":true,\"order\":1000,\"title\":\"demos.title\"}',NULL,'演示',4,'2024-12-30 13:43:27',4,'2024-12-30 14:38:00',0,1),(6,5,'NaiveDemos',1,'/demos/naive/index','/demos/naive',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"\",\"ignoreAccess\":false,\"title\":\"demos.naive\"}',NULL,'NativeUI',4,'2024-12-30 14:03:31',4,'2024-12-30 14:40:47',0,1),(7,5,'Table',1,'/demos/table/index','/demos/table',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"ignoreAccess\":false,\"title\":\"demos.table\"}',NULL,'Table',4,'2024-12-30 14:04:21',4,'2024-12-30 14:38:57',0,1),(8,5,'Form',1,'/demos/form/basic','/demos/form',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"ignoreAccess\":false,\"title\":\"demos.form\"}',NULL,'表单演示',4,'2024-12-30 14:05:00',4,'2024-12-30 14:38:52',0,1),(9,0,'SystemManage',2,'BasicLayout','/system/menu','/system/menu/index','{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:account-settings-variant\",\"ignoreAccess\":false,\"title\":\"system.title\"}',NULL,'系统管理',4,'2024-12-30 14:05:48',4,'2025-01-01 14:28:57',0,1),(10,9,'MenuManage',1,'/system/menu/index','/system/menu/index',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:menu\",\"ignoreAccess\":false,\"title\":\"system.menu.title\"}',NULL,'菜单管理',4,'2024-12-30 14:06:32',4,'2025-01-01 14:29:06',0,1),(11,2,'Workspace',1,'/dashboard/workspace/index','/workspace',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"carbon:workspace\",\"ignoreAccess\":false,\"keepAlive\":true,\"title\":\"page.dashboard.workspace\"}',NULL,'工作台',4,'2024-12-30 14:09:43',4,'2024-12-30 14:39:30',0,1),(12,9,'UserManage',1,'/system/user/index','/system/user/index',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:account\",\"ignoreAccess\":false,\"title\":\"system.user.title\"}',NULL,'用户管理',4,'2024-12-30 14:50:03',NULL,NULL,0,1),(13,9,'DeptManage',1,'/system/dept/index','/system/dept/index',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"fluent:organization-20-filled\",\"ignoreAccess\":false,\"title\":\"system.dept.title\"}',NULL,'部门管理',4,'2025-01-01 14:29:47',4,'2025-01-01 14:35:44',0,1),(14,0,'VbenProject',2,'BasicLayout','/vben-admin',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"\",\"ignoreAccess\":false,\"order\":9999,\"title\":\"demos.vben.title\"}',NULL,'项目',4,'2025-01-01 14:44:20',4,'2025-01-01 14:47:11',0,1),(15,14,'VbenDocument',3,'IFrameView','/vben-admin/document',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"iframeSrc\":\"https://doc.vben.pro\",\"ignoreAccess\":false,\"title\":\"demos.vben.document\"}',NULL,'文档',4,'2025-01-01 14:45:47',4,'2025-01-01 14:46:23',0,1),(16,9,'RoleManage',1,'/system/role/index','/system/role/index',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"eos-icons:role-binding\",\"ignoreAccess\":false,\"title\":\"system.role.title\"}',NULL,'角色管理',4,'2025-01-02 19:15:50',4,'2025-01-02 19:19:42',0,1),(17,9,'PostManage',1,'/system/post/index','/system/post/index',NULL,'{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:post\",\"ignoreAccess\":false,\"title\":\"system.post.title\"}',NULL,'职位管理',4,'2025-01-02 19:16:27',NULL,NULL,0,1);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_operation_log`
--

DROP TABLE IF EXISTS `sys_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_operation_log` (
  `operation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `business_type` smallint NOT NULL DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `request_method` smallint NOT NULL DEFAULT '0' COMMENT '请求方式',
  `request_module` varchar(64) NOT NULL DEFAULT '' COMMENT '请求模块',
  `request_url` varchar(256) NOT NULL DEFAULT '' COMMENT '请求URL',
  `called_method` varchar(128) NOT NULL DEFAULT '' COMMENT '调用方法',
  `operator_type` smallint NOT NULL DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `user_id` bigint DEFAULT '0' COMMENT '用户ID',
  `username` varchar(32) DEFAULT '' COMMENT '操作人员',
  `operator_ip` varchar(128) DEFAULT '' COMMENT '操作人员ip',
  `operator_location` varchar(256) DEFAULT '' COMMENT '操作地点',
  `dept_id` bigint DEFAULT '0' COMMENT '部门ID',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `operation_param` varchar(2048) DEFAULT '' COMMENT '请求参数',
  `operation_result` varchar(2048) DEFAULT '' COMMENT '返回参数',
  `status` smallint NOT NULL DEFAULT '1' COMMENT '操作状态（1正常 0异常）',
  `error_stack` varchar(2048) DEFAULT '' COMMENT '错误消息',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_operation_log`
--

LOCK TABLES `sys_operation_log` WRITE;
/*!40000 ALTER TABLE `sys_operation_log` DISABLE KEYS */;
INSERT INTO `sys_operation_log` VALUES (1,1,2,'用户管理','/system/users','com.springboottemplate.admin.controller.system.SysUserController.add()',1,0,'admin','127.0.0.1','',0,NULL,'{\"deptId\":1,\"username\":\"sleepyhead@outlook.com\",\"nickname\":\"sleepyhead\",\"email\":\"sleepyhead@outlook.com\",\"phoneNumber\":\"13213\",\"sex\":0,\"avatar\":\"\",\"password\":\"123456\",\"status\":1,\"roleId\":1,\"postId\":1,\"remark\":\"\"},','',1,'','2024-09-24 09:11:56',0),(2,1,2,'用户管理','/system/users','com.springboottemplate.admin.controller.system.SysUserController.add()',1,0,'admin','127.0.0.1','',0,NULL,'{\"deptId\":1,\"username\":\"sleepyhead@outlook.com\",\"nickname\":\"sleepyhead\",\"email\":\"sleepyhead@outlook.com\",\"phoneNumber\":\"13213\",\"sex\":0,\"avatar\":\"\",\"password\":\"123456\",\"status\":1,\"roleId\":1,\"postId\":1,\"remark\":\"\"},','',0,'用户名已被其他用户占用','2024-09-24 09:15:09',0),(3,1,2,'用户管理','/system/users','com.springboottemplate.admin.controller.system.SysUserController.add()',1,0,'admin','127.0.0.1','',0,NULL,'{\"deptId\":1,\"username\":\"sleepyhead@outlook.com\",\"nickname\":\"sleepyhead\",\"email\":\"sleepyhead@outlook.com\",\"phoneNumber\":\"13213\",\"sex\":0,\"avatar\":\"\",\"password\":\"123456\",\"status\":1,\"roleId\":1,\"postId\":1,\"remark\":\"\"},','',0,'用户名已被其他用户占用','2024-09-24 09:15:14',0),(4,1,2,'用户管理','/system/users','com.springboottemplate.admin.controller.system.SysUserController.add()',1,0,'admin','127.0.0.1','',0,NULL,'{\"deptId\":1,\"username\":\"sleepyhead@outlook.com\",\"nickname\":\"sleepyhead\",\"email\":\"sleepyhead@outlook.com\",\"phoneNumber\":\"13213\",\"sex\":0,\"avatar\":\"\",\"password\":\"123456\",\"status\":1,\"roleId\":1,\"postId\":1,\"remark\":\"\"},','',0,'用户名已被其他用户占用','2024-09-24 09:19:18',0),(5,1,2,'用户管理','/system/users','com.springboottemplate.admin.controller.system.SysUserController.add()',1,0,'admin','127.0.0.1','',0,NULL,'{\"deptId\":1,\"username\":\"sleepyhead@outlook.com\",\"nickname\":\"sleepyhead\",\"email\":\"sleepyhead@outlook.com\",\"phoneNumber\":\"13213\",\"sex\":0,\"avatar\":\"\",\"password\":\"!@#ASAS!@123132\",\"status\":1,\"roleId\":1,\"postId\":1,\"remark\":\"\"},','',1,'','2024-09-24 09:21:20',0),(6,1,2,'用户管理','/system/users','com.springboottemplate.admin.controller.system.SysUserController.add()',1,0,'admin','127.0.0.1','',0,NULL,'{\"deptId\":1,\"username\":\"sleepyhead@outlook.com\",\"nickname\":\"sleepyhead\",\"email\":\"sleepyhead@outlook.com\",\"phoneNumber\":\"13213\",\"sex\":0,\"avatar\":\"\",\"password\":\"!@#ASAS!@123132\",\"status\":1,\"roleId\":1,\"postId\":1,\"remark\":\"\"},','',0,'用户名已被其他用户占用','2024-09-24 09:22:36',0),(7,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Demos\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/demos\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"ic:baseline-view-in-ar\",\"ignoreAccess\":false,\"title\":\"$t(\'demos.title\')\"}},','',0,'数据库异常','2024-12-26 12:47:27',0),(8,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Demos\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/demos\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"ic:baseline-view-in-ar\",\"ignoreAccess\":false,\"title\":\"$t(\'demos.title\')\"}},','',1,'','2024-12-26 12:52:55',0),(9,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Demos\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/demos\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"ic:baseline-view-in-ar\",\"ignoreAccess\":false,\"title\":\"$t(\'demos.title\')\"}},','',1,'','2024-12-26 12:54:45',0),(10,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Demos\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/demos\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"ic:baseline-view-in-ar\",\"ignoreAccess\":false,\"title\":\"$t(\'demos.title\')\"}},','',1,'','2024-12-26 12:55:14',0),(11,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"$t(\'page.dashboard.title\')\"},\"remark\":\"概览\"},','',1,'','2024-12-28 20:35:49',0),(12,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"$t(\'page.dashboard.title\')\"},\"remark\":\"概览\"},','',0,'新增菜单:{} 失败，菜单名称已存在','2024-12-28 20:35:51',0),(13,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"$t(\'page.dashboard.title\')\"},\"remark\":\"概览\"},','',0,'新增菜单:{} 失败，菜单名称已存在','2024-12-28 20:35:55',0),(14,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"$t(\'page.dashboard.title\')\"},\"remark\":\"概览\"},','',1,'','2024-12-28 20:44:19',0),(15,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Analytics\",\"menuType\":1,\"component\":\"/dashboard/analytics/index\",\"path\":\"/analytics\",\"meta\":{\"affixTab\":true,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:area-chart\",\"ignoreAccess\":false,\"title\":\"$t(\'page.dashboard.analytics\')\"},\"remark\":\"分析页\"},','',1,'','2024-12-28 20:52:19',0),(16,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Workspace\",\"menuType\":1,\"component\":\"/dashboard/workspace/index\",\"path\":\"/workspace\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"carbon:workspace\",\"ignoreAccess\":false,\"keepAlive\":true,\"title\":\"$t(\'page.dashboard.workspace\')\"},\"remark\":\"工作台\",\"status\":true},','',1,'','2024-12-30 09:47:46',0),(17,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":3,\"menuName\":\"123\",\"menuType\":1,\"component\":\"123\",\"path\":\"213\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"132\",\"ignoreAccess\":false,\"title\":\"123\"},\"remark\":\"123\",\"status\":true},','',0,'只允许在目录类型底下创建子菜单','2024-12-30 12:24:16',0),(18,3,4,'菜单管理','/system/menus/2','com.springboottemplate.admin.controller.system.SysMenuController.remove()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=2}','',0,'存在子菜单不允许删除','2024-12-30 12:42:14',0),(19,3,4,'菜单管理','/system/menus/2','com.springboottemplate.admin.controller.system.SysMenuController.remove()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=2}','',0,'存在子菜单不允许删除','2024-12-30 12:42:24',0),(20,3,4,'菜单管理','/system/menus/2','com.springboottemplate.admin.controller.system.SysMenuController.remove()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=2}','',0,'存在子菜单不允许删除','2024-12-30 13:38:21',0),(21,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Demos\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/demos\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"ic:baseline-view-in-ar\",\"ignoreAccess\":false,\"keepAlive\":true,\"order\":1000,\"title\":\"$t(\'demos.title\')\"},\"remark\":\"演示\",\"status\":true},','',1,'','2024-12-30 13:43:27',0),(22,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":5,\"menuName\":\"NaiveDemos\",\"menuType\":1,\"component\":\"/demos/naive/index\",\"path\":\"/demos/naive\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"123\",\"ignoreAccess\":false,\"title\":\"$t(\'demos.naive\')\"},\"remark\":\"NativeUI\",\"status\":true},','',1,'','2024-12-30 14:03:31',0),(23,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":5,\"menuName\":\"Table\",\"menuType\":1,\"component\":\"/demos/table/index\",\"path\":\"/demos/table\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"ignoreAccess\":false,\"title\":\"$t(\'demos.table\')\"},\"remark\":\"Table\",\"status\":true},','',1,'','2024-12-30 14:04:21',0),(24,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":5,\"menuName\":\"Form\",\"menuType\":1,\"component\":\"/demos/form/basic\",\"path\":\"/demos/form\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"ignoreAccess\":false,\"title\":\"$t(\'demos.form\')\"},\"remark\":\"表单演示\",\"status\":true},','',1,'','2024-12-30 14:05:00',0),(25,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"Menu\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/system/menu\",\"redirect\":\"/system/menu/index\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:account-settings-variant\",\"ignoreAccess\":false,\"title\":\"$t(\'system.title\')\"},\"remark\":\"系统管理\",\"status\":true},','',1,'','2024-12-30 14:05:48',0),(26,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":9,\"menuName\":\"MenuIndex\",\"menuType\":1,\"component\":\"/system/menu/index\",\"path\":\"/system/menu/index\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:menu\",\"ignoreAccess\":false,\"title\":\"$t(\'system.menu.title\')\"},\"remark\":\"菜单管理\",\"status\":true},','',1,'','2024-12-30 14:06:32',0),(27,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":2,\"menuName\":\"Workspace\",\"menuType\":1,\"component\":\"/dashboard/workspace/index\",\"path\":\"/workspace\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"carbon:workspace\",\"ignoreAccess\":false,\"keepAlive\":true,\"title\":\"$t(\'page.dashboard.workspace\')\"},\"remark\":\"工作台\",\"status\":true},','',1,'','2024-12-30 14:09:43',0),(28,3,4,'菜单管理','/system/menus/4','com.springboottemplate.admin.controller.system.SysMenuController.remove()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=4}','',0,'菜单已分配给角色，不允许','2024-12-30 14:09:54',0),(29,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":0,\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"page.dashboard.title\"},\"remark\":\"概览\",\"status\":true},','',0,'新增菜单:{} 失败，菜单名称已存在','2024-12-30 14:28:52',0),(30,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":0,\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"page.dashboard.title\"},\"remark\":\"概览\",\"status\":true},','',0,'新增菜单:{} 失败，菜单名称已存在','2024-12-30 14:29:20',0),(31,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":0,\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"$t(\'page.dashboard.title\')\"},\"remark\":\"概览\",\"status\":true},','',0,'新增菜单:{} 失败，菜单名称已存在','2024-12-30 14:29:29',0),(32,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":0,\"menuName\":\"Dashboard\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"lucide:layout-dashboard\",\"ignoreAccess\":false,\"title\":\"page.dashboard.title\"},\"remark\":\"概览\",\"status\":true},','',0,'新增菜单:{} 失败，菜单名称已存在','2024-12-30 14:36:37',0),(33,2,3,'菜单管理','/system/menus/2','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=2}','',1,'','2024-12-30 14:37:00',0),(34,2,3,'菜单管理','/system/menus/2','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=2}','',1,'','2024-12-30 14:37:11',0),(35,2,3,'菜单管理','/system/menus/5','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=5}','',1,'','2024-12-30 14:38:00',0),(36,2,3,'菜单管理','/system/menus/3','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=3}','',1,'','2024-12-30 14:38:30',0),(37,2,3,'菜单管理','/system/menus/6','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=6}','',1,'','2024-12-30 14:38:47',0),(38,2,3,'菜单管理','/system/menus/8','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=8}','',1,'','2024-12-30 14:38:52',0),(39,2,3,'菜单管理','/system/menus/7','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=7}','',1,'','2024-12-30 14:38:57',0),(40,2,3,'菜单管理','/system/menus/9','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=9}','',1,'','2024-12-30 14:39:01',0),(41,2,3,'菜单管理','/system/menus/10','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=10}','',1,'','2024-12-30 14:39:07',0),(42,2,3,'菜单管理','/system/menus/11','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=11}','',1,'','2024-12-30 14:39:30',0),(43,2,3,'菜单管理','/system/menus/6','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=6}','',1,'','2024-12-30 14:40:18',0),(44,2,3,'菜单管理','/system/menus/6','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=6}','',1,'','2024-12-30 14:40:47',0),(45,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":9,\"menuName\":\"UserManage\",\"menuType\":1,\"component\":\"/system/user/index\",\"path\":\"/system/user/index\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:account\",\"ignoreAccess\":false,\"title\":\"system.user.title\"},\"remark\":\"用户管理\",\"status\":true},','',1,'','2024-12-30 14:50:03',0),(46,2,3,'菜单管理','/system/menus/9','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=9}','',1,'','2025-01-01 14:28:57',0),(47,2,3,'菜单管理','/system/menus/10','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=10}','',1,'','2025-01-01 14:29:07',0),(48,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":9,\"menuName\":\"DeptManage\",\"menuType\":1,\"component\":\"/system/dept/index\",\"path\":\"/system/dept/index\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"ignoreAccess\":false,\"title\":\"system.dept.title\"},\"remark\":\"部门管理\",\"status\":true},','',1,'','2025-01-01 14:29:47',0),(49,2,3,'菜单管理','/system/menus/13','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=13}','',1,'','2025-01-01 14:35:44',0),(50,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"menuName\":\"VbenProject\",\"menuType\":2,\"component\":\"BasicLayout\",\"path\":\"/vben-admin\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"\",\"ignoreAccess\":false,\"title\":\"demos.vben.title\"},\"remark\":\"项目\",\"status\":true},','',1,'','2025-01-01 14:44:20',0),(51,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{\"parentId\":14,\"menuName\":\"VbenDocument\",\"menuType\":3,\"component\":\"IFrameView\",\"path\":\"/vben-admin/document\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"iframeSrc\":\"https://doc.vben.pro\",\"ignoreAccess\":false,\"title\":\"demos.vben.document\"},\"remark\":\"文档\",\"status\":true},','',1,'','2025-01-01 14:45:47',0),(52,2,3,'菜单管理','/system/menus/15','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=15}','',1,'','2025-01-01 14:46:23',0),(53,2,3,'菜单管理','/system/menus/14','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','192.168.3.189','',0,NULL,'{menuId=14}','',1,'','2025-01-01 14:47:11',0),(54,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','127.0.0.1','',0,NULL,'{\"parentId\":9,\"menuName\":\"RoleManage\",\"menuType\":1,\"component\":\"/system/role/index\",\"path\":\"/system/role/index\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:role\",\"ignoreAccess\":false,\"title\":\"system.menu.title\"},\"remark\":\"角色管理\",\"status\":true},','',1,'','2025-01-02 19:15:50',0),(55,1,2,'菜单管理','/system/menus','com.springboottemplate.admin.controller.system.SysMenuController.add()',1,0,'sleepyhead@outlook.com','127.0.0.1','',0,NULL,'{\"parentId\":9,\"menuName\":\"PostManage\",\"menuType\":1,\"component\":\"/system/post/index\",\"path\":\"/system/post/index\",\"meta\":{\"affixTab\":false,\"affixTabOrder\":0,\"hideChildrenInMenu\":false,\"hideInBreadcrumb\":false,\"hideInMenu\":false,\"hideInTab\":false,\"icon\":\"mdi:post\",\"ignoreAccess\":false,\"title\":\"system.post.title\"},\"remark\":\"职位管理\",\"status\":true},','',1,'','2025-01-02 19:16:28',0),(56,2,3,'菜单管理','/system/menus/16','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','127.0.0.1','',0,NULL,'{menuId=16}','',1,'','2025-01-02 19:16:33',0),(57,2,3,'菜单管理','/system/menus/16','com.springboottemplate.admin.controller.system.SysMenuController.edit()',1,0,'sleepyhead@outlook.com','127.0.0.1','',0,NULL,'{menuId=16}','',1,'','2025-01-02 19:19:42',0);
/*!40000 ALTER TABLE `sys_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(64) NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` smallint NOT NULL COMMENT '状态（1正常 0停用）',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `creator_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,1,'',NULL,'2022-05-21 08:30:54',NULL,NULL,0),(2,'se','项目经理',2,1,'',NULL,'2022-05-21 08:30:54',NULL,NULL,0),(3,'hr','人力资源',3,1,'',NULL,'2022-05-21 08:30:54',NULL,NULL,0),(4,'user','普通员工',5,0,'',NULL,'2022-05-21 08:30:54',NULL,NULL,0);
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_key` varchar(128) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` smallint DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）',
  `dept_id_set` varchar(1024) DEFAULT '' COMMENT '角色所拥有的部门数据权限',
  `status` smallint NOT NULL COMMENT '角色状态（1正常 0停用）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,1,'',1,NULL,'2022-05-21 08:30:54',NULL,NULL,'超级管理员',0),(2,'普通角色','common',3,2,'',1,NULL,'2022-05-21 08:30:54',NULL,NULL,'普通角色',0),(3,'闲置角色','unused',4,2,'',0,NULL,'2022-05-21 08:30:54',NULL,NULL,'未使用的角色',0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,43),(2,44),(2,45),(2,46),(2,47),(2,48),(2,49),(2,50),(2,51),(2,52),(2,53),(2,54),(2,55),(2,56),(2,57),(2,58),(2,59),(2,60),(2,61),(3,1),(111,1);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `post_id` bigint DEFAULT NULL COMMENT '职位id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `username` varchar(64) NOT NULL COMMENT '用户账号',
  `nickname` varchar(32) NOT NULL COMMENT '用户昵称',
  `user_type` smallint DEFAULT '0' COMMENT '用户类型（00系统用户）',
  `email` varchar(128) DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(18) DEFAULT '' COMMENT '手机号码',
  `sex` smallint DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(512) DEFAULT '' COMMENT '头像地址',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '帐号状态（1正常 2停用 3冻结）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '超级管理员标志（1是，0否）',
  `creator_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,1,1,1,'admin','superadmin',0,'','123456',0,'','$2a$10$MFUKIUS2wS/khRSRlW.VseoEN9QjqkTeq9b7eNmt.FxAZyQck3c1a',1,'127.0.0.1','2024-10-11 15:35:47',1,NULL,'2024-09-17 11:28:31',2,'2024-10-11 15:35:47','',0),(4,1,1,1,'sleepyhead@outlook.com','sleepyhead',0,'sleepyhead@outlook.com','13213',0,'','$2a$10$GAFJe7nsQ6Mqe3LRAfe2a.XqFYEGWuXo93e88LlorTdmIVyoDuP5e',1,'127.0.0.1','2025-01-02 19:13:31',1,1,'2024-09-24 09:21:20',4,'2025-01-02 19:13:31','',0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-02 21:06:57

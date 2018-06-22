-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: teaching_service_system
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `parent_authority_id` bigint(20) DEFAULT NULL COMMENT '父权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `code` varchar(100) DEFAULT NULL COMMENT '权限编码',
  `module` varchar(100) DEFAULT NULL COMMENT '模块名称',
  PRIMARY KEY (`id`),
  KEY `fk_parent_idx` (`parent_authority_id`),
  CONSTRAINT `fk_parent` FOREIGN KEY (`parent_authority_id`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,NULL,'审核商家','merchant','系统模块'),(2,NULL,'测','test','商户模块'),(3,NULL,'测试','唐唐','系统模块');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_number` varchar(64) NOT NULL COMMENT '课程号',
  `course_name` varchar(45) DEFAULT NULL COMMENT '课程名称',
  `teacher` varchar(45) DEFAULT NULL COMMENT '任课老师',
  `course_info` varchar(255) DEFAULT NULL COMMENT '课程信息',
  `course_credit` decimal(4,2) DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_number_UNIQUE` (`course_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'HGH','dd','dd','dd',5.00),(2,'G-002','数据库','mo','实验为主',5.00),(3,'SHU-01','高数','mm','',6.00);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_number` int(11) NOT NULL COMMENT '课程号',
  `exam_time` datetime NOT NULL COMMENT '考试时间',
  `exam_place` varchar(255) NOT NULL COMMENT '考试地点',
  `exam_capacity` int(11) DEFAULT NULL COMMENT '考点容量',
  `invigilator` varchar(64) DEFAULT NULL COMMENT '监考人员',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_exam_idx` (`course_number`),
  CONSTRAINT `fk_exam` FOREIGN KEY (`course_number`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='考试安排表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (17,2,'2018-06-05 00:00:00','复505',22,'mm',''),(18,3,'2018-06-14 15:13:27','中106',60,'jj','');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `level` int(11) DEFAULT NULL COMMENT '菜单等级',
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `href` varchar(200) DEFAULT NULL COMMENT '菜单链接',
  `parent_menu_id` bigint(20) DEFAULT NULL COMMENT '父菜单id',
  `seq` int(11) DEFAULT NULL COMMENT '菜单排序权值',
  `is_show` tinyint(1) DEFAULT '1' COMMENT '是否显示',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标，是fontawesome.io/icons的样式，内容是fa fa-search这种形式',
  PRIMARY KEY (`id`),
  KEY `fk_parent1_idx` (`parent_menu_id`),
  CONSTRAINT `fk_parent1` FOREIGN KEY (`parent_menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,0,'主菜单',NULL,NULL,1,1,0,NULL),(2,1,'系统管理','#',1,1,1,0,'fa fa-cog'),(3,2,'菜单管理','admin/menu/list',2,1,1,0,'fa fa-bars'),(4,2,'用户管理','admin/user/list',2,5,1,0,'fa fa-user'),(12,2,'角色管理','admin/role/list',2,2,1,0,'fa fa-key'),(13,2,'发布课程','admin/course/list',26,4,1,0,''),(14,2,'发布考试','admin/exam/list',25,6,1,0,''),(15,2,'发布通知','admin/notice/list',27,8,1,0,''),(16,2,'学生管理','admin/student/list',19,7,0,0,''),(17,2,'学生管理','admin/student/list',19,7,1,0,''),(18,2,'教师管理','admin/teacher/list',19,2,1,0,''),(19,1,'人员管理','#',1,2,1,0,'fa fa-cog'),(20,2,'选课','admin/studentCourse/list',24,6,1,0,''),(21,2,'选课结果','admin/studentCourse/result',24,8,1,0,''),(22,2,'选课结果','admin/studentCourse/result',24,8,1,0,''),(23,2,'查看考试','admin/exam/student',25,12,1,0,''),(24,1,'选课服务','#',1,6,1,0,''),(25,1,'考试安排','#',1,5,1,0,''),(26,1,'课程管理','#',1,5,1,0,''),(27,1,'通知管理','#',1,2,1,0,'');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '通知内容',
  `notice_time` datetime DEFAULT NULL COMMENT '通知时间',
  `initiator` varchar(45) DEFAULT NULL COMMENT '通知发起者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (4,'课程实验','周日前交实验报告','2018-06-01 09:01:57','mo'),(5,'考试安排发出','请学生查看考试安排','2018-06-14 15:14:28','教务处');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `edit_able` tinyint(1) DEFAULT '1' COMMENT '是否可编辑',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员',NULL,'admin',0),(6,'教职工','',NULL,1),(7,'学生','',NULL,1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_authority`
--

DROP TABLE IF EXISTS `role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_idx` (`role_id`),
  KEY `fk_authority_idx` (`authority_id`),
  CONSTRAINT `fk_authority` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_authority`
--

LOCK TABLES `role_authority` WRITE;
/*!40000 ALTER TABLE `role_authority` DISABLE KEYS */;
INSERT INTO `role_authority` VALUES (6,1,2),(7,1,1),(8,1,3);
/*!40000 ALTER TABLE `role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_idx` (`role_id`),
  KEY `fk_menu_idx` (`menu_id`),
  CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=432 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (403,1,2),(404,1,3),(405,1,12),(406,1,4),(407,1,19),(408,1,18),(409,1,16),(410,1,17),(411,1,27),(412,1,15),(413,1,25),(414,1,14),(415,1,26),(416,1,13),(417,6,25),(418,6,14),(419,6,26),(420,6,13),(427,7,25),(428,7,23),(429,7,24),(430,7,20),(431,7,21);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_number` varchar(64) NOT NULL COMMENT '学生号',
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  `id_card` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `native_place` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `class` varchar(45) DEFAULT NULL COMMENT '班级',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_number_UNIQUE` (`student_number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2,'201508','mm','男','450902199609282957','2018-05-11','广西','328863397@qq.com','15084978668','信息院','计科1505','ef5cf7cd9d5df30ebe9b6e2164de1c3e'),(3,'201505','qqq','男','450902199609282957','2018-05-28','广西','328863397@qq.com','15084978667','信息院','计科1505','d65a02ff6cda66dc78576b766b03c770'),(4,'201508010520','chen','nan','450902199609282957','2018-06-19','guangxi','328863397@qq.com','15084978667','信息院','计科1505','468bea32fb2c7ea8ce107d8f4aa28885');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_number` int(11) NOT NULL COMMENT '学号',
  `course_number` int(11) NOT NULL COMMENT '课程号',
  `score` int(3) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`),
  KEY `fk_stu_idx` (`student_number`),
  KEY `fk_cou_idx` (`course_number`),
  CONSTRAINT `fk_cou` FOREIGN KEY (`course_number`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_stu` FOREIGN KEY (`student_number`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (8,3,2,NULL),(9,2,2,NULL),(10,4,2,NULL);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfig`
--

DROP TABLE IF EXISTS `systemconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` text COMMENT 'value',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数配置(如地图信息之类的也会存在这里)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfig`
--

LOCK TABLES `systemconfig` WRITE;
/*!40000 ALTER TABLE `systemconfig` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemlog`
--

DROP TABLE IF EXISTS `systemlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `module` varchar(50) DEFAULT NULL COMMENT '模块',
  `operation` varchar(50) DEFAULT NULL COMMENT '操作',
  `params` text COMMENT '参数',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '是否成功',
  `error_message` text COMMENT '错误信息',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `request_method` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `headers` text COMMENT 'http headers',
  PRIMARY KEY (`id`),
  KEY `fk_user_idx` (`user_id`),
  CONSTRAINT `fk_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemlog`
--

LOCK TABLES `systemlog` WRITE;
/*!40000 ALTER TABLE `systemlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_number` varchar(64) NOT NULL COMMENT '老师编号',
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  `id_card` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `native_place` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_number_UNIQUE` (`teacher_number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='老师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (7,'2015022','czq','女','','2018-04-30','广西','328863397@qq.com','15084978667','','12c82dcfe2c8faa463bdb76c61daa27a'),(8,'2015088','cyh','男','450902199609282957','2018-05-08','广西','328863397@qq.com','15084978667','信息院','821812ed8d7cf23b7a57737074d43f84');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT '333333' COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `type` int(11) DEFAULT '0' COMMENT '用户类型',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `headImage` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','aa7b043b818888f5868936482a6f08be',NULL,1,NULL,'http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb'),(8,'2015022','12c82dcfe2c8faa463bdb76c61daa27a','czq',2,NULL,'http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb'),(9,'2015088','821812ed8d7cf23b7a57737074d43f84','cyh',2,NULL,'http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb'),(10,'201508','ef5cf7cd9d5df30ebe9b6e2164de1c3e','mm',3,NULL,'http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb'),(11,'201505','d65a02ff6cda66dc78576b766b03c770','qqq',3,NULL,'http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb'),(12,'201508010520','468bea32fb2c7ea8ce107d8f4aa28885','chen',3,NULL,'http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_idx` (`user_id`),
  KEY `fk_role_idx` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,8,6),(3,9,6),(4,10,7),(5,11,7),(6,12,7);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-22 17:01:35

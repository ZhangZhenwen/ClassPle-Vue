/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : classple

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 13/12/2020 21:45:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad` (
  `ad_id` int NOT NULL AUTO_INCREMENT COMMENT '广告ID',
  `ad_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告标题',
  `ad_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告内容',
  `ad_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告链接',
  `is_shown` tinyint(1) NOT NULL COMMENT '是否展示',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ad_id`) USING BTREE,
  UNIQUE KEY `id` (`ad_id`) COMMENT '主键索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ad
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for asgn_file
-- ----------------------------
DROP TABLE IF EXISTS `asgn_file`;
CREATE TABLE `asgn_file` (
  `asgn_id` int NOT NULL COMMENT '作业ID',
  `file_id` int NOT NULL COMMENT '文件ID',
  PRIMARY KEY (`asgn_id`,`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asgn_file
-- ----------------------------
BEGIN;
INSERT INTO `asgn_file` VALUES (7, 2);
INSERT INTO `asgn_file` VALUES (7, 4);
INSERT INTO `asgn_file` VALUES (22, 14);
INSERT INTO `asgn_file` VALUES (22, 15);
INSERT INTO `asgn_file` VALUES (29, 25);
COMMIT;

-- ----------------------------
-- Table structure for assignment
-- ----------------------------
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
  `asgn_id` int NOT NULL AUTO_INCREMENT COMMENT '作业ID',
  `asgn_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业名称',
  `asgn_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作业简介',
  `asgn_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业类型',
  `released_date` datetime DEFAULT NULL COMMENT '发布日期',
  `deadline` datetime DEFAULT NULL COMMENT '截止日期',
  `max_score` int NOT NULL COMMENT '满分值',
  `is_repeat` tinyint NOT NULL COMMENT '是否差重',
  `repeat_rate` int DEFAULT NULL COMMENT '差重预警',
  `return_rate` int DEFAULT NULL COMMENT '打回值',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`asgn_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assignment
-- ----------------------------
BEGIN;
INSERT INTO `assignment` VALUES (1, '123', '', 'P', NULL, '2020-12-29 16:00:00', 123, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (2, '123', '', 'P', NULL, '2020-12-29 16:00:00', 123, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (3, '123', '', 'P', NULL, '2020-12-30 16:00:00', 123, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (4, '1233', '123', 'P', '2020-12-06 10:18:30', '2020-12-06 10:18:15', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (5, '321', '321321', 'P', '2020-12-06 10:25:30', '2020-12-06 10:24:56', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (6, '321', '321321', 'P', '2020-12-06 10:28:19', '2020-12-06 10:24:56', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (7, '123123', '123123', 'P', '2020-12-06 11:21:47', '2020-12-06 11:21:34', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (8, '123123', '21312312321', 'P', '2020-12-06 11:24:41', '2020-12-06 11:24:25', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (9, '123123', '12321321', 'P', '2020-12-06 13:06:11', '2020-12-06 13:05:34', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (10, 'ceshui', '12312312', 'P', '2020-12-06 13:08:33', '2020-12-06 13:08:18', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (11, 'test', 'setsets', 'P', '2020-12-06 13:10:15', '2020-12-06 13:09:53', 100, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (12, 'test', 'setsets', 'P', '2020-12-06 13:12:37', '2020-12-06 13:09:53', 100, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (13, 'test', 'setsets', 'P', '2020-12-06 13:13:01', '2020-12-06 13:09:53', 100, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (14, 'test', 'setsets', 'P', '2020-12-06 13:18:48', '2020-12-06 13:09:53', 100, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (15, '123', '123321', 'P', '2020-12-06 13:19:27', '2020-12-29 16:00:00', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (16, '123321', '2132131', 'P', '2020-12-06 13:21:01', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (17, '123321', '2132131', 'P', '2020-12-06 13:22:49', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (18, '123321', '2132131', 'P', '2020-12-06 13:23:20', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (19, '123321', '2132131', 'P', '2020-12-06 13:23:20', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (20, '123321', '2132131', 'P', '2020-12-06 13:24:09', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (21, '123321', '2132131', 'P', '2020-12-06 13:24:53', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (22, '123321', '2132131', 'P', '2020-12-06 13:26:26', '2020-12-06 13:20:46', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (23, '111', '12312312', 'P', '2020-12-07 15:54:16', '2020-12-07 15:54:11', 100, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (24, '测试发布作业', '随便写写', 'P', '2020-12-09 09:32:47', '2020-12-09 09:32:32', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (25, '测试上传', '测试上传文件', 'P', '2020-12-09 09:50:12', '2020-12-23 16:00:00', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (26, 'test', 'test', 'P', '2020-12-09 09:57:36', '2020-12-09 09:57:25', 100, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (27, 'testes', 'testestse', 'P', '2020-12-09 09:59:04', '2020-12-09 09:58:51', 100, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (28, 'testse', 'testsetes', 'P', '2020-12-09 10:10:07', '2020-12-08 16:00:00', 100, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `assignment` VALUES (29, 'test', 'te s t se t', 'P', '2020-12-09 10:10:34', '2020-12-09 10:10:22', 100, 1, 50, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cmt_id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cmt_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `crse_id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `crse_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `crse_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课堂名称',
  `introduction` varchar(255) DEFAULT NULL COMMENT '课堂简介',
  `crse_semester` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程学期',
  `crse_join_code` int NOT NULL COMMENT '加入课程是否需要信息',
  `max_member` int DEFAULT NULL COMMENT '最大成员数',
  `allow_drop_out` tinyint(1) NOT NULL COMMENT '允许退课',
  `is_file` tinyint(1) NOT NULL COMMENT '是否归档',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`crse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, '2VQ89W', '测试', '118230203、04', '测试课堂', '2020-2021 第一学期', 0, NULL, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `course` VALUES (2, 'O91VCA', '测试课堂', '118230203、04', '测试课堂', '2020-2021 第二学期', 0, NULL, 0, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `course` VALUES (3, '8BIAN0', '测试创建课堂', '11823020338', NULL, '2020-2021 第一学期', 1, NULL, 0, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `course` VALUES (4, 'BUEEWZ', '托尔斯泰', '特色图', NULL, '2020-2021 第二学期', 0, NULL, 0, 0, 0, '', NULL, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for crse_asgn
-- ----------------------------
DROP TABLE IF EXISTS `crse_asgn`;
CREATE TABLE `crse_asgn` (
  `crse_id` int NOT NULL,
  `asgn_id` int NOT NULL,
  PRIMARY KEY (`crse_id`,`asgn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crse_asgn
-- ----------------------------
BEGIN;
INSERT INTO `crse_asgn` VALUES (1, 23);
INSERT INTO `crse_asgn` VALUES (2, 3);
INSERT INTO `crse_asgn` VALUES (2, 4);
INSERT INTO `crse_asgn` VALUES (2, 5);
INSERT INTO `crse_asgn` VALUES (2, 6);
INSERT INTO `crse_asgn` VALUES (2, 7);
INSERT INTO `crse_asgn` VALUES (2, 8);
INSERT INTO `crse_asgn` VALUES (2, 9);
INSERT INTO `crse_asgn` VALUES (2, 10);
INSERT INTO `crse_asgn` VALUES (2, 11);
INSERT INTO `crse_asgn` VALUES (2, 12);
INSERT INTO `crse_asgn` VALUES (2, 13);
INSERT INTO `crse_asgn` VALUES (2, 14);
INSERT INTO `crse_asgn` VALUES (2, 15);
INSERT INTO `crse_asgn` VALUES (2, 16);
INSERT INTO `crse_asgn` VALUES (2, 17);
INSERT INTO `crse_asgn` VALUES (2, 18);
INSERT INTO `crse_asgn` VALUES (2, 19);
INSERT INTO `crse_asgn` VALUES (2, 20);
INSERT INTO `crse_asgn` VALUES (2, 21);
INSERT INTO `crse_asgn` VALUES (2, 22);
INSERT INTO `crse_asgn` VALUES (3, 24);
INSERT INTO `crse_asgn` VALUES (3, 25);
INSERT INTO `crse_asgn` VALUES (3, 26);
INSERT INTO `crse_asgn` VALUES (3, 27);
INSERT INTO `crse_asgn` VALUES (3, 28);
INSERT INTO `crse_asgn` VALUES (3, 29);
COMMIT;

-- ----------------------------
-- Table structure for crse_file
-- ----------------------------
DROP TABLE IF EXISTS `crse_file`;
CREATE TABLE `crse_file` (
  `crse_id` int NOT NULL COMMENT '课程ID',
  `file_id` int NOT NULL COMMENT '文件ID',
  PRIMARY KEY (`crse_id`,`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crse_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '文件表ID',
  `parent_id` int DEFAULT NULL COMMENT '父文件ID',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
  `file_size` double DEFAULT NULL COMMENT '文件大小',
  `downloadable` tinyint(1) NOT NULL COMMENT '是否可下载',
  `download_count` int DEFAULT NULL COMMENT '下载次数',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (1, NULL, '11823020338张振文实验二.docx', '/profile/upload/2020/11/23/eeded95a16e4950af74f5735654b334b.docx', 'file', 22382, 1, 7, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (2, NULL, '11823020338张振文实验二的副本.docx', '/profile/upload/2020/11/23/d348d14e720dae1b728a2e359da61096.docx', 'file', 27800, 1, 11, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (3, NULL, '11823020338张振文实验一.docx', '/profile/upload/2020/11/23/89600cab2b4315b73b2a1fb3c2093e0b.docx', 'file', 64358, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (4, NULL, '11823020338张振文实验二.docx', '/profile/upload/2020/11/23/11823020338张振文实验二.docx1173953525132800.docx', 'file', 22382, 1, 2, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (5, NULL, '11823020338张振文实验二的副本.docx', '/profile/upload/2020/11/23/11823020338张振文实验二的副本.docx1173964162855151.docx', 'file', 27800, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (6, NULL, '11823020338张振文实验一.docx', '/profile/upload/2020/11/23/11823020338张振文实验一.docx1173968867987102.docx', 'file', 64358, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (7, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/06/a70f47605b4150ac33db6e22b53bd3a0.txt', 'file', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (8, NULL, '智慧树刷课代码.rtf', '/profile/upload/2020/12/06/66a75cab75c76d088151ae27a9d5bb16.rtf', 'file', 2537, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (9, NULL, '396077.xls', '/profile/upload/2020/12/06/ae8813a838de6e3ac23836811f2da7de.xls', 'file', 36864, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (10, NULL, '396077.xls', '/profile/upload/2020/12/06/16a3e85fc34c2b7ef7507d335f5a3a4b.xls', 'file', 36864, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (11, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/06/9d896e84e867b88ed4f98c86ea853283.txt', 'file', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (12, NULL, '11823020338张振文实验一.docx', '/profile/upload/2020/12/06/93d9584366b62f9f0eb3182adbfc0ed3.docx', 'file', 64358, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (13, NULL, '11823020338张振文实验二.docx', '/profile/upload/2020/12/06/cc65e0b99cd013b711751929ff001005.docx', 'file', 22382, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (14, NULL, '11823020338张振文实验四.docx', '/profile/upload/2020/12/06/f1aa1ddde33ac59f0c5664f03460ad2d.docx', 'file', 13226, 1, 1, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (15, NULL, '11823020338张振文实验五.docx', '/profile/upload/2020/12/06/d7c17e3db4ee7b90a93f897fe7309e7e.docx', 'file', 42764, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (16, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/09/c831d849f2f02eea08d9d467eb171339.txt', 'text/plain', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (17, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/09/8da339cfefea29efa596c1e91634fbdb.txt', 'text/plain', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (18, NULL, '智慧树刷课代码.rtf', '/profile/upload/2020/12/09/1a70913e9a93587f586c8972982c209b.rtf', 'text/rtf', 2537, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (19, NULL, '功能分解法-1.pdf', '/profile/upload/2020/12/09/a61665f02c91aae5e3aabd2d72e4cd41.pdf', 'application/pdf', 145862, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (20, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/09/182bb2383e767be69b11458a6c9dd973.txt', 'text/plain', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (21, NULL, '单元测试框架和测试驱动开发C180513.pptx', '/profile/upload/2020/12/09/5644e14a7ba5e409d156a71ab7cf2623.pptx', 'application/octet-stream', 1044544, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (22, NULL, 'LR实验-张振文-11823020338.doc', '/profile/upload/2020/12/09/e4fc96a007afe23d94962c8f2c996c5d.doc', 'application/msword', 231424, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (23, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/09/68a6c0e48e03e708bd4603a05e14f3ef.txt', 'text/plain', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (24, NULL, '智慧树刷课代码.rtf', '/profile/upload/2020/12/09/d937ed5965e86be1a70e0901cba381a7.rtf', 'text/rtf', 2537, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (25, NULL, 'orthogonal array.txt', '/profile/upload/2020/12/09/f09d0690d4b378ed21ac55ce83f0452f.txt', 'text/plain', 1333324, 1, 0, 0, '', NULL, '', NULL, NULL);
INSERT INTO `file` VALUES (26, NULL, '智慧树刷课代码.rtf', '/profile/upload/2020/12/13/5f89c6fffe049c6c6cb52e9c0eafbc27.rtf', 'text/rtf', 2537, 1, 0, 0, '', '2020-12-13 13:15:41', '', NULL, NULL);
INSERT INTO `file` VALUES (27, NULL, '深度学习课程综合课程论文2020-2021-1.docx', '/profile/upload/2020/12/13/a57ad008a64902a94ea15813a463dc60.docx', 'application/octet-stream', 13019, 1, 0, 0, '', '2020-12-13 13:15:47', '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, '课程管理', 0, 1, '/course', NULL, 1, '', '0', '0', '', '#', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (2, '创建课程', 1, 0, '/course/create', NULL, 1, '', '0', '0', 'course:create', '#', '', NULL, '', NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for mooc
-- ----------------------------
DROP TABLE IF EXISTS `mooc`;
CREATE TABLE `mooc` (
  `mooc_id` int NOT NULL AUTO_INCREMENT COMMENT '慕课ID',
  `mooc_name` varchar(255) NOT NULL COMMENT '慕课名称',
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '慕课图标',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程简介',
  `outline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程大纲',
  `lecturer_name` varchar(255) NOT NULL COMMENT '主讲教师',
  `lecturer_school` varchar(255) NOT NULL COMMENT '教师学校',
  `lecturer_degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师学历',
  `lecturer_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师简介',
  `members_count` int DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`mooc_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mooc
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mooc_tag
-- ----------------------------
DROP TABLE IF EXISTS `mooc_tag`;
CREATE TABLE `mooc_tag` (
  `mooc_id` int NOT NULL COMMENT '慕课ID',
  `tag_id` int NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`mooc_id`,`tag_id`),
  KEY `tag` (`tag_id`),
  CONSTRAINT `mooc` FOREIGN KEY (`mooc_id`) REFERENCES `mooc` (`mooc_id`),
  CONSTRAINT `tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mooc_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `user_id` int NOT NULL COMMENT '通知对象ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知内容',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站路径',
  `is_read` varchar(255) DEFAULT NULL COMMENT '是否已读',
  `read_time` datetime DEFAULT NULL COMMENT '已读时间',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for preparation
-- ----------------------------
DROP TABLE IF EXISTS `preparation`;
CREATE TABLE `preparation` (
  `prep_id` int NOT NULL AUTO_INCREMENT COMMENT '备课ID',
  `name` varchar(255) NOT NULL COMMENT '备课区名称',
  `introduction` varchar(255) DEFAULT NULL COMMENT '备课区介绍',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`prep_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of preparation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色权限',
  `status` varchar(255) DEFAULT NULL COMMENT '角色状态',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'root', 'root', '0', 0, '', NULL, '', NULL, NULL);
INSERT INTO `role` VALUES (2, '老师', 'tch', '0', 0, '', NULL, '', NULL, NULL);
INSERT INTO `role` VALUES (3, '学生', 'stu', '0', 0, '', NULL, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名字',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int NOT NULL AUTO_INCREMENT COMMENT '作业ID',
  `asgn_id` int NOT NULL COMMENT '作业ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `score` int DEFAULT NULL COMMENT '得分',
  `repeat_rate` int NOT NULL COMMENT '差重值',
  `updated_count` int NOT NULL COMMENT '更新次数',
  `is_return` tinyint(1) DEFAULT NULL COMMENT '是否打回',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES (1, 3, 13, 100, 0, 14, 0, 0, NULL, '2020-12-01 18:00:00', NULL, '2020-12-13 12:56:22', NULL);
INSERT INTO `task` VALUES (2, 4, 13, 100, 0, 2, 0, 0, NULL, '2020-12-13 12:57:29', NULL, '2020-12-13 13:15:52', NULL);
COMMIT;

-- ----------------------------
-- Table structure for task_file
-- ----------------------------
DROP TABLE IF EXISTS `task_file`;
CREATE TABLE `task_file` (
  `task_id` int NOT NULL COMMENT '作业ID',
  `file_id` int NOT NULL COMMENT '文件ID',
  PRIMARY KEY (`task_id`,`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_file
-- ----------------------------
BEGIN;
INSERT INTO `task_file` VALUES (1, 2);
INSERT INTO `task_file` VALUES (1, 4);
INSERT INTO `task_file` VALUES (2, 26);
INSERT INTO `task_file` VALUES (2, 27);
COMMIT;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int NOT NULL AUTO_INCREMENT COMMENT '话题ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '话题标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '话题内容',
  `crse_id` int NOT NULL COMMENT '课程ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` varchar(1) DEFAULT NULL COMMENT '状态',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `enrolled_date` datetime DEFAULT NULL COMMENT '入学年月',
  `school_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学校编号',
  `institute` varchar(255) DEFAULT NULL COMMENT '学院',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学校',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '测试', '13075888888', 'ktp00122233', '$2a$10$f7mr.eMhG59hcVjfwPLah.Iw4KdHwcmbRHz8DzK8w12iPOOP501N2', '0', '13075808821@163.com', '2020-11-03 15:06:19', '11823020338', NULL, NULL, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (2, '管理员', '13000000000', 'admin', '$2a$10$f7mr.eMhG59hcVjfwPLah.Iw4KdHwcmbRHz8DzK8w12iPOOP501N2', '0', NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (3, '用户', '13012345678', 'ktp00122234', '$2a$10$f7mr.eMhG59hcVjfwPLah.Iw4KdHwcmbRHz8DzK8w12iPOOP501N2', '0', NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (12, '张三', '13000112233', 'ktp13000112233', '$2a$10$f7mr.eMhG59hcVjfwPLah.Iw4KdHwcmbRHz8DzK8w12iPOOP501N2', '0', NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (13, '李四', '13000112244', 'ktp13000112244', '$2a$10$23rZEqOdtPCZz5LgwnFYweF7vKQ9I3.u3i5FKOnJvUyI.SX3ok.NO', '0', NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (19, '张某', '11823020338', 'ktp11823020338', '$2a$10$13BwulIsT34La.T1NleG3edhB2oB0PaPewii5lsdmChYmQBYH38e2', '0', NULL, NULL, '', NULL, NULL, '重庆理工大学', '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (20, '王武', '13364032212', 'ktp13364032212', '$2a$10$SuTOpB.mSLn65QI6pY63f.S2iyKQXOd1Smk5HQhtbvFhagpgjHGiS', '0', NULL, NULL, '11823020300', NULL, NULL, '重庆理工大学', '', NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (21, '老六', '13364032222', 'ktp13364032222', '$2a$10$YRJ/zSUYZlgRqlExH6DDx.RsTFsHa4VpFqtB1itITo7t859eynO.y', NULL, NULL, NULL, '', NULL, NULL, '重庆理工大学', '', NULL, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_crse
-- ----------------------------
DROP TABLE IF EXISTS `user_crse`;
CREATE TABLE `user_crse` (
  `user_id` int NOT NULL COMMENT '用户ID',
  `crse_id` int NOT NULL COMMENT '课程ID',
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '在班级中的姓名',
  `school_no` varchar(20) DEFAULT NULL COMMENT '学号',
  `join_date` datetime NOT NULL COMMENT '加课时间',
  `out_date` datetime DEFAULT NULL COMMENT '退课时间',
  `sort_index` int NOT NULL COMMENT '排序下标',
  `is_drop_out` tinyint(1) NOT NULL COMMENT '是否退课',
  `is_file` tinyint(1) NOT NULL COMMENT '是否归档',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否退课',
  PRIMARY KEY (`user_id`,`crse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_crse
-- ----------------------------
BEGIN;
INSERT INTO `user_crse` VALUES (3, 2, 'tch', '用户', NULL, '2020-12-13 09:08:25', NULL, 1, 0, 0, 0);
INSERT INTO `user_crse` VALUES (12, 1, 'admin', '张三', NULL, '2020-12-07 15:52:52', NULL, 1, 0, 0, 0);
INSERT INTO `user_crse` VALUES (12, 2, 'admin', '张三', NULL, '2020-11-23 16:37:04', NULL, 1, 0, 0, 0);
INSERT INTO `user_crse` VALUES (12, 3, 'stu', '张三', NULL, '2020-12-13 13:38:42', NULL, 3, 0, 0, 0);
INSERT INTO `user_crse` VALUES (12, 4, 'admin', '张三', NULL, '2020-12-13 13:37:35', NULL, 3, 0, 0, 0);
INSERT INTO `user_crse` VALUES (13, 2, 'stu', '李四', '11823020338', '2020-12-06 14:41:08', NULL, 2, 0, 0, 0);
INSERT INTO `user_crse` VALUES (19, 2, 'tch', '张某', '', '2020-12-13 09:08:25', NULL, 2, 0, 0, 0);
INSERT INTO `user_crse` VALUES (19, 3, 'admin', '张某', '', '2020-12-09 09:25:36', NULL, 1, 0, 0, 0);
INSERT INTO `user_crse` VALUES (20, 2, 'stu', '王武', '11823020300', '2020-12-13 09:45:15', NULL, 5, 0, 0, 0);
INSERT INTO `user_crse` VALUES (20, 3, 'stu', '王武', '11823020300', '2020-12-13 09:56:34', NULL, 2, 0, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (12, 2);
INSERT INTO `user_role` VALUES (13, 3);
INSERT INTO `user_role` VALUES (14, 2);
INSERT INTO `user_role` VALUES (15, 2);
INSERT INTO `user_role` VALUES (16, 2);
INSERT INTO `user_role` VALUES (17, 2);
INSERT INTO `user_role` VALUES (18, 2);
INSERT INTO `user_role` VALUES (19, 2);
INSERT INTO `user_role` VALUES (20, 3);
INSERT INTO `user_role` VALUES (21, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

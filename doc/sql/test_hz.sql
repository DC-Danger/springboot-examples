/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : test_hz

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2018-06-25 00:29:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `comment_text` varchar(255) NOT NULL,
  `comment_date` datetime NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `weibo_id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '我的梦想是世界和平！', '2018-06-25 00:29:36', '1', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'jack', 'admin', '123456');

-- ----------------------------
-- Table structure for weibo
-- ----------------------------
DROP TABLE IF EXISTS `weibo`;
CREATE TABLE `weibo` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `weibo_text` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `user_id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weibo
-- ----------------------------
INSERT INTO `weibo` VALUES ('1', '新加坡特金会顺利举行', '2018-06-25 00:29:15', '1');

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : test_hz_city

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2018-06-28 10:34:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `province_id` int(6) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=441301 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('440300', '440000', '深圳市', '深圳，简称“深”，别称“鹏城”，是中国四大一线城市之一，广东省省辖市、计划单列市、副省级市、国家区域中心城市、超大城市，国务院定位的全国经济中心城市和国际化城市、国家创新型城市、国际科技产业创新中心、全球海洋中心城市、国际性综合交通枢纽，中国三大全国性金融中心之一。');
INSERT INTO `city` VALUES ('441300', '440000', '惠州市', '惠州，背靠罗浮山，南临大亚湾，境内东江蜿蜒100多公里，属珠江三角洲、粤港澳大湾区东岸。惠州毗邻深圳、香港，北连河源市，东接汕尾市，西邻东莞市和广州市，是珠江三角洲中心城市之一 [1]  。惠州辖惠城区、惠阳区、惠东县、博罗县、龙门县2区3县，并设有两个国家级开发区：大亚湾经济技术开发区、仲恺高新技术产业开发区。');

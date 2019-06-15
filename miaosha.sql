/*
Navicat MySQL Data Transfer

Source Server         : hello
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-06-15 20:14:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `description` varchar(511) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT '0',
  `img_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('9', 'iphone7', '7777.77', '玫瑰金，128G', '1', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=png-alpha&.v=1550795422347');
INSERT INTO `item` VALUES ('10', 'iphone8', '8888.88', '金色，128G', '0', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone8/gold/iphone8-gold-select-2018?wid=470&hei=556&fmt=png-alpha&.v=1550795416637');
INSERT INTO `item` VALUES ('11', 'iphoneXR', '9999.99', '蓝色，256G', '9', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone/xr/iphone-xr-blue-select-201809?wid=470&hei=556&fmt=png-alpha&.v=1551226036356');
INSERT INTO `item` VALUES ('12', 'iphoneXs', '10000.00', '银色，512G', '6', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone/xs/iphone-xs-silver-select-2018?wid=470&hei=556&fmt=png-alpha&.v=1550795411708');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES ('6', '998', '9');
INSERT INTO `item_stock` VALUES ('7', '1000', '10');
INSERT INTO `item_stock` VALUES ('8', '989', '11');
INSERT INTO `item_stock` VALUES ('9', '993', '12');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `amount` int(11) NOT NULL DEFAULT '0',
  `order_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `promo_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019061400000300', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500000400', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500000500', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500000600', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500000700', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500000800', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500000900', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500001000', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500001100', '5', '9', '7777.77', '1', '7777.77', '0');
INSERT INTO `order_info` VALUES ('2019061500001200', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500001300', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500001400', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500001500', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500001600', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500001700', '5', '12', '10000.00', '1', '10000.00', '0');
INSERT INTO `order_info` VALUES ('2019061500001800', '5', '11', '9999.99', '1', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019061500001900', '5', '11', '8888.88', '1', '8888.88', '1');

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_time` datetime NOT NULL DEFAULT '2019-06-02 00:00:00',
  `end_time` datetime NOT NULL DEFAULT '2019-06-02 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES ('1', 'iphoneXR秒杀', '2019-06-15 18:15:00', '2019-06-17 00:00:00', '11', '8888.88');

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT '0',
  `step` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info_id', '20', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1男，2女',
  `age` int(11) NOT NULL DEFAULT '0',
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT 'byphone,bywechat,byalipay',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tel_unique_index` (`tel`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'redsheep', '1', '23', '12345678900', 'byphone', '');
INSERT INTO `user_info` VALUES ('3', 'RedSheep', '1', '23', '12345678909', 'byphone', '');
INSERT INTO `user_info` VALUES ('5', 'AlanRedSheep', '1', '312', '12312312312', 'byphone', '');
INSERT INTO `user_info` VALUES ('6', 'AlanRedSheep', '1', '12', '12312312313', 'byphone', '');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', '626zhou626', '1');
INSERT INTO `user_password` VALUES ('3', '4QrcOUm6Wau+VuBX8g+IPg==', '3');
INSERT INTO `user_password` VALUES ('4', 'Qpf0SxOVUjUkWySXOZ16kw==', '5');
INSERT INTO `user_password` VALUES ('5', 'Qpf0SxOVUjUkWySXOZ16kw==', '6');

/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : auctiondb

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-10-29 11:31:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auction
-- ----------------------------
DROP TABLE IF EXISTS `auction`;
CREATE TABLE `auction` (
  `auctionId` int(11) NOT NULL AUTO_INCREMENT,
  `auctionName` varchar(50) NOT NULL,
  `auctionStartPrice` decimal(9,2) NOT NULL,
  `auctionUpset` decimal(9,2) NOT NULL,
  `auctionStartTime` datetime NOT NULL,
  `auctionEndTime` datetime NOT NULL,
  `auctionPic` varchar(50) NOT NULL,
  `auctionPicType` varchar(20) NOT NULL,
  `auctionDesc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`auctionId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auction
-- ----------------------------
INSERT INTO `auction` VALUES ('3', '旧电脑3', '1000.00', '1200.00', '2019-09-10 21:46:52', '2019-09-29 00:00:00', '电脑.jpg', 'image/jpeg', '看视频不卡');
INSERT INTO `auction` VALUES ('4', '旧电脑2', '100.00', '100.00', '2017-04-20 00:00:00', '2017-04-30 00:00:00', 'book_04.gif', 'image/gif', 'very good');
INSERT INTO `auction` VALUES ('5', '旧桌子', '200.00', '200.00', '2017-04-20 00:00:00', '2017-04-30 00:00:00', 'images/33232242.jpg', 'jpg', 'very good');
INSERT INTO `auction` VALUES ('9', '旧桌子3', '200.00', '200.00', '2017-04-20 00:00:00', '2017-04-30 00:00:00', 'book_09.gif', 'image/gif', 'very good');
INSERT INTO `auction` VALUES ('12', '二手花瓶', '90.00', '90.00', '2017-04-17 00:00:00', '2017-04-28 00:00:00', 'ad20.jpg', 'jpg', 'sfsfs');
INSERT INTO `auction` VALUES ('13', '旧碗', '56.00', '56.00', '2017-04-23 00:00:00', '2017-04-30 00:00:00', 'ad20.jpg', 'jpg', 'sss');
INSERT INTO `auction` VALUES ('14', '显示器', '1222.00', '1222.00', '2017-04-24 00:00:00', '2017-04-30 00:00:00', 'ad20.jpg', 'jpg', 'dsds');
INSERT INTO `auction` VALUES ('15', '旧花瓶100', '500.00', '500.00', '2017-05-22 00:00:00', '2017-05-31 00:00:00', 'ad20.jpg', 'image/jpeg', 'sfsf');
INSERT INTO `auction` VALUES ('16', '旧拖鞋', '10.00', '10.00', '2017-06-14 00:00:00', '2017-06-30 00:00:00', 'book_08.gif', 'image/gif', '标杆');
INSERT INTO `auction` VALUES ('19', '牛角杯888', '100.00', '100.00', '2017-07-15 00:00:00', '2017-07-29 00:00:00', 'book_06.gif', 'gif', 'very good');
INSERT INTO `auction` VALUES ('20', 'AK74', '100.00', '100.00', '2017-07-22 00:00:00', '2017-07-31 00:00:00', 'book_01.gif', 'gif', '好');
INSERT INTO `auction` VALUES ('21', '风景', '20000.00', '25000.00', '2019-09-14 00:00:00', '2019-09-21 00:00:00', '1.jpg', 'image/jpeg', '美如画');
INSERT INTO `auction` VALUES ('22', '智能手机', '800.00', '850.00', '2019-09-10 12:06:32', '2019-09-17 06:00:00', '苹果手机.jpg', 'image/jpeg', '可以打王者');
INSERT INTO `auction` VALUES ('23', '钢笔', '60.00', '65.00', '2019-09-11 08:15:42', '2019-09-29 00:00:00', '钢笔.jpg', 'image/jpeg', '非常好写');
INSERT INTO `auction` VALUES ('24', '学习网站', '50000.00', '60000.00', '2019-09-15 23:21:43', '2019-09-30 00:00:00', '图标.png', 'image/png', '学霸必备,学习的好帮手');
INSERT INTO `auction` VALUES ('25', '键盘', '30.00', '30.00', '2019-09-19 08:51:51', '2019-09-21 00:00:00', '键盘.jpg', 'image/jpeg', '键盘侠必备');
INSERT INTO `auction` VALUES ('26', '飞机票', '888.00', '888.00', '2019-09-19 08:54:25', '2019-09-28 00:00:00', '飞往北京飞机票.jpg', 'image/jpeg', '国庆观看天安门大阅兵，我爱我的祖国！！！');
INSERT INTO `auction` VALUES ('27', '《演员的自我修养》', '200.00', '250.00', '2019-09-24 00:00:00', '2020-09-01 00:00:00', '书籍.jpg', 'image/jpeg', '星爷代言，你值得拥有');
INSERT INTO `auction` VALUES ('29', '坦克（附送炮弹30发）', '800000.00', '800000.00', '2019-09-19 09:04:17', '2019-09-30 00:00:00', 't-84坦克.jpg', 'image/jpeg', '不怕堵车，非常好用');
INSERT INTO `auction` VALUES ('30', '海报', '80.00', '80.00', '2019-10-23 21:39:37', '2019-10-27 00:00:00', '四.jpg', 'image/jpeg', '高清海报');
INSERT INTO `auction` VALUES ('31', '王者荣耀', '20.00', '20.00', '2019-10-23 21:49:18', '2019-10-31 00:00:00', '王者荣耀四美.jpg', 'image/jpeg', '这是官网的正版图片');

-- ----------------------------
-- Table structure for auctionrecord
-- ----------------------------
DROP TABLE IF EXISTS `auctionrecord`;
CREATE TABLE `auctionrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `auctionId` int(11) NOT NULL,
  `auctionTime` datetime NOT NULL,
  `auctionPrice` decimal(9,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rec_REF_user` (`userId`),
  KEY `FK_rec_REF_AUC` (`auctionId`),
  CONSTRAINT `FK_rec_REF_AUC` FOREIGN KEY (`auctionId`) REFERENCES `auction` (`auctionId`),
  CONSTRAINT `FK_rec_REF_user` FOREIGN KEY (`userId`) REFERENCES `auctionuser` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auctionrecord
-- ----------------------------
INSERT INTO `auctionrecord` VALUES ('7', '5', '15', '2017-05-22 10:15:03', '600.00');
INSERT INTO `auctionrecord` VALUES ('8', '5', '15', '2017-05-25 15:21:29', '700.00');
INSERT INTO `auctionrecord` VALUES ('9', '5', '16', '2017-06-14 20:45:57', '19.00');
INSERT INTO `auctionrecord` VALUES ('10', '5', '20', '2017-07-18 15:33:10', '110.00');
INSERT INTO `auctionrecord` VALUES ('11', '5', '20', '2017-07-18 15:33:45', '120.00');
INSERT INTO `auctionrecord` VALUES ('12', '5', '3', '2019-09-13 00:54:01', '2000.00');
INSERT INTO `auctionrecord` VALUES ('13', '5', '23', '2019-09-13 00:54:35', '80.00');
INSERT INTO `auctionrecord` VALUES ('14', '5', '21', '2019-09-13 01:01:06', '51.00');
INSERT INTO `auctionrecord` VALUES ('15', '5', '21', '2019-09-13 01:01:16', '90.00');
INSERT INTO `auctionrecord` VALUES ('16', '10', '24', '2019-09-16 09:28:07', '80000.00');
INSERT INTO `auctionrecord` VALUES ('17', '10', '22', '2019-09-16 09:28:31', '850.00');
INSERT INTO `auctionrecord` VALUES ('18', '10', '21', '2019-09-16 09:28:53', '25000.00');
INSERT INTO `auctionrecord` VALUES ('19', '10', '22', '2019-09-17 14:03:38', '900.00');
INSERT INTO `auctionrecord` VALUES ('20', '12', '29', '2019-09-19 10:34:06', '1000000.00');
INSERT INTO `auctionrecord` VALUES ('21', '12', '26', '2019-09-19 10:34:51', '900.00');
INSERT INTO `auctionrecord` VALUES ('22', '12', '24', '2019-09-19 10:35:58', '80001.00');
INSERT INTO `auctionrecord` VALUES ('23', '12', '23', '2019-09-19 10:37:41', '81.00');
INSERT INTO `auctionrecord` VALUES ('24', '10', '27', '2019-09-20 08:37:36', '300.00');

-- ----------------------------
-- Table structure for auctionuser
-- ----------------------------
DROP TABLE IF EXISTS `auctionuser`;
CREATE TABLE `auctionuser` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表',
  `userName` varchar(20) NOT NULL,
  `userPassword` varchar(20) NOT NULL,
  `userCardNo` varchar(20) DEFAULT NULL,
  `userTel` varchar(20) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  `userPostNumber` varchar(6) DEFAULT NULL,
  `userIsadmin` int(11) DEFAULT '0',
  `userQuestion` varchar(100) DEFAULT NULL,
  `userAnswer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auctionuser
-- ----------------------------
INSERT INTO `auctionuser` VALUES ('1', '旺财', '8888', null, null, null, null, '0', null, null);
INSERT INTO `auctionuser` VALUES ('4', 'Mike', '00000', null, null, null, null, '1', null, null);
INSERT INTO `auctionuser` VALUES ('5', 'test1', '999', '123456789012345678', '8787878', '广东省广州', '600600', '0', '', '');
INSERT INTO `auctionuser` VALUES ('7', '张三', '0000', null, null, null, null, '1', null, null);
INSERT INTO `auctionuser` VALUES ('8', '李四', '10000', '123456789012345678', null, null, '511500', '0', null, null);
INSERT INTO `auctionuser` VALUES ('9', 'Jack', '99999', '12345646456464', '99999999', 'gangdong', '599599', '0', '', '');
INSERT INTO `auctionuser` VALUES ('10', 'kk', '11', '123456789012345', '12234', 'sadfsaf', '23443', '0', '', '');
INSERT INTO `auctionuser` VALUES ('11', 'zyh', '000000', '123456789987654321', '110', 'foshan', '515300', '0', null, null);
INSERT INTO `auctionuser` VALUES ('12', '广东小靓仔', '00000', '123456789987654321', '1234567', '仙溪湖畔', '515300', '0', null, null);

-- ----------------------------
-- Table structure for stu
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu
-- ----------------------------
INSERT INTO `stu` VALUES ('1', '张三', '85', '语文');
INSERT INTO `stu` VALUES ('2', '张三', '79', '数学');
INSERT INTO `stu` VALUES ('3', '李四', '86', '语文');
INSERT INTO `stu` VALUES ('4', '李四', '75', '数学');
INSERT INTO `stu` VALUES ('5', '王五', '85', '语文');
INSERT INTO `stu` VALUES ('6', '王五', '85', '数学');
INSERT INTO `stu` VALUES ('7', '王五', '85', '英语');

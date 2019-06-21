/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50532
 Source Host           : localhost:3306
 Source Schema         : onlineshoppingmall

 Target Server Type    : MySQL
 Target Server Version : 50532
 File Encoding         : 65001

 Date: 16/05/2019 01:52:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of adminuser
-- ----------------------------
INSERT INTO `adminuser` VALUES (1, 'qq123456', 'F889275C4C33AF04DEE6619B55F131CA2CA24E56D79C824F5AB5BA14');
INSERT INTO `adminuser` VALUES (2, 'admin', 'E7C497C16C9EC45658DC537B70BB6725B8798C1640CD1DAB8604F6FA');
INSERT INTO `adminuser` VALUES (3, 'test', '96154D051DAC7D7D76AB61D43828F6ABF929D4A163DC514A7FEE1B11');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '女装男装');
INSERT INTO `category` VALUES (2, '鞋靴箱包');
INSERT INTO `category` VALUES (3, '运动户外');
INSERT INTO `category` VALUES (4, '珠宝配饰');
INSERT INTO `category` VALUES (5, '手机数码');
INSERT INTO `category` VALUES (6, '家电办公');
INSERT INTO `category` VALUES (7, '护肤彩妆');
INSERT INTO `category` VALUES (10, '家居饰品');
INSERT INTO `category` VALUES (11, '测试');

-- ----------------------------
-- Table structure for categorysecond
-- ----------------------------
DROP TABLE IF EXISTS `categorysecond`;
CREATE TABLE `categorysecond`  (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`) USING BTREE,
  INDEX `FK936FCAF21DB1FD15`(`cid`) USING BTREE,
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of categorysecond
-- ----------------------------
INSERT INTO `categorysecond` VALUES (1, '潮流女装', 1);
INSERT INTO `categorysecond` VALUES (2, '初冬羽绒', 1);
INSERT INTO `categorysecond` VALUES (3, '毛呢大衣', 1);
INSERT INTO `categorysecond` VALUES (4, '温暖毛衣', 1);
INSERT INTO `categorysecond` VALUES (5, '精选男装', 1);
INSERT INTO `categorysecond` VALUES (6, '冬季外套', 1);
INSERT INTO `categorysecond` VALUES (7, '羽绒服', 1);
INSERT INTO `categorysecond` VALUES (9, '女鞋', 2);
INSERT INTO `categorysecond` VALUES (10, '短靴', 2);
INSERT INTO `categorysecond` VALUES (11, '男鞋', 2);
INSERT INTO `categorysecond` VALUES (12, '女包', 2);
INSERT INTO `categorysecond` VALUES (13, '男包', 2);
INSERT INTO `categorysecond` VALUES (14, '服饰配件', 2);
INSERT INTO `categorysecond` VALUES (15, '运动鞋', 3);
INSERT INTO `categorysecond` VALUES (16, '运动服', 3);
INSERT INTO `categorysecond` VALUES (17, '户外运动', 3);
INSERT INTO `categorysecond` VALUES (18, '健身装备', 3);
INSERT INTO `categorysecond` VALUES (19, '骑行装备', 3);
INSERT INTO `categorysecond` VALUES (20, '珠宝首饰', 4);
INSERT INTO `categorysecond` VALUES (21, '时尚饰品', 4);
INSERT INTO `categorysecond` VALUES (22, '品质手表', 4);
INSERT INTO `categorysecond` VALUES (23, '眼镜配饰', 4);
INSERT INTO `categorysecond` VALUES (24, '手机', 5);
INSERT INTO `categorysecond` VALUES (25, '平板', 5);
INSERT INTO `categorysecond` VALUES (26, '电脑', 5);
INSERT INTO `categorysecond` VALUES (27, '相机', 5);
INSERT INTO `categorysecond` VALUES (28, '大家电', 6);
INSERT INTO `categorysecond` VALUES (29, '厨房电器', 6);
INSERT INTO `categorysecond` VALUES (30, '生活电器', 6);
INSERT INTO `categorysecond` VALUES (31, '个户电器', 6);
INSERT INTO `categorysecond` VALUES (32, '办公耗材', 6);
INSERT INTO `categorysecond` VALUES (33, '美容护肤', 7);
INSERT INTO `categorysecond` VALUES (34, '强效保养', 7);
INSERT INTO `categorysecond` VALUES (35, '超值彩妆', 7);
INSERT INTO `categorysecond` VALUES (36, '换季保养', 7);
INSERT INTO `categorysecond` VALUES (40, '组合柜222', 10);
INSERT INTO `categorysecond` VALUES (41, '测试2', 11);
INSERT INTO `categorysecond` VALUES (42, '测试', 11);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `FKE8B2AB6166C01961`(`oid`) USING BTREE,
  INDEX `FKE8B2AB6171DB7AE4`(`pid`) USING BTREE,
  INDEX `FKE8B2AB6140ACF87A`(`oid`) USING BTREE,
  CONSTRAINT `FKE8B2AB6140ACF87A` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKE8B2AB6171DB7AE4` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (142, 1, 198, 21, 2019000001);
INSERT INTO `orderitem` VALUES (143, 2, 456, 1, 2019032909);
INSERT INTO `orderitem` VALUES (144, 2, 1776, 80, 2019032910);
INSERT INTO `orderitem` VALUES (145, 1, 83, 72, 2019032911);
INSERT INTO `orderitem` VALUES (146, 2, 344, 2, 2019032911);
INSERT INTO `orderitem` VALUES (147, 1, 83, 72, 2019032912);
INSERT INTO `orderitem` VALUES (148, 1, 186, 32, 2019032913);
INSERT INTO `orderitem` VALUES (149, 4, 912, 1, 2019032914);
INSERT INTO `orderitem` VALUES (150, 1, 119, 6, 2019032914);
INSERT INTO `orderitem` VALUES (151, 3, 357, 6, 2019032917);
INSERT INTO `orderitem` VALUES (152, 1, 228, 1, 2019032917);
INSERT INTO `orderitem` VALUES (153, 1, 119, 3, 2019032917);
INSERT INTO `orderitem` VALUES (154, 1, 228, 1, 2019032920);
INSERT INTO `orderitem` VALUES (155, 1, 228, 1, 2019032922);
INSERT INTO `orderitem` VALUES (156, 1, 888, 80, 2019032923);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `addr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `FKC3DF62E5AA3D9C7`(`uid`) USING BTREE,
  CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2019032924 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2019000001, 198, '2019-04-27 22:59:04', 1, '杨廷战', '15527981002', '湖北武汉东西湖区', 16);
INSERT INTO `orders` VALUES (2019032909, 456, '2019-04-27 23:02:13', 1, '杨廷战测试账号', '15527981002', '湖北武汉东西湖区测试地址', 16);
INSERT INTO `orders` VALUES (2019032910, 1776, '2019-05-09 10:02:00', 4, '杨廷战测试账号', '15527981002', '湖北武汉东西湖区测试地址', 25);
INSERT INTO `orders` VALUES (2019032911, 427, '2019-05-03 10:02:48', 2, '测试账号', '15527981002', '测试地址', 25);
INSERT INTO `orders` VALUES (2019032912, 83, '2019-05-13 15:16:56', 2, '杨廷战', '15527981002', '湖北武汉东西湖区', 16);
INSERT INTO `orders` VALUES (2019032913, 186, '2019-05-14 15:21:42', 2, '杨廷战', '15527981002', '湖北武汉东西湖区', 16);
INSERT INTO `orders` VALUES (2019032914, 1031, '2019-05-15 16:15:31', 4, 'test', '15527981002', '湖北武汉东西湖区测试地址', 27);
INSERT INTO `orders` VALUES (2019032917, 704, '2019-05-16 01:34:58', 2, 'test', '15527981002', '湖北武汉东西湖区测试地址', 27);
INSERT INTO `orders` VALUES (2019032920, 228, '2019-05-16 01:42:41', 1, NULL, NULL, NULL, 27);
INSERT INTO `orders` VALUES (2019032922, 228, '2019-05-16 01:46:57', 1, NULL, NULL, NULL, 27);
INSERT INTO `orders` VALUES (2019032923, 888, '2019-05-16 01:50:17', 1, NULL, NULL, NULL, 27);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `pdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `csid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `FKED8DCCEFB9B74E02`(`csid`) USING BTREE,
  CONSTRAINT `FKED8DCCEFB9B74E02` FOREIGN KEY (`csid`) REFERENCES `categorysecond` (`csid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '韩版连帽加厚毛衣女外套', 558, 228, 'products/1/cs10001.jpg', 89, '双11限量200件，拍完下架，加车享优惠，早下手早发货。。秋冬个性中长款毛衣，美丽和温度同在！限量供应，拒绝撞衫！迫于纱线和人工在不断上涨的双重压力下，产品涨价在即！少量现货出售中，手快有，手慢等哦，赶紧抢哦，绝对高大上。', 1, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (2, '女装立领长袖拼接PU皮毛呢外套', 436, 172, 'products/1/cs10002.jpg', 98, '【现在拍下并支付定金，即可获得双12当天10元无门槛优惠券一张。注：只限有预付定金款~优惠券统一在12月11号发放】 毛呢外套 整洁干练的长款版型 整个肩部给予皮绒拼接 与毛呢一起撑起品质感 立领 长袖 肩部往下做流行加层拼接 一粒扣收合门襟 特意做的夹棉里层（袖里无） 不必再畏惧冷冽寒风', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (3, '韩版女装翻领羔绒夹棉格子毛呢外套', 238, 119, 'products/1/cs10003.jpg', 100, '外套款。 称为棉衣也不为过 或者因为它的表层毛呢　称呼为毛呢外套 ｙａｎｇ　羔绒位于翻领及袖口 从视觉着手　带来温暖无限 夹棉里衬（袖里也有）再次提升御寒功能 流行元素上　选择红黑格纹理　大气而又经典 金属拉链开叉　插袋自不会少', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (4, '韩版女装翻领羔绒夹棉格子毛呢外套', 238, 119, 'products/1/cs10004.jpg', 100, '外套款。 称为棉衣也不为过 或者因为它的表层毛呢　称呼为毛呢外套 ｙａｎｇ　羔绒位于翻领及袖口 从视觉着手　带来温暖无限 夹棉里衬（袖里也有）再次提升御寒功能 流行元素上　选择红黑格纹理　大气而又经典 金属拉链开叉　插袋自不会少', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (5, '韩版女装翻领羔绒夹棉格子毛呢外套', 238, 119, 'products/1/cs10005.jpg', 100, '外套款。 称为棉衣也不为过 或者因为它的表层毛呢　称呼为毛呢外套 ｙａｎｇ　羔绒位于翻领及袖口 从视觉着手　带来温暖无限 夹棉里衬（袖里也有）再次提升御寒功能 流行元素上　选择红黑格纹理　大气而又经典 金属拉链开叉　插袋自不会少', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (6, '冬装韩版女装翻领羔绒夹棉格子毛呢外套', 238, 119, 'products/1/cs10006.jpg', 96, '外套款。 称为棉衣也不为过 或者因为它的表层毛呢　称呼为毛呢外套 ｙａｎｇ　羔绒位于翻领及袖口 从视觉着手　带来温暖无限 夹棉里衬（袖里也有）再次提升御寒功能 流行元素上　选择红黑格纹理　大气而又经典 金属拉链开叉　插袋自不会少', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (7, '新款优雅奢华毛领白鸭绒轻薄羽绒服', 1120, 590, 'products/1/cs10007.jpg', 100, '秋冬热卖款，今日特惠！库存有限，卖完即止！喜欢的赶紧出手哦！', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (8, '秋冬季毛呢外套女中长款圆领小香风呢子大衣', 672, 336, 'products/1/cs10008.jpg', 99, '【双12预售】双12提前开抢，11月24日—12月11日抢先付预付款33.6元，12月12日付剩余尾款，先付先发货，提前引爆双12！！！买就【送】双十二10元无门槛优惠券！！！商家【赠】运费险！！！', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (9, '女装貉子毛大衣 时尚修身长袖淑女毛呢外套', 238, 119, 'products/1/cs10009.jpg', 100, '秋冬热卖款，今日特惠！库存有限，卖完即止！喜欢的赶紧出手哦！', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (10, '修身显瘦淑女针织长袖打底连衣裙女', 356, 158, 'products/1/cs10010.jpg', 100, '【1212万能盛典预售抢先购,早买早便宜！】定金15.80元，预售价158.00元，双12价涨价至178.00元!', 0, '2019-03-24 15:56:00', 1);
INSERT INTO `product` VALUES (11, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20001.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (12, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20002.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (13, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20003.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (14, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20004.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (15, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20005.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (16, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20006.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (17, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20007.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (18, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20008.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (19, '整貂皮大衣外套中长款收腰立领长袖进口真皮草裘皮', 19800, 9900, 'products/1/cs20009.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货 【售后保障】终身免费保养维修', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (20, '中长款貂皮大衣整貂女装', 17900, 7290, 'products/1/cs20010.jpg', 100, '采用100%进口小母貂 毛皮结实柔软毛峰更有光泽 保暖效果极强。　 【正品保证】【售后保障】我们承诺100%整皮水貂 假一罚十 支持验货 无理由退换货', 0, '2019-03-24 15:56:00', 2);
INSERT INTO `product` VALUES (21, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30001.png', 97, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 1, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (22, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30002.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (23, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30003.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (24, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30004.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (25, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30005.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (26, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30006.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (27, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30007.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (28, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30008.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (29, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30009.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (30, '韩版毛呢外套女韩范秋冬装厚中长款冬季呢子', 598, 198, 'products/1/cs30010.png', 100, '今年韩国首尔爆款，超高端定制羊毛呢大衣，版型超美，不挑身材，不挑年龄，上身非常漂亮哦，适合任何场合，这个秋冬MM必备款哦。几乎人手一件，美丽时尚的你怎么能少了这件呢，现秋冬预热加十一到来，只需99元包邮，只限今天，今天过后马上涨价，早买早划算哦~', 0, '2019-03-24 15:56:00', 3);
INSERT INTO `product` VALUES (31, '打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40001.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (32, '打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40002.png', 99, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (33, '打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40003.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (34, '性感时尚 酷感黑色小圆领露肩修身长袖针织衫', 387, 186, 'products/1/cs40004.jpg', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (35, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40005.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 1, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (36, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40006.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (37, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40007.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (38, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40008.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (39, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40009.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (40, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40010.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 0, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (41, '韩版黑色打底衫加厚修身羊毛衫女装羊绒衫', 387, 186, 'products/1/cs40011.png', 100, '本产品 不起球 不掉色 不缩水 是一款贴身必备的厚款羊绒衫 质量保证支持退换', 1, '2019-03-24 15:56:00', 4);
INSERT INTO `product` VALUES (42, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50001.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 1, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (43, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50002.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 0, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (44, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50003.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 1, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (45, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50004.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 0, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (46, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50005.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 0, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (47, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50006.png', 98, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 1, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (48, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50007.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 0, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (49, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50008.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 0, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (50, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50009.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 0, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (51, '冬装外套棉衣立领修身商务大码男装潮牌上衣', 899, 358, 'products/1/cs50010.png', 100, '【型男卖点简介】该款凭借其独特的设计、精选的面料和一致的时尚理念，以质感都市，充满时尚感的设计风格， 简约毛呢大衣，修身不变形，不起球，国际大牌一样的面料！面料成分：77.8%聚酯纤维+22.2%粘纤 秋冬简约修身防静电呢风衣外套', 1, '2019-03-24 15:56:00', 5);
INSERT INTO `product` VALUES (52, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60001.png', 99, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (53, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60002.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 1, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (54, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60003.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (55, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60004.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (56, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60005.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (57, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60006.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 1, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (58, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60007.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (59, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60008.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (60, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60009.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 1, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (61, '商务修身羊毛呢子风衣 中长款呢大衣外套', 997, 299, 'products/1/cs60010.png', 100, '冬装新品首发！大牌做工，顶级羊毛呢面料，穿着舒适保暖，冬季潮男必备品！新品预售价299元，预计涨到398！不要拿市场上那些低劣的毛呢大衣相比较，一分价钱一分货哦！买到就是赚到，支持7天无理由退换货，尺码不对，可享受免费换货！', 0, '2019-03-24 15:56:00', 6);
INSERT INTO `product` VALUES (62, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70001.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (63, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70002.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (64, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70003.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 0, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (65, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70004.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (66, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70005.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 0, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (67, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70006.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (68, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70007.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (69, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70008.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (70, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70009.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 0, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (71, '韩版修身羽绒服加厚保暖可脱卸帽', 198, 83, 'products/1/cs70010.png', 100, '羽绒棉服，保暖性比普通棉服高出3倍，价格合适，比羽绒服便宜3倍，超高性价比～～现亏本冲业绩，活动过后涨价为198元哦，亲们速抢～～！！', 1, '2019-03-24 15:56:00', 7);
INSERT INTO `product` VALUES (72, '女鞋', 198, 83, 'products/1/nvxie.jpg', 97, '韩版女鞋...', 0, '2019-03-24 15:56:00', 9);
INSERT INTO `product` VALUES (73, '短靴1', 299, 198, 'products/1/duanxue1.png', 101, '女款短靴...', 1, '2019-03-24 15:56:00', 10);
INSERT INTO `product` VALUES (74, '女款短靴2', 200, 125, 'products/1/duanxue2.png', 100, '女款短靴', 1, '2019-03-24 15:56:00', 10);
INSERT INTO `product` VALUES (75, '女款短靴3', 200, 125, 'products/1/duanxue3.png', 100, '女款短靴', 1, '2019-03-24 15:56:00', 10);
INSERT INTO `product` VALUES (76, '女款短靴4', 200, 125, 'products/1/duanxue4.png', 100, '女款短靴', 1, '2019-03-24 15:56:00', 10);
INSERT INTO `product` VALUES (79, 'Thinkpad', 5999, 5099, 'products/1/dn2.jpg', 100, 'Thinkpad', 1, '2019-03-24 15:56:00', 26);
INSERT INTO `product` VALUES (80, '测试商品', 999, 888, 'products/9.jpg', 84, '测试商品', 1, '2019-04-26 14:31:11', 1);
INSERT INTO `product` VALUES (81, '测试商123123', 123, 123, 'products/6.jpg', 121, '123', 0, '2019-04-26 14:31:03', 41);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (15, '李四', '3E87877CEB13235C0FB007073D10259866B3BF6D7D997D942BB54B90', '李四', NULL, 'test@shop.com', '15527981002', '中国', 1, '');
INSERT INTO `user` VALUES (16, 'yangtz', '595B2F1FE8D48244F2FFB620F3DDC330E8A80D4F7CF813D814B19EDD', '杨廷战', 'male', 'test@shop.com', '15527981002', '湖北武汉东西湖区', 1, NULL);
INSERT INTO `user` VALUES (18, 'test', '5CA5395D5838A6AE91A6695BF527F79B3475656A993FD24F69786D09', '测试1', NULL, 'test@shop.com', '', '', 1, '');
INSERT INTO `user` VALUES (19, 'test2', 'E1BC6991750B73CC506E73F2DC5FAD24CAC369545379995B8498BC86', '测试2', NULL, 'test@shop.com', '15527981002', '湖北武汉东西湖区测试地址', 1, '');
INSERT INTO `user` VALUES (23, 'test3', '9966B829E6BF21153B441EBCFF07B36CABB852B46703160193FB6D89', '测试3', NULL, 'test@shop.com', '15527981002', '湖北武汉东西湖区测试地址', 1, '');
INSERT INTO `user` VALUES (24, 'test4', 'D71F6367B3A937DF3D4DF5C3F47D72A79550D025288EC6F4E23E21E4', '测试4', 'female', 'test@shop.com', '15527981002', '湖北武汉东西湖区测试地址', 1, NULL);
INSERT INTO `user` VALUES (25, '测试', 'F147F099CDF1AB24BB625C88BDEC2C144955EC5BA94839D879E80573', '杨廷战测试账号', 'male', 'test@shop.com', '15527981002', '湖北武汉东西湖区测试地址', 1, NULL);
INSERT INTO `user` VALUES (27, 'test55', '259AF62CB9FD8EA92097BC704CB4C831D96C44019DCC83373B91F9F5', 'test', NULL, 'test@shop.com', '15527981002', '湖北武汉东西湖区测试地址', 1, '');

SET FOREIGN_KEY_CHECKS = 1;

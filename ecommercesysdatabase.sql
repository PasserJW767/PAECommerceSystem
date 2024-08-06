/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : ecommercesysdatabase

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 05/05/2021 15:20:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cropsinfo
-- ----------------------------
DROP TABLE IF EXISTS `cropsinfo`;
CREATE TABLE `cropsinfo`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `unit` double(255, 1) NOT NULL,
  `sales` double(255, 1) NOT NULL,
  `repertory` double(255, 1) NOT NULL,
  `detailInfo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seller` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pictureLocation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `addingTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `sellerForeign`(`seller`) USING BTREE,
  CONSTRAINT `sellerForeign` FOREIGN KEY (`seller`) REFERENCES `userinfo` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cropsinfo
-- ----------------------------
INSERT INTO `cropsinfo` VALUES (1, '鸭梨', 14.0, 3255.9, 200.0, '黄憨憨20210503100209.txt', '黄憨憨', '/cropsImg/黄憨憨20210107105216.gif', '2021-05-03 11:35:55');
INSERT INTO `cropsinfo` VALUES (2, '猕猴桃', 11.0, 5231.6, 4234.1, '黄憨憨20210503100232.txt', '黄憨憨', '/cropsImg/黄憨憨20201230005301.gif', '2021-05-03 11:36:39');
INSERT INTO `cropsinfo` VALUES (3, '速冻草莓', 5.8, 1336.4, 3313.4, '景天广茂20210503100245.txt', '景天广茂', '/cropsImg/景天广茂20201231075318.gif', '2021-05-03 11:37:13');
INSERT INTO `cropsinfo` VALUES (4, '柠檬片', 3.7, 601.5, 309.5, 'HSX20210503100259.txt', 'HSX', '/cropsImg/HSX20210101105333.gif', '2021-05-03 11:37:41');
INSERT INTO `cropsinfo` VALUES (5, '有机葡萄', 26.0, 3287.1, 230.2, 'BNC223320210503100305.txt', 'BNC2233', '/cropsImg/BNC223320210102125412.gif', '2021-05-03 11:38:30');
INSERT INTO `cropsinfo` VALUES (6, '甜瓜', 12.0, 3221.3, 616.4, 'Blys12320210109175419.txt', 'Blys123', '/cropsImg/Blys12320210109175419.gif', '2021-05-03 11:33:03');
INSERT INTO `cropsinfo` VALUES (7, '新疆大枣', 26.6, 4286.1, 620.6, 'kuer6620210503100326.txt', 'kuer66', '/cropsImg/kuer6620210108015422.gif', '2021-05-03 11:39:19');
INSERT INTO `cropsinfo` VALUES (8, '香酥鱼皮花生', 10.9, 640.5, 231.7, 'ligenqi20210503100337.txt', 'ligenqi', '/cropsImg/ligenqi20210107065431.gif', '2021-05-03 11:39:59');
INSERT INTO `cropsinfo` VALUES (9, '特产圣女果', 16.0, 3576.6, 143.6, 'ymf278220210503100347.txt', 'ymf2782', '/cropsImg/ymf278220210106055437.gif', '2021-05-03 11:40:27');
INSERT INTO `cropsinfo` VALUES (10, '哈密瓜', 18.7, 3170.1, 3473.5, 'XHLss20210503100354.txt', 'XHLss', '/cropsImg/XHLss20210105075445.gif', '2021-05-03 11:41:28');
INSERT INTO `cropsinfo` VALUES (17, '螺蛳粉', 11.0, 0.0, 220.0, '黄憨憨20210503170506.txt', '黄憨憨', '/cropsImg/黄憨憨20210503170506.gif', '2021-05-03 17:05:07');

-- ----------------------------
-- Table structure for useridentifyid
-- ----------------------------
DROP TABLE IF EXISTS `useridentifyid`;
CREATE TABLE `useridentifyid`  (
  `ID` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `realName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idCardFront` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idCardBehind` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of useridentifyid
-- ----------------------------
INSERT INTO `useridentifyid` VALUES ('450104200011112044', '阿克里', '/userIdentify/asdd412202105051052581.gif', '/userIdentify/asdd412202105051052582.gif');
INSERT INTO `useridentifyid` VALUES ('622630188809093274', '黄大锤', '/userIdentify/黄憨憨202105051217011.gif', '/userIdentify/黄憨憨202105051217012.gif');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userPwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identityCard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('asdd412', '女', 'asdd123456.', '13482722233', '上海静安', '普通用户', '450104200011112044');
INSERT INTO `userinfo` VALUES ('asdd423', '女', 'asdd123456.', '13333333333', '北京市大兴区', '普通用户', '231423199011112222');
INSERT INTO `userinfo` VALUES ('asdd4asdf', '女', 'asdd123456.', '13482722233', '上海静安', '普通用户', '223543199212053333');
INSERT INTO `userinfo` VALUES ('Blys123', '男', 'Woshibly', '15555555555', '浙江杭州', '农户', '966666196606072222');
INSERT INTO `userinfo` VALUES ('BNC2233', '女', 'Woshibnc', '11111111111', '山东济南', '农户', '822888196409092222');
INSERT INTO `userinfo` VALUES ('glutxx001as', '男', '645654', '16723746298', '广东潮汕', '普通用户', '622630188809090099');
INSERT INTO `userinfo` VALUES ('HSX', '男', 'Woshihsx', '12222222222', '河北邯郸', '农户', '722222198706066666');
INSERT INTO `userinfo` VALUES ('kuer66', '男', 'Woshikuer', '16666666666', '新疆乌鲁木齐', '农户', '466666199901011111');
INSERT INTO `userinfo` VALUES ('ligenqi', '男', 'Woshilgq', '12222222222', '贵州贵阳', '农户', '333333199911013333');
INSERT INTO `userinfo` VALUES ('wangzhazha', '女', 'wangzhazha!!', '18807890192', '甘肃省兰州市', '普通用户', '422433199011060030');
INSERT INTO `userinfo` VALUES ('woshi', '女', 'woshiwoshi!', '15199872635', '甘肃省陇南市', '普通用户', '622630197802020753');
INSERT INTO `userinfo` VALUES ('XHLss', '男', 'Wobuzhidao', '66666444444', '新疆克拉玛依', '农户', '315664198612292222');
INSERT INTO `userinfo` VALUES ('ymf2782', '女', 'Woshiymf', '17777777777', '陕西西安', '农户', '666666196810292204');
INSERT INTO `userinfo` VALUES ('景天广茂', '女', 'Woshijtgm', '13333333333', '广西壮族自治区', '农户', '234564200003042044');
INSERT INTO `userinfo` VALUES ('黄憨憨', '男', 'Woshihhh', '13333333333', '上海静安', '农户', '622630188809093274');

-- ----------------------------
-- Table structure for userorder
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `cropId` int(255) NOT NULL,
  `cropName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tradeUnit` double(255, 2) NOT NULL,
  `shopAmount` double(255, 2) NOT NULL,
  `shopAllValue` double(255, 2) NOT NULL,
  `buyer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `orderCreateDate` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO `userorder` VALUES (1, 1, '鸭梨', 13.30, 1.00, 13.30, '黄憨憨', '2021-01-12 11:27:22');
INSERT INTO `userorder` VALUES (2, 5, '有机葡萄', 26.00, 1.00, 26.00, '黄憨憨', '2021-01-12 12:07:41');
INSERT INTO `userorder` VALUES (3, 1, '鸭梨', 13.30, 1.00, 13.30, 'glutxx001as', '2021-01-12 14:43:03');
INSERT INTO `userorder` VALUES (4, 1, '鸭梨', 13.30, 1.00, 13.30, '黄憨憨', '2021-01-12 14:41:54');
INSERT INTO `userorder` VALUES (5, 1, '鸭梨', 13.30, 6.20, 82.46, 'glutxx001as', '2021-01-12 14:43:06');
INSERT INTO `userorder` VALUES (6, 7, '新疆大枣', 26.60, 2.50, 66.50, '黄憨憨', '2021-01-12 14:41:58');
INSERT INTO `userorder` VALUES (7, 7, '新疆大枣', 26.60, 4.00, 106.40, '黄憨憨', '2021-01-12 14:41:59');
INSERT INTO `userorder` VALUES (8, 10, '哈密瓜', 18.70, 4.00, 74.80, '黄憨憨', '2021-01-12 14:42:00');
INSERT INTO `userorder` VALUES (9, 9, '特产圣女果', 16.00, 1.00, 16.00, 'glutxx001as', '2021-01-12 14:42:01');
INSERT INTO `userorder` VALUES (10, 4, '柠檬片', 3.70, 1.00, 3.70, 'glutxx001as', '2021-01-12 14:42:03');
INSERT INTO `userorder` VALUES (11, 4, '柠檬片', 3.70, 1.00, 3.70, 'glutxx001as', '2021-01-12 14:42:08');
INSERT INTO `userorder` VALUES (12, 4, '柠檬片', 3.70, 1.00, 3.70, 'glutxx001as', '2021-01-12 14:42:09');
INSERT INTO `userorder` VALUES (13, 5, '有机葡萄', 26.00, 1.00, 26.00, 'glutxx001as', '2021-01-12 14:42:10');
INSERT INTO `userorder` VALUES (14, 5, '有机葡萄', 26.00, 1.00, 26.00, 'glutxx001as', '2021-01-12 14:42:11');
INSERT INTO `userorder` VALUES (15, 5, '有机葡萄', 26.00, 1.00, 26.00, 'glutxx001as', '2021-01-12 14:42:12');
INSERT INTO `userorder` VALUES (16, 4, '柠檬片', 3.70, 1.00, 3.70, '黄憨憨', '2021-01-12 14:42:09');
INSERT INTO `userorder` VALUES (52, 9, '特产圣女果', 16.00, 1.00, 16.00, '黄憨憨', '2021-05-04 19:50:54');
INSERT INTO `userorder` VALUES (53, 7, '新疆大枣', 26.60, 1.00, 26.60, '黄憨憨', '2021-05-04 19:53:11');
INSERT INTO `userorder` VALUES (54, 9, '特产圣女果', 16.00, 2.00, 32.00, '黄憨憨', '2021-05-04 19:53:55');
INSERT INTO `userorder` VALUES (55, 8, '香酥鱼皮花生', 10.90, 1.00, 10.90, '黄憨憨', '2021-05-04 19:54:46');
INSERT INTO `userorder` VALUES (56, 8, '香酥鱼皮花生', 10.90, 2.00, 21.80, '黄憨憨', '2021-05-04 19:56:10');
INSERT INTO `userorder` VALUES (57, 5, '有机葡萄', 26.00, 1.00, 26.00, '黄憨憨', '2021-05-04 20:38:43');
INSERT INTO `userorder` VALUES (58, 5, '有机葡萄', 26.00, 1.00, 26.00, '黄憨憨', '2021-05-04 20:41:10');
INSERT INTO `userorder` VALUES (59, 10, '哈密瓜', 18.70, 1.00, 18.70, '黄憨憨', '2021-05-04 20:48:55');
INSERT INTO `userorder` VALUES (60, 3, '速冻草莓', 5.80, 3.00, 17.40, '黄憨憨', '2021-05-04 20:50:08');
INSERT INTO `userorder` VALUES (61, 3, '速冻草莓', 5.80, 3.00, 17.40, '黄憨憨', '2021-05-04 20:52:48');
INSERT INTO `userorder` VALUES (62, 3, '速冻草莓', 5.80, 3.00, 17.40, '黄憨憨', '2021-05-04 20:53:23');
INSERT INTO `userorder` VALUES (63, 3, '速冻草莓', 5.80, 3.00, 17.40, '黄憨憨', '2021-05-04 20:53:50');
INSERT INTO `userorder` VALUES (64, 7, '新疆大枣', 26.60, 3.00, 79.80, 'wangzhazha', '2021-05-04 21:08:06');
INSERT INTO `userorder` VALUES (65, 5, '有机葡萄', 26.00, 1.00, 26.00, 'wangzhazha', '2021-05-04 21:11:08');
INSERT INTO `userorder` VALUES (66, 5, '有机葡萄', 26.00, 1.00, 26.00, 'wangzhazha', '2021-05-04 21:12:00');

-- ----------------------------
-- Table structure for usershopcart
-- ----------------------------
DROP TABLE IF EXISTS `usershopcart`;
CREATE TABLE `usershopcart`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `cropId` int(11) NOT NULL,
  `cropName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cropUnit` double(255, 2) NOT NULL,
  `cosumerAmount` double(255, 2) NOT NULL,
  `cosumerPrice` double(255, 2) NOT NULL,
  `buyer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `addTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usershopcart
-- ----------------------------
INSERT INTO `usershopcart` VALUES (5, 6, '甜瓜', 12.00, 1.00, 12.00, '黄憨憨', '2021-01-14 09:28:19');
INSERT INTO `usershopcart` VALUES (6, 10, '哈密瓜', 18.70, 1.00, 18.70, '黄憨憨', '2021-01-14 09:33:31');
INSERT INTO `usershopcart` VALUES (8, 9, '特产圣女果', 16.00, 3.00, 48.00, '黄憨憨', '2021-05-04 17:56:17');
INSERT INTO `usershopcart` VALUES (9, 9, '特产圣女果', 16.00, 3.00, 48.00, '黄憨憨', '2021-05-04 17:57:22');
INSERT INTO `usershopcart` VALUES (10, 17, '螺蛳粉', 11.00, 2.00, 22.00, '黄憨憨', '2021-05-04 17:57:54');
INSERT INTO `usershopcart` VALUES (12, 8, '香酥鱼皮花生', 10.90, 2.00, 21.80, 'glutxx001as', '2021-05-04 18:01:20');
INSERT INTO `usershopcart` VALUES (13, 5, '有机葡萄', 26.00, 2.00, 52.00, 'glutxx001as', '2021-05-04 18:05:13');
INSERT INTO `usershopcart` VALUES (14, 6, '甜瓜', 12.00, 1.00, 12.00, 'glutxx001as', '2021-05-04 18:06:05');
INSERT INTO `usershopcart` VALUES (16, 3, '速冻草莓', 5.80, 5.00, 29.00, '黄憨憨', '2021-05-04 18:09:19');

-- ----------------------------
-- Table structure for userwaiting
-- ----------------------------
DROP TABLE IF EXISTS `userwaiting`;
CREATE TABLE `userwaiting`  (
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userPwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identityCard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userwaiting
-- ----------------------------
INSERT INTO `userwaiting` VALUES ('glutxx001', '男', 'asdfghj', '16798463456', '广西桂林', '商户', '647895342520145246');
INSERT INTO `userwaiting` VALUES ('glutxxxxy', '男', 'Woshiglut', '16798465375', '广东潮汕', '商户', '321542687795865234');
INSERT INTO `userwaiting` VALUES ('Nini', '女', 'Woshinini', '18706669945', '甘肃陇南', '商户', '654234522252489534');
INSERT INTO `userwaiting` VALUES ('姜hh', '女', '520wyf114', '12234567904', '北街', '商户', '622630299819980009');
INSERT INTO `userwaiting` VALUES ('姜二傻傻', '女', '520wyf112', '12234567902', '北街', '商户', '622630199819980019');
INSERT INTO `userwaiting` VALUES ('武hh', '女', '520wyf115', '12234567905', '南街', '商户', '622640199819980009');
INSERT INTO `userwaiting` VALUES ('王二傻傻', '女', '520wyf110', '12234567901', '北街', '商户', '622630199819980019');
INSERT INTO `userwaiting` VALUES ('黄二傻傻', '男', '520wyf113', '12234567903', '北街', '商户', '622630199819980019');

SET FOREIGN_KEY_CHECKS = 1;

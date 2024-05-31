/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 192.168.4.111:3306
 Source Schema         : db2024

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 31/05/2024 22:14:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_pay`;
CREATE TABLE `t_pay`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pay_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '支付流水号',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单流水号',
  `user_id` int(10) NOT NULL COMMENT '用户账单id',
  `amount` decimal(8, 2) NOT NULL COMMENT '交易金额',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标志，默认0不删除，1删除',
  `crate_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付交易表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pay
-- ----------------------------
INSERT INTO `t_pay` VALUES (1, 'pay00111902', '20240522002', 2, 219.32, 0, '2024-05-24 03:34:29', '2024-05-24 03:34:29');
INSERT INTO `t_pay` VALUES (2, 'pay00012999', '202405220012', 2, 10.32, 0, '2024-05-24 08:28:15', '2024-05-24 08:28:36');

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `t_pay` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pay_no` varchar(50) NOT NULL COMMENT '支付流水号',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单流水号',
  `user_id` int(10) NOT NULL COMMENT '用户账单id',
  `amount` decimal(8,2) NOT NULL COMMENT '交易金额',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `crate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付交易表';
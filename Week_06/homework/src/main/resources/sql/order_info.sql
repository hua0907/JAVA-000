CREATE TABLE `order_info`
(
  `id`                int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code`              varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '无' COMMENT '订单流水号',
  `amount`            bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单总金额（单位：分）',
  `order_status`      tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态（0：未支付，1：支付成功，2：支付失败，3：待退款，4：已取消，5：已完成）',
  `address`           varchar(100)                    NOT NULL DEFAULT '无' COMMENT '收货地址',
  `user_id`           int(20) NOT NULL DEFAULT '0' COMMENT '关联用户id',
  `order_details_id`  int(20) NOT NULL DEFAULT '0' COMMENT '关联订单详情id',
  `is_deleted`        tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time`       timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `update_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';


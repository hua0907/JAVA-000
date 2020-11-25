CREATE TABLE `delivery`
(
  `id`                int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id`          int(20) unsigned NOT NULL DEFAULT '0' COMMENT '关联订单id',
  `delivery_status`   tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态（0：未配送，1：正在配送，2：已签收，3未签收，4：取消配送）',
  `delivery_user_id`  int(20) unsigned NOT NULL DEFAULT '0' COMMENT '配送人id',
  `address`           varchar(100) NOT NULL DEFAULT '无' COMMENT '当前所在地',
  `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `update_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配送信息表';


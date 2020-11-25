CREATE TABLE `order_details`
(
  `id`                int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `good_id`           int(20) NOT NULL DEFAULT '0' COMMENT '关联商品id',
  `good_name`         varchar(50) NOT NULL DEFAULT '无' COMMENT '商品名称',
  `good_price`        int(20) NOT NULL DEFAULT '0' COMMENT '商品价格',
  `good_number`       int(20) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time`       timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `update_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';


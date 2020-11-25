CREATE TABLE `goods`
(
  `id`                int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name`              varchar(50)  NOT NULL DEFAULT '无' COMMENT '商品名称',
  `picture`           varchar(255) NOT NULL DEFAULT '无' COMMENT '商品图片',
  `good_status`       tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态（0：下架，1：上架）',
  `price`             int(20) unsigned NOT NULL DEFAULT '0' COMMENT '价格（单位：分）',
  `stock`             int(20) unsigned NOT NULL DEFAULT '0' COMMENT '库存',
  `used_stock`        int(20) unsigned NOT NULL DEFAULT '0' COMMENT '预占库存',
  `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `update_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';


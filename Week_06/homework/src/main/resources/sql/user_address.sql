CREATE TABLE `user_address`
(
  `id`                int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id`           int(20) unsigned NOT NULL DEFAULT '0' COMMENT '关联用户id',
  `province`          varchar(30)           DEFAULT '无' COMMENT '省',
  `city`              varchar(30)           DEFAULT '无' COMMENT '市',
  `area`              varchar(255)          DEFAULT '无' COMMENT '区',
  `street`            varchar(30)           DEFAULT '无' COMMENT '街道',
  `address`           varchar(100) NOT NULL DEFAULT '无' COMMENT '详细收货地址',
  `is_default`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认地址（0：非默认，1：默认地址）',
  `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `update_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户地址表';


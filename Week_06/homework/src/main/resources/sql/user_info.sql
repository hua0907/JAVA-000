CREATE TABLE `user_info`
(
  `id`                int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username`          varchar(50)  NOT NULL DEFAULT '无' COMMENT '用户名',
  `password`          varchar(100) NOT NULL DEFAULT '无' COMMENT '密码',
  `phone`             varchar(20)  NOT NULL DEFAULT '无' COMMENT '手机号',
  `id_card`           varchar(20)  NOT NULL DEFAULT '无' COMMENT '身份证号',
  `sex`               tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别（0：未知，1：男，2：女）',
  `salt`              varchar(50)  NOT NULL DEFAULT '无' COMMENT '密码的盐值',
  `is_locked`         tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定（0：未锁定，1：锁定）',
  `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `update_time_stamp` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


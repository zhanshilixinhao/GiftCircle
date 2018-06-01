
/*==============================================================*/
/* Table: user_base                                              */
/*==============================================================*/
drop table if exists `user_base`;
create table `user_base`(
`id`int  ( 10 ) not null auto_increment comment'用户id',
`password`varchar(64) default null comment'密码',
`account` varchar ( 15 ) default null comment '用户账号',
`phone`varchar (16) default null comment'电话',
`avatar` varchar (200) default null comment'头像',
`nickname` varchar (180) default null comment'昵称',
`type` tinyint(4) default null comment'账号类型，1-手机，2-QQ,3-微信，4-微博',
`status` tinyint(4) UNSIGNED default null comment'状态，1-正常，2-禁用',
`last_ip` VARCHAR ( 32 ) COMMENT '上次登录ip',
`last_clien` VARCHAR ( 40 ) COMMENT '上次登录客户端',
`created` datetime COMMENT '创建时间',
`updated`datetime COMMENT '更新时间',
 PRIMARY KEY ( id ),
 UNIQUE KEY `account` ( `account` ),
 UNIQUE KEY `phone` ( `phone` )
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COMMENT = '用户信息';

/*==============================================================*/
/* Table: user_third                                              */
/*==============================================================*/
drop table if exists `user_third`;
create table `user_third`(
`id`int (11) not null auto_increment comment'用户id',
`open_id`varchar (128)default null comment'第三方ID',
`phone`varchar (16) default null comment'电话',
`type`tinyint(4)default null comment'账号类型，1-手机，2-QQ,3-微信，4-微博',
`created` datetime COMMENT '创建时间',
`updated`datetime COMMENT '更新时间',
 PRIMARY KEY ( id ),
 UNIQUE KEY `open_id` ( `open_id`,`type` )
)ENGINE = INNODB CHARACTER
SET = utf8mb4 COMMENT = '第三方信息';











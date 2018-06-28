
drop table if exists app_user;

drop table if exists article;

drop table if exists bank_dict;

drop table if exists brand;

drop table if exists charge_order;

drop table if exists district;



drop table if exists item_category;

drop table if exists item_comment;

drop table if exists item_detail;

drop table if exists item_feature;

drop table if exists item_sku;

drop table if exists item_sku_value;

drop table if exists item_value;

drop table if exists merchant;

drop table if exists payment_info;

drop table if exists shipping;

drop table if exists store;

drop table if exists third_account;

drop table if exists user_bankcard;

drop table if exists user_favorite;

drop table if exists user_withdraw;

drop table if exists virtual_item;

drop table if exists virtual_item_category;

drop table if exists wallet;
drop table if exists wallet;

drop table if exists event;


/*==============================================================*/
/* Table: app_user                                              */
/*==============================================================*/
create table app_user
(
  id        int auto_increment not null,
  account   varchar(15) comment '账号',
  password  varchar(128) comment '密码',
  phone     varchar(15) comment '手机号码吗',
  avatar    varchar(250) comment '头像',
  nickname  varchar(32) comment '昵称',
  age       int comment '年龄',
  gender    tinyint comment '性别 1 男 2 女',
  signature varchar(50) comment '签名',
  district  varchar(20) comment '地区',
  status    tinyint comment '状态  1 正常 2 禁用',
  sent_pwd  varchar(6) comment '赠送密码',
  wxid      varchar(32) comment '微信账号',
  created   datetime comment '创建按时间',
  updated   datetime comment '更新时间',
  primary key (`id`),
  unique key `account` (`account`),
  unique key `phone` (`phone`)
)
  engine = innodb, character set = utf8mb4
  comment '用户信息';

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
  id       int(10) auto_increment not null,
  title    varchar(128) comment '文章标题',
  cover    varchar(255) comment '封面图',
  summary  varchar(255) comment '简介',
  detail   text comment '文章详情富文本',
  admin_id int comment '创建人',
  status   tinyint comment '1 正常 2 不可用 3 已删除',
  created  datetime,
  updated  datetime,
  primary key id (id),
  key admin_id (admin_id)
)
  engine = innodb, character set = utf8mb4
  comment '文章';




/*==============================================================*/
/* Table: bank_dict                                             */
/*==============================================================*/
create table bank_dict
(
  id        int auto_increment not null
  comment '表id',
  bank_name varchar(64) comment '银行名称',
  logo      varchar(255) comment '银行logo',
  status    tinyint comment '状态，1-正常，2-禁用',
  created   datetime comment '创建时间',
  updated   datetime comment '更新时间',
  primary key id (id)
)
  engine = innodb, character set = utf8mb4 comment '银行';

/*==============================================================*/
/* Table: brand                                                 */
/*==============================================================*/
create table brand
(
  id      int auto_increment not null,
  name    varchar(64) comment '品牌名称',
  logo    varchar(255),
  created datetime,
  updated datetime,
  primary key id (id)
)
  engine = innodb, character set = utf8mb4 comment '品牌';

/*==============================================================*/
/* Table: charge_order                                          */
/*==============================================================*/
create table charge_order
(
  id       int auto_increment not null
  comment '表id',
  user_id  int comment '用户id',
  amount   decimal(18, 2) comment '充值金额',
  order_no bigint comment '订单号',
  status   tinyint comment '充值状态，1-未支付，2-已支付',
  pay_way  tinyint comment '支付方式，1-微信，2-支付宝，3-银行卡',
  created  datetime comment '创建时间',
  updated  datetime comment '更新时间',
  primary key id (id),
  unique KEY order_no (`order_no`),
  key user_id (user_id)
)
  engine = innodb, character set = utf8mb4 comment '充值订单';

/*==============================================================*/
/* Table: district                                              */
/*==============================================================*/
create table district
(
  adcode   int auto_increment not null
  comment '行政区编码',
  p_adcode int comment '父级行政区编码',
  name     varchar(64) comment '行政区名称',
  type     tinyint comment '行政区类型 1 省/自治区 2 神奇的 4 城市 5 行政区 ',
  level    varchar(15) comment '行政区级别',
  created  datetime comment '创建时间',
  updated  datetime,
  primary key id (adcode),
  key adcode (adcode)
)
  engine = innodb, character set = utf8mb4 comment '全国行政区';

/*==============================================================*/
/* Table: item                                                  */
/*==============================================================*/
drop table if exists item;
create table item
(
  id          int auto_increment not null,
  title       varchar(200) comment '标题',
  price       decimal(18, 2) comment '价格',
  sales       int comment '销量',
  stock       int comment '库存',
  description varchar(200) comment '属性描述',
  re_gender   tinyint comment '推荐性别 0 默认 1 男  2 女',
  re_max_age  tinyint comment '推荐最大年龄',
  re_age_min  tinyint comment '推荐最小年龄',
  store_id    int comment '店铺id',
  category_id datetime comment '分类id',
  cover       varchar(255) comment '封面图',
  pictures    varchar(2000) comment '商品相册',
  choiceness  tinyint default 0 comment '精选，0-否，1-是',
  hot         tinyint default 0 comment '热门，0-否，1-是',
  status      tinyint comment '1 正常 2 禁用 3 已删除',
  is_audit    tinyint default 0
  comment '1 审核通过 2 审核失败',
  brand_id    int comment '品牌id',
  created     datetime,
  updated     datetime,
  primary key id (id),
  key category_id (`category_id`),
  key brand_id (brand_id)
)
  engine = innodb, character set = utf8mb4 comment '商品信息';

/*==============================================================*/
/* Table: item_category                                         */
/*==============================================================*/
create table item_category
(
  id      int auto_increment not null,
  pid     int comment '父级分类id',
  name    varchar(64) comment '分类名称',
  status  tinyint comment '1 正常  2 禁用 ',
  sort    int comment '排序值',
  icon    varchar(200) comment '图标',
  created datetime,
  updated datetime,
  primary key id (id),
  key pid (pid)
)
  engine = innodb, character set = utf8mb4 comment '商品分类';


/*==============================================================*/
/* Table: item_event                                        */
/*==============================================================*/
drop table if exists item_event;
create table item_event
(
  id      int auto_increment not null,
  event_id     int comment '事件id',
  item_id    int comment '商品id',
  created datetime,
  updated datetime,
  primary key id (id),
  key event_id (event_id),
  key item_id (item_id)

)
  engine = innodb, character set = utf8mb4 comment '商品事件';


/*==============================================================*/
/* Table: item_comment                                          */
/*==============================================================*/
create table item_comment
(
  id       int auto_increment not null,
  item_id  int comment '商品id',
  order_no bigint comment '订单号',
  user_id  int comment '用户id',
  star     int default 0
  comment '评分',
  content  varchar(2000) comment '评价文字内容',
  pictures varchar(2000) comment '评价图品集合 json数组',
  created  datetime,
  updated  datetime,
  primary key id (id),
  key order_no (order_no),
  key item_id (item_id),
  key user_id (user_id)
)
  engine = innodb, character set = utf8mb4 comment '商品评价';


/*==============================================================*/
/* Table: item_detail                                           */
/*==============================================================*/
create table item_detail
(
  item_id int auto_increment not null,
  detail  text comment '详情富文本',
  primary key id (item_id)
)
  engine = innodb, character set = utf8mb4 comment '商品详情';


/*==============================================================*/
/* Table: item_feature                                          */
/*==============================================================*/
create table item_feature
(
  id        int auto_increment not null,
  name      varchar(64) comment '属性名称',
  sort      int comment '排序值',
  `values`  varchar(500) comment '属性值集合',
  is_select tinyint comment '1 可选 2 不可选',
  status    tinyint comment '1 正常  2 无效',
  created   datetime,
  updated   datetime,
  primary key id (id)
)
  engine = innodb, character set = utf8mb4 comment '商品属性';

/*==============================================================*/
/* Table: item_sku                                              */
/*==============================================================*/
create table item_sku
(
  id       int auto_increment not null,
  item_id  int comment '商品id',
  title    varchar(255) comment 'sku标题',
  cover    varchar(255) comment '封面图',
  pictures varchar(2000) comment '相册图集合',
  stock    int comment 'sku库存',
  price    decimal(18, 2),
  sales    int comment '销量',
  status   tinyint comment '状态 1 正常 2 禁用',
  created  datetime,
  updated  datetime,
  primary key id (id),
  key item_id (item_id)
)
  engine = innodb, character set = utf8mb4 comment '商品最小销售单元';

/*==============================================================*/
/* Table: item_sku_value                                        */
/*==============================================================*/
create table item_sku_value
(
  id       int auto_increment not null,
  sku_id   int comment 'sku id',
  value_id int comment '属性值id',
  status   tinyint comment '1 正常',
  created  datetime comment '从',
  updated  datetime,
  primary key id (id),
  key sku_id (sku_id),
  key (value_id)
)
  engine = innodb, character set = utf8mb4 comment 'sku和属性关联表';

/*==============================================================*/
/* Table: item_value                                            */
/*==============================================================*/
create table item_value
(
  id         int auto_increment not null,
  item_id    int comment '商品id',
  feature_id int comment '属性id',
  value      varchar(64) comment '属性值',
  sort       int comment '排序值',
  created    datetime,
  updated    datetime,
  primary key id (id),
  key item_id (item_id),
  key feature_id (feature_id)
)
  engine = innodb, character set = utf8mb4 comment '商品属性';

/*==============================================================*/
/* Table: merchant                                              */
/*==============================================================*/
create table merchant
(
  id              int auto_increment not null,
  name            varchar(200) comment '企业名称',
  user_id         int comment '用户id',
  admin_id        int comment '分配的管理员id',
  address         varchar(200) comment '地址',
  registration_no varchar(32) comment '企业注册号',
  legal_person    varchar(32) comment '法人代表',
  phone           varchar(15) comment '联系电话',
  license_pic     varchar(255) comment '营业执照照片',
  other_pics      varchar(1000) comment '其他证件图片',
  status          tinyint comment '1 默认 2 审核中 3 审核通过 4 审核失败',
  created         datetime comment '创建时间',
  updated         datetime,
  primary key id (id),
  key user_id (user_id),
  key admin_id (admin_id)
)
  engine = innodb, character set = utf8mb4 comment '商家';

/*==============================================================*/
/* Table: payment_info                                          */
/*==============================================================*/
create table payment_info
(
  id              int auto_increment not null
  comment '表id',
  user_id         int comment '用户id',
  type            tinyint comment '1-充值订单',
  order_no        bigint comment '订单号',
  total_fee       decimal(18, 2) comment '交易金额',
  pay_platform    tinyint comment '支付平台，1-微信，2-支付宝，3-银行卡',
  platform_number varchar(100) comment '支付流水号',
  platform_status varchar(20) comment '支付状态，平台交易成功状态标识:支付返回码：9000(支付宝支付成功)',
  seller          varchar(100) comment '卖家账号',
  buyer           varchar(100) comment '买家账号',
  created         datetime comment '创建时间',
  updated         datetime comment '更新时间',
  primary key id (id),
  unique key order_no (order_no)
)  engine = innodb, character set = utf8mb4 comment '支付信息记录';


/*==============================================================*/
/* Table: shipping                                              */
/*==============================================================*/
create table shipping
(
  id             int auto_increment not null,
  user_id        int comment '用户id',
  consignee_name varchar(32) comment '收货人',
  address        varchar(128) comment '收获地址地区',
  phone          varchar(15) comment '联系电话',
  address_detail varchar(128) comment '详细地址',
  adcode         int comment '收货地址的行政却编码',
  status         tinyint comment '1 正常 2 默认地址 3 删除的地址',
  created        datetime comment '创建时间',
  updated        datetime,
  primary key id (id),
  key user_id (user_id)
)
  engine = innodb, character set = utf8mb4 comment '收货地址';

/*==============================================================*/
/* Table: store                                                 */
/*==============================================================*/
create table store
(
  id          int auto_increment not null,
  merchant_id int comment '商家id',
  name        varchar(200) comment '店铺名称',
  address     varchar(200) comment '店铺地址',
  phone       varchar(15) comment '电话',
  created     datetime,
  updated     datetime,
  primary key id (id),
  key merchant_id (merchant_id)
)  engine = innodb, character set = utf8mb4 comment '店铺';

/*==============================================================*/
/* Table: third_account                                         */
/*==============================================================*/
create table third_account
(
  id       integer auto_increment not null
  comment '表id',
  open_id  varchar(64) comment '第三方账号唯一标识',
  phone    varchar(32) comment '用户账号',
  type     tinyint comment '第三方账号类型，1-App微信，2-微信小程序',
  created  datetime comment '创建时间',
  updated  datetime comment '更新时间',
  primary key id (id),
  key phone (phone),
  unique key phone_type(phone, type)
)  engine = innodb, character set = utf8mb4 comment '第三发账号';

/*==============================================================*/
/* Table: user_bankcard                                         */
/*==============================================================*/
create table user_bankcard
(
  id           int auto_increment not null
  comment '表id',
  user_id      int comment '用户id',
  bank_id      int comment '银行id',
  deposit_bank varchar(64) comment '开户银行',
  card_holder  varchar(64) comment '持卡人姓名',
  card_no      varchar(20) comment '银行卡号',
  status       tinyint default 1
  comment '状态，1-正常，2-已删除',
  created      datetime comment '创建时间',
  updated      datetime comment '更新时间',
  primary key id (id),
  key user_id (user_id),
  key bank_id (bank_id)
)  engine = innodb, character set = utf8mb4 comment '用户银行卡';


/*==============================================================*/
/* Table: user_favorite                                         */
/*==============================================================*/
create table user_favorite
(
  id        int auto_increment not null,
  user_id   int comment '用户id',
  target_id int comment '收藏对象id',
  type      tinyint default '1'
  comment '1 商品',
  created   datetime,
  updated   datetime,
  primary key id (id),
  key user_id (user_id),
  key target_id (target_id)
)  engine = innodb, character set = utf8mb4 comment '用户收藏';

/*==============================================================*/
/* Table: user_withdraw                                         */
/*==============================================================*/
create table user_withdraw
(
  id               int auto_increment not null
  comment '表id',
  user_id          int comment '用户id',
  amount           decimal(18, 2) comment '提现金额',
  user_bankcard_id int,
  status           tinyint default 1
  comment '提现状态，1-申请提现，2-提现中，3-提现成功，4-提现失败',
  `describe`       varchar(64) comment '提现说明',
  created          datetime comment '创建时间',
  updated          datetime comment '更新时间',
  primary key id (id),
  key user_id (user_id),
  key user_bankcard_id (user_bankcard_id)
) engine = innodb, character set = utf8mb4 comment '提现记录';


/*==============================================================*/
/* Table: virtual_item                                          */
/*==============================================================*/
create table virtual_item
(
  id          int auto_increment not null,
  cate_id     int comment '商品分类id',
  name        varchar(255) comment '虚拟商品名称',
  price       decimal(18, 2) comment '价格',
  cover       varchar(255) comment '封面图片',
  description varchar(255) comment '虚拟商品描述',
  sort        int comment '排序值',
  created     datetime,
  updated     datetime,
  primary key id (id),
  key cate_id (cate_id)
)engine = innodb, character set = utf8mb4 comment '虚拟商品';

/*==============================================================*/
/* Table: virtual_item_category                                 */
/*==============================================================*/
create table virtual_item_category
(
  id      int auto_increment not null,
  name    varchar(255) comment '分类名称',
  sort    int comment '排序值',
  created datetime,
  updated datetime,
    primary key id (id)
)engine = innodb, character set = utf8mb4 comment '虚拟商品分类';

/*==============================================================*/
/* Table: wallet                                                */
/*==============================================================*/
create table wallet
(
  user_id        int auto_increment not null
  comment '用户id',
  balance        decimal(18, 2) comment '钱包余额',
  total_amount   decimal(18, 2) comment '历史总金额',
  consume_amount decimal(18, 2) comment '消费总金额',
  created        datetime comment '创建时间',
  updated        datetime comment '更新时间',
    primary key id (user_id)
)engine = innodb, character set = utf8mb4 comment '钱包';

/*==============================================================*/
/* Table: suggestion                                                */
/*==============================================================*/
drop table if exists suggestion;
create table suggestion
(
  id        int auto_increment not null comment '表id',
  user_id   int (11) comment '用户id',
  type        tinyint(4) comment '意见类型，1-提个建议，2-程序出错啦，3-不好用，4-其他',
  feedback    varchar(255) comment '意见反馈文字',
  contact_way  varchar(100) comment '联系方式',
  created        datetime comment '创建时间',
  updated        datetime comment '更新时间',
  primary key id (id)
)engine = innodb, character set = utf8mb4 comment '意见反馈';

/*==============================================================*/
/* Table: virtual_item_order                                                */
/*==============================================================*/

DROP TABLE IF EXISTS `virtual_item_order`;
CREATE TABLE `virtual_item_order`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '虚拟商品订单id',
   order_no bigint comment '订单号',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `virtual_item_id` INT(11) DEFAULT NULL COMMENT '虚拟商品id',
  `quantity` INT(11) DEFAULT NULL COMMENT '数量',
  `name` varchar(200)  DEFAULT NULL COMMENT '商品名称',
  `cover` varchar(500)  DEFAULT NULL COMMENT '虚拟商品封面图片',
  `summary` varchar(500)  DEFAULT NULL COMMENT '虚拟商品描述',
   price   decimal(18, 2) comment '商品单价',
   total_price decimal(18, 2) comment '订单总价',
   `status` tinyint(4) DEFAULT NULL COMMENT '订单状态, 1 未支付 2 已支付 3 已取消',
  `created` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY ( id ),
  KEY `user_id` ( `user_id` ),
  KEY `virtual_item_id` ( `virtual_item_id` )
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '虚拟商品订单表';


/*==============================================================*/
/* Table: event                                                */
/*==============================================================*/
create table event
(
  id        int auto_increment not null comment '表id',
  event_name    varchar(100) comment '事件名称',
  created        datetime comment '创建时间',
  updated        datetime comment '更新时间',
  primary key id (id)
)engine = innodb, character set = utf8mb4 comment '事件列表';

/*==============================================================*/
/* Table: user_virtual_item                                                */
/*==============================================================*/

DROP TABLE IF EXISTS `user_virtual_item`;
CREATE TABLE `user_virtual_item`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `virtual_item_id` INT(11) DEFAULT NULL COMMENT '虚拟商品id',
  `quantity` INT(11) DEFAULT NULL COMMENT '数量',
  `name` varchar(200)  DEFAULT NULL COMMENT '商品名称',
  `cover` varchar(500)  DEFAULT NULL COMMENT '虚拟商品封面图片',
  `summary` varchar(500)  DEFAULT NULL COMMENT '虚拟商品描述',
   price   decimal(18, 2) comment '商品单价',
   total_price decimal(18, 2) comment '商品总价',
  `created` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY ( id ),
  KEY `user_id` ( `user_id` ),
  KEY `virtual_item_id` ( `virtual_item_id` )
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '用户虚拟商品表';


/*==============================================================*/
/* Table: memo_activity                                                */
/*==============================================================*/

DROP TABLE IF EXISTS `memo_activity`;
CREATE TABLE `memo_activity` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` INT(11) COMMENT '创建用户id',
  `target_time` datetime COMMENT '标记的备忘时间',
  `address` varchar(255) comment '地点',
  `count` int(10) comment '人数',
  `title` varchar(128) comment '标题',
  `detail` varchar(255) comment '活动详情',
  `users` varchar(500) comment '被邀请的用户id集合',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  KEY `user_id` ( `user_id` )
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '备忘录活动';
/*==============================================================*/
/* Table: event                                                */
/*==============================================================*/
drop table if exists `cart`;
create table `cart`
(
  id        int auto_increment not null comment '表id',
  user_id     int(11) comment '用户id',
  item_id     int(11) comment '商品id',
  sku_id      int(11) comment 'sku_id',
  price       decimal(18, 2) comment '价格',
  `quantity`    int(11) comment '数量',
  created        datetime comment '创建时间',
  updated        datetime comment '更新时间',
  primary key id (id),
  key `user_id` (`user_id`),
  key `item_id` (`item_id`),
  key `sku_id` (`sku_id`)

)engine = innodb, character set = utf8mb4 comment '购物车';

/*==============================================================*/
/* Table: memo_event                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `memo_event`;
CREATE TABLE `memo_event` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` INT(11) COMMENT '创建用户id',
  `event_time` datetime comment '事件事件',
  `target_time` datetime COMMENT '标记的备忘时间',
  `title` varchar(128) comment '标题',
  `detail` varchar(255) comment '活动详情',
  `is_public` tinyint(4) default 2 comment '是否公开 1 公开 2 不公开',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  KEY `user_id` ( `user_id` )
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '备忘录事件';


/*==============================================================*/
/* Table: app_message                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `app_message`;
CREATE TABLE `app_message` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `title` varchar(128) comment '标题',
  `sumarry` varchar(255) comment '简介',
  `content` varchar(500) comment '消息内容可以是json',
  `target_id` int(11) comment '目标id',
  target_type tinyint(4) comment '目标类型11 礼物实物 12 礼物虚拟物品 41 ',
  message_type tinyint(4) comment '消息类型 1 礼物 2 系统 3 寄售台 4 礼品交换',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key `target_id` (`target_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = 'app消息通知';


/*==============================================================*/
/* Table: app_message_user                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `app_message_user`;
CREATE TABLE `app_message_user` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` int(11) comment '用户id',
  `app_message_id` int(11) comment '消息id',
  `is_read` tinyint(4) comment '是否已读 1 已读 2 未读',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key user_id (user_id),
  key app_message_id (app_message_id)
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '用户消息关联';

/*==============================================================*/
/* Table: user_friend                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` int(11) comment '用户id',
  `friend_user_id` int(11) comment '好友用户id',
  `remark` varchar(64) comment '备注',
  `relationship` varchar(64) comment '该好友和我的关系',
  `group_id` int(11) comment '分组id',
  `sort` int(10) default 0 comment '排序',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  unique key user_friend (user_id, friend_user_id),
  key user_id (user_id),
  key group_id (group_id),
  key friend_user_id (friend_user_id)
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '用户好友关系表';


/*==============================================================*/
/* Table: friend_group                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `friend_group`;
CREATE TABLE `friend_group` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` int(11) comment '用户id',
  `name` varchar(64) comment '分组名称',
  `sort` int(10) default 0 comment '排序',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key user_id (user_id)
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '好友分组';

/*==============================================================*/
/* Table: new_friend_notify                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `new_friend_notify`;
CREATE TABLE `new_friend_notify` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户虚拟商品id',
  `user_id` int(11) comment '创建用户id',
  `target_user_id` int(11) comment '目标用户id',
  `notify_type` tinyint(4) default 1 comment '消息通知类型 1 好友申请通知',
  `status` tinyint(4) default 1 comment '消息处理状态 1 待验证 2 同意 3 拒绝 4 已回复',
  `user_status` tinyint(4) default 1 comment '消息创建者对于这条消息的状态',
  `target_user_status` tinyint(4) default 0 comment '目标用户对于这条消息的状态',
  `content` varchar(500) comment '添加用户的可选属性{"remark":"加好友之后的备注","groupId":加好友之后的分组id}',
  `validation_msg` varchar(128) comment '验证消息',
  `reply` varchar(128) comment '回复消息',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key user_id (user_id)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COMMENT = '新的朋友里面的通知消息';


/*==============================================================*/
/* Table: theme                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '好物主题id',
  `name` varchar(64) comment '好物主题名称',
  `cover` varchar(255) comment '好物主题封面图',
  `sort` int(11) comment '排序值',
  `status` tinyint(4) default 2 comment '主题状态 1 使用 2 禁用',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id )
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '好物主题';

/*==============================================================*/
/* Table: theme_item                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `theme_item`;
CREATE TABLE `theme_item` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '好物主题商品id',
  `item_id` int(11) comment '商品id',
  `theme_id` int(11) comment '主题id',
  `sort` int(11) comment '排序值',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key item_id (item_id),
  key theme_id (theme_id)
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '好物主题商品';

/*==============================================================*/
/* Table: item_order                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `item_order`;
CREATE TABLE `item_order` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品订单id',
  `user_id` int(11) comment '用户id',
  `store_id` int(11) comment '店铺id',
  `order_no` bigint(20) comment '订单号',
  `total_price` decimal(18,2) comment '商品总价',
  `quantity` int(11) comment '数量',
  `status` tinyint(4) comment '订单状态，1-未支付，2-已支付，3-已取消,4-已删除',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key user_id (user_id),
  key store_id (store_id),
  unique key order_no (order_no)
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '创建商品订单';


/*==============================================================*/
/* Table: item_order_detail                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `item_order_detail`;
CREATE TABLE `item_order_detail` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `user_id` int(11) comment '用户id',
  `item_id` int(11) comment '商品id',
  `sku_id` int(11) comment '商品最小销售单元id',
  `order_no` bigint(20) comment '订单号',
  `title` varchar(64) comment '标题',
  `description` varchar(200) comment '商品描述',
  `cover` varchar(200) comment '封面图片',
  `price` decimal(18,2) comment '商品单价',
  `total_price` decimal(18,2) comment '商品总价',
  `quantity` int(11) comment '数量',
  `status` tinyint(4) comment '订单状态，1-未支付，2-已支付，3-已取消,4-已删除',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  key user_id (user_id),
  key item_id (item_id),
  key sku_id (sku_id),
  key order_no (order_no)

) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COMMENT = '商品订单详情';


-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(20)  DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(128)  DEFAULT NULL COMMENT '登录密码MD5加密',
  `active` tinyint(4) UNSIGNED DEFAULT NULL COMMENT '1.启用 0.禁用',
  `avatar` varchar(255)  DEFAULT NULL COMMENT '头像',
  `real_name` varchar(20)  DEFAULT NULL COMMENT '用户真实姓名',
  `phone` varchar(15)  DEFAULT NULL COMMENT '用户手机号',
  `id_number` varchar(20)  DEFAULT NULL COMMENT '身份证号',
  `gender` tinyint(4) UNSIGNED DEFAULT NULL COMMENT '1.男 2.女',
  `email` varchar(255)  DEFAULT NULL COMMENT '电子邮箱地址',
  `qq` varchar(255)  DEFAULT NULL COMMENT 'QQ号',
  `weChat` varchar(255)  DEFAULT NULL COMMENT '微信号',
  `created` datetime(0) DEFAULT NULL COMMENT '注册时间',
  `updated` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_ip` varchar(20)  DEFAULT NULL COMMENT '注册ip',
  `login_count` int(10) UNSIGNED DEFAULT NULL COMMENT '登录次数',
  `last_login_time` datetime(0) DEFAULT NULL COMMENT '最后一次登录时间',
  `last_login_ip` varchar(20)  DEFAULT NULL COMMENT '最后一次登录ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COMMENT = '后台用户表';


/*==============================================================*/
/* Table: user_tag                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `user_tag_dict`;
CREATE TABLE `user_tag_dict` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `tag` varchar(64) comment '标签名字',
  `type` tinyint(4) comment '1 正面的 2 负面的',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( id )
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COMMENT = '好友印象标签字典';

/*==============================================================*/
/* Table: gift_preference_dict                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `gift_preference_dict`;
CREATE TABLE `gift_preference_dict` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `text` varchar(64) comment '标签名字',
  `type` tinyint(4) comment '1 正面的 2 负面的',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( id )
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COMMENT = '礼物偏好字典';


/*==============================================================*/
/* Table: user_detail                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `user_preference`;
CREATE TABLE `user_preference` (
  `user_id` int(11) UNSIGNED NOT NULL ,
  `tags` varchar(1000) comment '好友标签json数组[{tag:标签,num:次数,type:1}]',
  `gift_preference` varchar(255) comment '礼物偏好json数组',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( user_id )
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户信息补充';

/*==============================================================*/
/* Table:  receive_item_order                                            */
/*==============================================================*/
DROP TABLE IF EXISTS `receive_item_order`;
CREATE TABLE `receive_item_order` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `user_id` int(11) comment '用户id',
  `item_id` int(11) comment '商品id',
  `bp_item_id` int(11) comment '背包商品id',
  `sku_id` int(11) comment '商品最小销售单元id',
  `order_no` bigint(20) comment '订单号',
  `title` varchar(64) comment '标题',
  `description` varchar(200) comment '商品描述',
  `cover` varchar(200) comment '封面图片',
  `price` decimal(18,2) comment '商品单价',
  `total_price` decimal(18,2) comment '商品总价',
  `quantity` int(11) comment '数量',
  `receive_info` varchar(255) comment '收货信息',
  `logistics_info` varchar(255) comment '物流信息',
  `status` tinyint(4) comment '订单状态，1-待发货；2-已发货；3-已收货,待评价，4-已评价,5-取消，6-删除',
  `created` datetime(0) COMMENT '创建时间',
  `updated` datetime(0) COMMENT '更新时间',
  PRIMARY KEY ( id ),
  unique key order_no (order_no),
  key user_id (user_id),
  key bp_item_id (bp_item_id)

) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COMMENT = '提货订单';







/*==============================================================*/
/* Table: moment                                               */
/*==============================================================*/
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment` (
  `id` int(11) not null auto_increment,
  `user_id` int(11) UNSIGNED NOT NULL ,
  `content` varchar(512) comment '秀秀文字内容',
  `medias` varchar(2000) comment '秀秀图片或视频内容json数组[{type://1图片2视频,url:图片或视频地址}]',
  `show_gift` tinyint(4) default 2 comment '是否显示最近收到的礼物 1 显示 2 不显示',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( id ),
  key user_id (user_id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户秀秀';


/*==============================================================*/
/* Table: moment_comment                                              */
/*==============================================================*/
DROP TABLE IF EXISTS `moment_comment`;
CREATE TABLE `moment_comment` (
  `id` int(11) not null auto_increment,
  `moment_id` int(11) comment '秀秀id',
  `user_id` int(11) UNSIGNED NOT NULL comment '评论者id',
  `target_user_id` int(11) comment '回复对像用户id',
  `content` varchar(512) comment '评论或回复文字内容',
  `type` tinyint(4) comment '1 评论 2 回复',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( id ),
  key user_id (user_id),
  key moment_id (moment_id),
  key target_user_id (target_user_id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户秀秀评论';


/*==============================================================*/
/* Table: moment_praise                                              */
/*==============================================================*/
DROP TABLE IF EXISTS `moment_praise`;
CREATE TABLE `moment_praise` (
  `id` int(11) not null auto_increment,
  `moment_id` int(11) comment '秀秀id',
  `user_id` int(11) UNSIGNED NOT NULL comment '评论者id',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( id ),
  key user_id (user_id),
  key moment_id (moment_id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户赞';


/*==============================================================*/
/* Table: bp_item                                              */
/*==============================================================*/
DROP TABLE IF EXISTS `bp_item`;
CREATE TABLE `bp_item` (
  `id` bigint(20) not null,
  `user_id` int(11) UNSIGNED NOT NULL comment '评论者id',
  `item_id` int(11) comment '商品id',
  `order_no` bigint(20) comment '购买商品的订单',
  `sku_id` int(11) comment '商品skuId',
  `quantity` int(10) comment '数量',
  `price` decimal(18,2) comment '价格',
  `created` datetime,
  `updated` datetime,
  PRIMARY KEY ( id ),
  key user_id (user_id),
  key sku_id (sku_id),
  key item_id (item_id),
  key order_no (order_no)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '背包商品';

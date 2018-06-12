
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








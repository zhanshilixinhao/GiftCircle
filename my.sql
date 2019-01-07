create table if not exists app_message
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  title varchar(128) null comment '标题',
  summary varchar(5000) null comment '简介',
  content varchar(5000) null comment '消息内容可以是json',
  target_id bigint null comment '目标id  (target_type 为24 目标id是记录详情id ，target_type 为23 目标id是背包id，target_type 为25 目标id是背包id )',
  target_type tinyint null comment '目标类型11 礼物实物 12 礼物虚拟物品 21折现 22 推送 23 提现 24 答谢 25 索要礼品',
  message_type tinyint null comment '消息类型 1 礼物 2 系统 3 寄售台 4 礼品交换',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment 'app消息通知' charset=utf8mb4;

create index target_id
on app_message (target_id);

create table if not exists app_message_user
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '用户id',
  app_message_id int null comment '消息id',
  is_read tinyint null comment '是否已读 1 已读 2 未读',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '用户消息关联' charset=utf8mb4;

create table if not exists app_user
(
  id int auto_increment
  primary key,
  account varchar(15) null comment '账号',
  password varchar(128) null comment '密码',
  phone varchar(15) null comment '手机号码吗',
  avatar varchar(250) null comment '头像',
  nickname varchar(255) null comment '昵称',
  age int null comment '年龄',
  gender tinyint null comment '性别 1 男 2 女',
  signature varchar(255) null comment '签名',
  district varchar(64) null comment '地区',
  status tinyint null comment '状态  1 正常 2 禁用',
  sent_pwd varchar(255) null comment '赠送密码',
  wxid varchar(32) null comment '微信账号',
  created datetime null comment '创建按时间',
  updated datetime null comment '更新时间',
  constraint account
  unique (account),
  constraint phone
  unique (phone)
  )
  comment '用户信息' charset=utf8mb4;

create table if not exists article
(
  id int(10) auto_increment
  primary key,
  title varchar(128) null comment '文章标题',
  cover varchar(255) null comment '封面图',
  summary varchar(255) null comment '简介',
  detail text null comment '文章详情富文本',
  admin_id int null comment '创建人',
  status tinyint null comment '1 正常 2 不可用 3 已删除',
  sort int null,
  created datetime null,
  updated datetime null
  )
  comment '文章' charset=utf8mb4;

create index admin_id
on article (admin_id);

create table if not exists bank_dict
(
  id int auto_increment comment '表id'
  primary key,
  bank_name varchar(64) null comment '银行名称',
  logo varchar(255) null comment '银行logo',
  status tinyint null comment '状态，1-正常，2-禁用',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '银行' charset=utf8mb4;

create table if not exists banner
(
  id int(8) auto_increment comment '表id'
  primary key,
  target_id varchar(32) null comment '跳转的目标id',
  cover varchar(100) null comment '封面图片',
  request_url varchar(200) null comment '跳转url',
  title varchar(32) null comment '	图片标题',
  rich_text text null comment '富文本',
  type tinyint null comment 'banner类型,  1 跳转到商品详情 2 跳转到好物主题列表 3-跳转url,4-纯展示',
  status tinyint null comment '1 正常 2 禁用 3 已删除',
  created datetime null comment '创建时间',
  updated datetime null comment '修改时间'
  )
  comment 'banner表' charset=utf8mb4;

create table if not exists bp_item
(
  id bigint not null
  primary key,
  user_id int(11) unsigned not null comment '评论者id',
  target_id int null comment '目标物品id，type=1商品skuId，type=2虚拟物品id，type=3优惠券id',
  quantity int(10) null comment '数量',
  price decimal(18,2) null comment '价格',
  `from` varchar(255) null comment '来源json字符串{}',
  type tinyint null comment '1 物品 2 虚拟物品 3 优惠券',
  created datetime null,
  updated datetime null
  )
  comment '背包商品' charset=utf8mb4;

create index target_id
on bp_item (target_id);

create index user_id
on bp_item (user_id);

create table if not exists brand
(
  id int auto_increment
  primary key,
  name varchar(64) null comment '品牌名称',
  logo varchar(255) null,
  status tinyint null comment '状态 1.正常 2.已删除',
  created datetime null,
  updated datetime null
  )
  comment '品牌' charset=utf8mb4;

create table if not exists cart
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  item_id int null comment '商品id',
  sku_id int null comment 'sku_id',
  price decimal(18,2) null comment '价格',
  quantity int null comment '数量',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '购物车' charset=utf8mb4;

create index item_id
on cart (item_id);

create index sku_id
on cart (sku_id);

create index user_id
on cart (user_id);

create table if not exists charge_order
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  amount decimal(18,2) null comment '充值金额',
  order_no bigint null comment '订单号',
  status tinyint null comment '状态，1-未支付，2-已支付，3-已删除',
  pay_way tinyint null comment '支付方式，1-微信，2-支付宝，',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  constraint order_no
  unique (order_no)
  )
  comment '充值订单' charset=utf8mb4;

create index user_id
on charge_order (user_id);

create table if not exists consignment
(
  id int auto_increment
  primary key,
  bp_id bigint null comment '背包id',
  user_id int(11) unsigned not null comment '发布者id',
  target_id int null comment '目标物品id',
  quantity int(10) null comment '数量',
  price decimal(18,2) null comment '价格',
  type tinyint null comment '1 商品 2 虚拟商品（没有)，3 优惠券',
  status tinyint null comment '1 上架 2 已购买未支付 3 交易完成 4 已下架',
  updated datetime null,
  created datetime null
  )
  comment '寄售台' charset=utf8mb4;

create index bp_id
on consignment (bp_id);

create index target_id
on consignment (target_id);

create index user_id
on consignment (user_id);

create table if not exists consignment_order
(
  id int auto_increment
  primary key,
  order_no bigint null comment '背包id',
  user_id int(11) unsigned not null comment '买家用户id',
  sell_user_id int(11) unsigned not null comment '发布者id',
  consignment_id int null comment '寄售台id',
  quantity int(10) null comment '数量',
  price decimal(18,2) null comment '价格',
  status tinyint null comment '1 未支付 2 已支付 ,3 已取消,4 已删除',
  updated datetime null,
  created datetime null,
  constraint order_no
  unique (order_no)
  )
  comment '寄售台订单' charset=utf8mb4;

create index buyer_user_id
on consignment_order (sell_user_id);

create index consignment_id
on consignment_order (consignment_id);

create index user_id
on consignment_order (user_id);

create table if not exists coupon
(
  id int auto_increment
  primary key,
  merchant_id int null comment '合作商id',
  admin_id int null comment '管理员id',
  partner_id int null comment '品牌id',
  title varchar(64) null comment '标题,例如5元',
  brand_name varchar(64) null comment '品牌名称',
  content varchar(255) null comment '描述json字符床{discount:5元/7折,type:抵用券/折扣券,detail:不限时间不限礼品}',
  cover varchar(255) null comment '优惠券图片',
  status tinyint null comment '1 启用 2 禁用 3 已删除',
  `starting` datetime null comment '开始时间',
  ending datetime null comment '结束时间',
  updated datetime null,
  created datetime null
  )
  comment '优惠券' charset=utf8mb4;

create index admin_id
on coupon (admin_id);

create index merchant_id
on coupon (merchant_id);

create table if not exists discounting
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  bp_id bigint null comment '背包id',
  item_price decimal(18,2) null comment '商品价格',
  discount_price decimal(18,2) null comment '折现后价格',
  `explain` varchar(2000) null comment '折现说明',
  status tinyint null comment '折现状态，折现状态，1-折现成功，2-折现失败，-1删除记录',
  type tinyint null comment '1-物品，2-虚拟物品',
  created datetime null comment '创建时间',
  updated datetime null comment '修改时间'
  )
  comment '折现记录' charset=utf8mb4;

create index bp_id
on discounting (bp_id);

create index user_id
on discounting (user_id);

create table if not exists district
(
  adcode int auto_increment comment '行政区编码'
  primary key,
  p_adcode int null comment '父级行政区编码',
  name varchar(64) null comment '行政区名称',
  type tinyint null comment '行政区类型 1 省/自治区 2 神奇的 4 城市 5 行政区',
  level varchar(15) null comment '行政区级别',
  created datetime null comment '创建时间',
  updated datetime null
  )
  comment '全国行政区' charset=utf8mb4;

create index adcode
on district (adcode);

create table if not exists event
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id(0代表系统添加)',
  event_name varchar(100) null comment '事件名称',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '事件列表' charset=utf8mb4;

create table if not exists for_record
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  bp_id bigint null comment '背包id',
  friend_user_id int null comment '好友用户id',
  status tinyint null comment '索要状态，1-索要中，2-索要成功，3-索要失败',
  operation tinyint null comment '0-默认无操作， 1-同意好友索要，2-拒绝好友索要',
  created datetime null comment '创建时间',
  updated datetime null comment '修改时间'
)
  comment '索要记录' charset=utf8mb4;

create index bp_id
on for_record (bp_id);

create index friend_user_id
on for_record (friend_user_id);

create index user_id
on for_record (user_id);

create table if not exists friend
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '用户id',
  friend_user_id int null comment '好友用户id',
  remark varchar(64) null comment '备注',
  relationship varchar(64) null comment '该好友和我的关系',
  group_id int null comment '分组id',
  sort int(10) default 0 null comment '排序',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  constraint user_friend
  unique (user_id, friend_user_id)
  )
  comment '用户好友关系表' charset=utf8mb4;

create index friend_user_id
on friend (friend_user_id);

create index group_id
on friend (group_id);

create index user_id
on friend (user_id);

create table if not exists friend_group
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '用户id',
  name varchar(64) null comment '分组名称',
  sort int(10) default 0 null comment '排序',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '好友分组' charset=utf8mb4;

create index group_name
on friend_group (name);

create index user_id
on friend_group (user_id);

create table if not exists gift_exchange
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  friend_user_id int null comment '好友用户id',
  exchange_gifts varchar(1000) null comment '想要交换的物品',
  want_gifts varchar(1000) null comment '想要的物品',
  submit_gifts varchar(1000) null comment '好友提交的物品',
  status tinyint null comment '礼品添加状态，1-用户提交，2-好友提交，3-已完成，4-交换失败',
  created datetime null comment '创建时间',
  updated datetime null comment '修改时间'
  )
  comment '礼物交换' charset=utf8mb4;

create index friend_user_id
on gift_exchange (friend_user_id);

create index user_id
on gift_exchange (user_id);

create table if not exists gift_preference_dict
(
  id int(11) unsigned auto_increment
  primary key,
  text varchar(64) null comment '标签名字',
  type tinyint null comment '1 正面的 2 负面的',
  created datetime null,
  updated datetime null
  )
  comment '礼物偏好字典' charset=utf8mb4;

create table if not exists gift_record
(
  id int auto_increment
  primary key,
  user_id int(11) unsigned not null comment '送礼者id',
  greetting varchar(255) null comment '祝福语',
  type tinyint null comment '1 立即赠送 2 按时间赠送 3 小程序选择好友赠送需要领取 4 小程序随机赠送需要领取',
  event varchar(64) null comment '事件名称',
  target_time datetime null comment '目标赠送赠送时间',
  status tinyint null comment '1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败',
  updated datetime null,
  created datetime null,
  p float(4,2) null comment '礼物获得概率'
  )
  comment '送礼记录' charset=utf8mb4;

create index user_id
on gift_record (user_id);

create table if not exists gift_record_detail
(
  id int auto_increment
  primary key,
  gift_record_id int null comment '礼物记录id',
  user_id int(11) unsigned null comment '接收礼物的用户id',
  amount decimal(18,2) null comment '礼物的总金额',
  content varchar(2000) null comment '礼物内容json数组',
  is_reply tinyint null comment '1 答谢 2 未答谢',
  reply varchar(255) null comment '答谢回复',
  status tinyint null comment '1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败',
  updated datetime null,
  created datetime null
  )
  comment '送礼记录详细信息' charset=utf8mb4;

create index gift_record_id
on gift_record_detail (gift_record_id);

create index user_id
on gift_record_detail (user_id);

create table if not exists gift_record_self
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '备注用户id',
  amount decimal(18,2) null comment '记录金额',
  event varchar(64) null comment '说点什么',
  detail varchar(255) null comment '详情',
  ob_type varchar(32) null comment '类型 个人、家庭',
  inout_type tinyint null comment '1 汁出 2 收入',
  target_time datetime null comment '记录时间',
  created datetime null comment '创建时间',
  updated datetime null comment '修改时间'
  )
  comment '自己补录的礼品记录' charset=utf8mb4;

create table if not exists item
(
  id int auto_increment
  primary key,
  title varchar(200) null comment '标题',
  price decimal(18,2) null comment '价格',
  sales int null comment '销量',
  stock int null comment '库存',
  description varchar(200) null comment '属性描述',
  re_gender tinyint null comment '推荐性别 0 默认 1 男  2 女',
  re_max_age tinyint null comment '推荐最大年龄',
  re_age_min tinyint null comment '推荐最小年龄',
  store_id int null comment '店铺id',
  category_id int null comment '分类id',
  cover varchar(255) null comment '封面图',
  pictures varchar(2000) null comment '商品相册',
  choiceness tinyint default 0 null comment '精选，0-否，1-是',
  hot tinyint default 0 null comment '热门，0-否，1-是',
  status tinyint null comment '1 正常 2 禁用 3 已删除',
  is_audit tinyint default 0 null comment '1 审核通过 2 审核失败',
  brand_id int null comment '品牌id',
  created datetime null,
  updated datetime null
  )
  comment '商品信息' charset=utf8mb4;

create index brand_id
on item (brand_id);

create index category_id
on item (category_id);

create table if not exists item_category
(
  id int auto_increment
  primary key,
  pid int null comment '父级分类id',
  name varchar(64) null comment '分类名称',
  status tinyint null comment '1 正常  2 禁用 3 已删除',
  sort int null comment '排序值',
  icon varchar(200) null comment '图标',
  created datetime null,
  updated datetime null
  )
  comment '商品分类' charset=utf8mb4;

create index pid
on item_category (pid);

create table if not exists item_comment
(
  id int auto_increment
  primary key,
  sku_id int null comment '最小销售单元id',
  item_id int null comment '商品id',
  order_no bigint null comment '订单号',
  user_id int null comment '用户id',
  star int default 0 null comment '评分',
  content varchar(2000) null comment '评价文字内容',
  pictures varchar(2000) null comment '评价图品集合 json数组',
  created datetime null,
  updated datetime null
  )
  comment '商品评价' charset=utf8mb4;

create index item_id
on item_comment (item_id);

create index order_no
on item_comment (order_no);

create index user_id
on item_comment (user_id);

create table if not exists item_detail
(
  item_id int auto_increment
  primary key,
  detail text null comment '详情富文本'
)
  comment '商品详情' charset=utf8mb4;

create table if not exists item_event
(
  id int auto_increment
  primary key,
  event_id int null comment '事件id',
  item_id int null comment '商品id',
  created datetime null,
  updated datetime null
)
  comment '商品事件' charset=utf8mb4;

create index event_id
on item_event (event_id);

create index item_id
on item_event (item_id);

create table if not exists item_feature
(
  id int auto_increment
  primary key,
  name varchar(64) null comment '属性名称',
  sort int null comment '排序值',
  `values` varchar(500) null comment '属性值集合',
  is_select tinyint null comment '1 可选 2 不可选',
  status tinyint null comment '1 正常  2 禁用 3 删除',
  created datetime null,
  updated datetime null
  )
  comment '商品属性' charset=utf8mb4;

create table if not exists item_order
(
  id int(11) unsigned auto_increment comment '商品订单id'
  primary key,
  user_id int null comment '用户id',
  store_id int null comment '店铺id',
  order_no bigint null comment '订单号',
  total_price decimal(18,2) null comment '商品总价',
  quantity int null comment '数量',
  status tinyint null comment '订单状态，1-未支付，2-已支付，3-已取消,4-已删除',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  constraint order_no
  unique (order_no)
  )
  comment '创建商品订单' charset=utf8mb4;

create index store_id
on item_order (store_id);

create index user_id
on item_order (user_id);

create table if not exists item_order_detail
(
  id int(11) unsigned auto_increment
  primary key,
  user_id int null comment '用户id',
  item_id int null comment '商品id',
  sku_id int null comment '商品最小销售单元id',
  order_no bigint null comment '订单号',
  title varchar(64) null comment '标题',
  description varchar(200) null comment '商品描述',
  cover varchar(200) null comment '封面图片',
  price decimal(18,2) null comment '商品单价',
  total_price decimal(18,2) null comment '商品总价',
  quantity int null comment '数量',
  status tinyint null comment '订单状态，1-未支付，2-已支付，3-已取消,4-已删除',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '商品订单详情' charset=utf8mb4;

create index item_id
on item_order_detail (item_id);

create index order_no
on item_order_detail (order_no);

create index sku_id
on item_order_detail (sku_id);

create index user_id
on item_order_detail (user_id);

create table if not exists item_sku
(
  id int auto_increment
  primary key,
  item_id int null comment '商品id',
  title varchar(255) null comment 'sku标题',
  cover varchar(255) null comment '封面图',
  pictures varchar(2000) null comment '相册图集合',
  stock int null comment 'sku库存',
  price decimal(18,2) null,
  sales int null comment '销量',
  status tinyint null comment '状态 1 正常 2 禁用',
  created datetime null,
  updated datetime null
  )
  comment '商品最小销售单元' charset=utf8mb4;

create index item_id
on item_sku (item_id);

create table if not exists item_sku_value
(
  id int auto_increment
  primary key,
  sku_id int null comment 'sku id',
  value_id int null comment '属性值id',
  status tinyint null comment '1 正常',
  created datetime null comment '从',
  updated datetime null
)
  comment 'sku和属性关联表' charset=utf8mb4;

create index sku_id
on item_sku_value (sku_id);

create index value_id
on item_sku_value (value_id);

create table if not exists item_value
(
  id int auto_increment
  primary key,
  item_id int null comment '商品id',
  feature_id int null comment '属性id',
  value varchar(64) null comment '属性值',
  sort int null comment '排序值',
  created datetime null,
  updated datetime null
  )
  comment '商品属性' charset=utf8mb4;

create index feature_id
on item_value (feature_id);

create index item_id
on item_value (item_id);

create table if not exists memo_activity
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '创建用户id',
  target_time datetime null comment '标记的备忘时间',
  address varchar(255) null comment '地点',
  count int(10) null comment '人数',
  title varchar(128) null comment '标题',
  detail varchar(255) null comment '活动详情',
  users varchar(500) null comment '被邀请的用户id集合',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '备忘录活动' charset=utf8mb4;

create index user_id
on memo_activity (user_id);

create table if not exists memo_event
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '创建用户id',
  event_time datetime null comment '事件事件',
  target_time datetime null comment '标记的备忘时间',
  title varchar(128) null comment '标题',
  detail varchar(255) null comment '活动详情',
  is_public tinyint default 2 null comment '是否公开 1 公开 2 不公开',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '备忘录事件' charset=utf8mb4;

create index user_id
on memo_event (user_id);


create table if not exists memo_affair
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '创建用户id',
  target_time datetime null comment '标记的备忘时间',
  title varchar(128) null comment '标题',
  detail varchar(255) null comment '活动详情',
  users varchar(500) null comment '被邀请的用户id集合',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '备忘录事件(新加)' charset=utf8mb4;
create index user_id
on memo_event (user_id);



create table if not exists merchant
(
  id int auto_increment
  primary key,
  name varchar(200) null comment '企业名称',
  user_id int null comment '用户id',
  admin_id int null comment '分配的管理员id',
  address varchar(200) null comment '地址',
  registration_no varchar(32) null comment '企业注册号',
  legal_person varchar(32) null comment '法人代表',
  phone varchar(15) null comment '联系电话',
  license_pic varchar(255) null comment '营业执照照片',
  other_pics varchar(1000) null comment '其他证件图片',
  status tinyint null comment '1 默认 2 审核中 3 审核通过 4 审核失败 5 已刪除',
  created datetime null comment '创建时间',
  updated datetime null
  )
  comment '商家' charset=utf8mb4;

create index admin_id
on merchant (admin_id);

create index user_id
on merchant (user_id);

create table if not exists message_push
(
  id int auto_increment comment '表id'
  primary key,
  app_message_id int null comment '消息id',
  title varchar(200) null comment '消息标题',
  content varchar(200) null comment '消息内容',
  type tinyint null comment '推送类型，1-消息提醒，2-链接',
  type_value varchar(1000) null comment '推送类型值',
  ring tinyint null comment '是否响铃，1-是，2-否',
  vibrate tinyint null comment '是否震动，1-是，2-否',
  clearable tinyint null comment '是否可清除，1-是，2-否',
  status tinyint null comment '推送状态，1-推送成功，2-推送失败',
  created datetime null comment '创建时间',
  create_admin_id int null comment '创建者',
  updated datetime null comment '修改时间',
  update_admin_id int null comment '修改者'
  )
  comment '消息推送' charset=utf8mb4;

create index app_message_id
on message_push (app_message_id);

create index create_admin_id
on message_push (create_admin_id);

create index update_admin_id
on message_push (update_admin_id);

create table if not exists moment
(
  id int auto_increment
  primary key,
  user_id int(11) unsigned not null,
  content varchar(512) null comment '秀秀文字内容',
  medias varchar(2000) null comment '秀秀图片或视频内容json数组[{type://1图片2视频,url:图片或视频地址}]',
  show_gift tinyint default 2 null comment '是否显示最近收到的礼物 1 显示 2 不显示',
  media_count int(6) null,
  created datetime null,
  updated datetime null
  )
  comment '用户秀秀' charset=utf8mb4;

create index user_id
on moment (user_id);

create table if not exists moment_comment
(
  id int auto_increment
  primary key,
  t_comment_id int null comment '回复的评论id',
  moment_id int null comment '秀秀id',
  user_id int(11) unsigned not null comment '评论者id',
  target_user_id int null comment '回复对像用户id',
  content varchar(512) null comment '评论或回复文字内容',
  type tinyint null comment '1 评论 2 回复',
  created datetime null,
  updated datetime null
  )
  comment '用户秀秀评论' charset=utf8mb4;

create index moment_id
on moment_comment (moment_id);

create index target_user_id
on moment_comment (target_user_id);

create index user_id
on moment_comment (user_id);

create table if not exists moment_praise
(
  id int auto_increment
  primary key,
  moment_id int null comment '秀秀id',
  user_id int(11) unsigned not null comment '评论者id',
  created datetime null,
  updated datetime null
  )
  comment '用户赞' charset=utf8mb4;

create index moment_id
on moment_praise (moment_id);

create index user_id
on moment_praise (user_id);

create table if not exists new_friend_notify
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '创建用户id',
  target_user_id int null comment '目标用户id',
  notify_type tinyint default 1 null comment '消息通知类型 1 好友申请通知',
  status tinyint default 1 null comment '消息处理状态 1 待验证 2 同意 3 拒绝 4 已回复',
  user_status tinyint default 1 null comment '消息创建者对于这条消息的状态',
  target_user_status tinyint default 0 null comment '目标用户对于这条消息的状态',
  content varchar(500) null comment '添加用户的可选属性{"remark":"加好友之后的备注","groupId":加好友之后的分组id}',
  validation_msg varchar(128) null comment '验证消息',
  reply varchar(128) null comment '回复消息',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '新的朋友里面的通知消息' charset=utf8mb4;

create index target_user_id
on new_friend_notify (target_user_id);

create index user_id
on new_friend_notify (user_id);

create table if not exists payment_info
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  type tinyint null comment '1-充值订单,2-商品订单',
  order_no bigint null comment '订单号',
  total_fee decimal(18,2) null comment '交易金额',
  pay_platform tinyint null comment '支付平台，1-微信，2-支付宝，3-余额',
  platform_number varchar(100) null comment '支付流水号',
  platform_status varchar(20) null comment '支付状态，平台交易成功状态标识:支付返回码：9000(支付宝支付成功)',
  seller varchar(100) null comment '卖家账号',
  buyer varchar(100) null comment '买家账号',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  constraint order_no
  unique (order_no)
  )
  comment '支付信息记录' charset=utf8mb4;

create table if not exists receive_item_order
(
  id int(11) unsigned auto_increment
  primary key,
  user_id int null comment '用户id',
  item_id int null comment '商品id',
  bp_id bigint null comment '背包id',
  sku_id int null comment '商品最小销售单元id',
  order_no bigint null comment '订单号',
  title varchar(64) null comment '标题',
  description varchar(200) null comment '商品描述',
  cover varchar(200) null comment '封面图片',
  price decimal(18,2) null comment '商品单价',
  total_price decimal(18,2) null comment '商品总价',
  quantity int null comment '数量',
  receive_info varchar(255) null comment '收货信息',
  logistics_info varchar(255) null comment '物流信息',
  status tinyint null comment '订单状态，1-待发货；2-已发货；3-已收货,待评价，4-已评价,5-取消，6-删除',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  constraint order_no
  unique (order_no)
  )
  comment '提货订单' charset=utf8mb4;

create index bp_item_id
on receive_item_order (bp_id);

create index user_id
on receive_item_order (user_id);

create table if not exists shipping
(
  id int auto_increment
  primary key,
  user_id int null comment '用户id',
  consignee_name varchar(32) null comment '收货人',
  address varchar(128) null comment '收获地址地区',
  phone varchar(15) null comment '联系电话',
  address_detail varchar(128) null comment '详细地址',
  adcode int null comment '收货地址的行政却编码',
  status tinyint null comment '1 正常 2 默认地址 3 删除的地址',
  created datetime null comment '创建时间',
  updated datetime null
  )
  comment '收货地址' charset=utf8mb4;

create index user_id
on shipping (user_id);

create table if not exists store
(
  id int auto_increment
  primary key,
  merchant_id int null comment '商家id',
  name varchar(200) null comment '店铺名称',
  address varchar(200) null comment '店铺地址',
  phone varchar(15) null comment '电话',
  created datetime null,
  updated datetime null
  )
  comment '店铺' charset=utf8mb4;

create index merchant_id
on store (merchant_id);

create table if not exists suggestion
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  type tinyint null comment '意见类型，1-提个建议，2-程序出错啦，3-不好用，4-其他',
  feedback varchar(255) null comment '意见反馈文字',
  contact_way varchar(100) null comment '联系方式',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '意见反馈' charset=utf8mb4;

create table if not exists sys_admin
(
  id int unsigned auto_increment comment '自增主键'
  primary key,
  username varchar(20) null comment '登录用户名',
  password varchar(32) null comment '登录密码MD5加密',
  active tinyint(4) unsigned null comment '1.启用 0.禁用',
  avatar varchar(255) null comment '头像',
  real_name varchar(20) null comment '用户真实姓名',
  phone varchar(15) null comment '用户手机号',
  id_number varchar(20) null comment '身份证号',
  gender tinyint(4) unsigned null comment '1.男 2.女',
  email varchar(255) null comment '电子邮箱地址',
  qq varchar(255) null comment 'QQ号',
  weChat varchar(255) null comment '微信号',
  created datetime null comment '注册时间',
  updated datetime null comment '更新时间',
  create_admin_id int(11) unsigned null comment '创建人',
  update_admin_id int(11) unsigned null comment '更新人',
  create_ip varchar(20) null comment '注册ip',
  login_count int unsigned null comment '登录次数',
  last_login_time datetime null comment '最后一次登录时间',
  last_login_ip varchar(20) null comment '最后一次登录ip',
  constraint username
  unique (username)
  )
  comment '后台用户表' charset=utf8mb4;

create table if not exists sys_admin_role
(
  admin_id int unsigned not null comment '后台用户ID',
  role_id int unsigned not null comment '角色ID',
  admin_role_note varchar(255) null comment '用户角色标记',
  created datetime null comment '关系建立时间',
  primary key (admin_id, role_id)
  )
  comment '后台用户和权限角色关联表' charset=utf8mb4;

create table if not exists sys_function_permission
(
  id int unsigned auto_increment comment '功能权限ID'
  primary key,
  name varchar(30) null comment '功能权限名称',
  desciption varchar(255) null,
  url varchar(255) null comment '功能URL',
  menu_id int unsigned null comment '功能所属菜单功能组',
  active tinyint(4) unsigned null comment '1.启用 0.禁用',
  created datetime null comment '创建时间',
  create_admin_id int unsigned null comment '创建人',
  updated datetime null,
  update_admin_id int unsigned null comment '更新人'
  )
  charset=utf8mb4;

create table if not exists sys_menu
(
  id int unsigned auto_increment comment '自增主键'
  primary key,
  parent_id int(10) null comment '父级菜单id',
  name varchar(20) null comment '菜单名称',
  description varchar(255) null comment '功能描述',
  url varchar(255) null,
  active tinyint(4) unsigned null comment '1.启用 0.禁用',
  created datetime null comment '创建时间',
  create_admin_id int unsigned null comment '创建人',
  updated datetime null comment '更新时间',
  update_admin_id int unsigned null comment '更新人',
  constraint url_index
  unique (url)
  )
  charset=utf8mb4;

create table if not exists sys_role
(
  id int(11) unsigned auto_increment comment '角色id'
  primary key,
  name varchar(20) null comment '角色名称',
  description varchar(255) null comment '角色描述',
  permission_names varchar(5000) null comment '角色权限名称集合',
  active tinyint(4) unsigned null comment '1.启用 0.禁用',
  created datetime null,
  updated datetime null,
  create_admin_id int null,
  update_admin_id int null
  )
  comment '后台用户角色' charset=utf8mb4;

create table if not exists sys_role_function_permission
(
  role_id int unsigned not null comment '角色id',
  sys_function_permission_id int unsigned not null comment '功能权限id',
  role_permission_note varchar(255) null comment '权限角色关系描述',
  created datetime null comment '关系建立时间',
  primary key (role_id, sys_function_permission_id)
  )
  charset=utf8mb4;

create table if not exists sys_role_menu
(
  role_id int unsigned not null comment '角色id',
  menu_id int unsigned not null comment '菜单id',
  role_menu_note varchar(255) null comment '菜单角色关系描述',
  created datetime null comment '关系建立时间',
  primary key (role_id, menu_id)
  )
  charset=utf8mb4;

create table if not exists theme
(
  id int(11) unsigned auto_increment comment '好物主题id'
  primary key,
  name varchar(64) null comment '好物主题名称',
  cover varchar(255) null comment '好物主题封面图',
  sort int null comment '排序值',
  status tinyint default 2 null comment '主题状态 1 使用 2 禁用 3已删除',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '商品主题' charset=utf8mb4;

create table if not exists theme_item
(
  id int(11) unsigned auto_increment comment '好物主题商品id'
  primary key,
  item_id int null comment '商品id',
  theme_id int null comment '主题id',
  sort int null comment '排序值',
  status tinyint null comment '状态 1 正常 2 禁用',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '好物主题商品' charset=utf8mb4;

create index item_id
on theme_item (item_id);

create index theme_id
on theme_item (theme_id);

create table if not exists third_account
(
  id int auto_increment comment '表id'
  primary key,
  open_id varchar(64) null comment '第三方账号唯一标识',
  phone varchar(32) null comment '用户账号',
  type tinyint null comment '第三方账号类型，1-App微信，2-微信小程序',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  constraint phone_type
  unique (phone, type)
  )
  comment '第三发账号' charset=utf8mb4;

create index phone
on third_account (phone);

create table if not exists user_bankcard
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  bank_id int null comment '银行id',
  deposit_bank varchar(64) null comment '开户银行',
  card_holder varchar(64) null comment '持卡人姓名',
  card_no varchar(20) null comment '银行卡号',
  status tinyint default 1 null comment '状态，1-正常，2-已删除',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间',
  is_default tinyint default 2 null comment '1 默认 2 不是默认'
  )
  comment '用户银行卡' charset=utf8mb4;

create index bank_id
on user_bankcard (bank_id);

create index user_id
on user_bankcard (user_id);

create table if not exists user_favorite
(
  id int auto_increment
  primary key,
  user_id int null comment '用户id',
  target_id int null comment '收藏对象id',
  type tinyint default 1 null comment '1 商品',
  created datetime null,
  updated datetime null
)
  comment '用户收藏' charset=utf8mb4;

create index target_id
on user_favorite (target_id);

create index user_id
on user_favorite (user_id);

create table if not exists user_preference
(
  user_id int(11) unsigned not null
  primary key,
  tags varchar(1000) null comment '好友标签json数组[{tag:标签,num:次数,type:1}]',
  gift_preference varchar(255) null comment '礼物偏好json数组',
  created datetime null,
  updated datetime null
  )
  comment '用户信息补充' charset=utf8mb4;

create table if not exists user_tag_dict
(
  id int(11) unsigned auto_increment
  primary key,
  tag varchar(64) null comment '标签名字',
  type tinyint null comment '1 正面的 2 负面的',
  status tinyint null comment '标签状态 1 可用 2 禁用 3 已删除',
  created datetime null,
  updated datetime null
  )
  comment '好友印象标签字典' charset=utf8mb4;

create table if not exists user_virtual_item
(
  id int(11) unsigned auto_increment comment '用户虚拟商品id'
  primary key,
  user_id int null comment '用户id',
  virtual_item_id int null comment '虚拟商品id',
  quantity int null comment '数量',
  name varchar(200) null comment '商品名称',
  cover varchar(500) null comment '虚拟商品封面图片',
  summary varchar(500) null comment '虚拟商品描述',
  price decimal(18,2) null comment '商品单价',
  total_price decimal(18,2) null comment '商品总价',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '用户虚拟商品表' charset=utf8mb4;

create index user_id
on user_virtual_item (user_id);

create index virtual_item_id
on user_virtual_item (virtual_item_id);

create table if not exists user_withdraw
(
  id int auto_increment comment '表id'
  primary key,
  user_id int null comment '用户id',
  amount decimal(18,2) null comment '提现金额',
  user_bankcard_id int null,
  status tinyint default 1 null comment '提现状态，1-申请提现，2-处理中 3-提现成功，4-提现失败',
  `describe` varchar(64) null comment '提现说明',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '提现记录' charset=utf8mb4;

create index user_bankcard_id
on user_withdraw (user_bankcard_id);

create index user_id
on user_withdraw (user_id);

create table if not exists virtual_item
(
  id int auto_increment
  primary key,
  brand_id int null comment '虚拟商品品牌id',
  cate_id int null comment '商品分类id',
  name varchar(255) null comment '虚拟商品名称',
  price decimal(18,2) null comment '价格',
  cover varchar(255) null comment '封面图片',
  description varchar(255) null comment '虚拟商品描述',
  sales int null comment '销量',
  status tinyint null comment '虚拟商品状态 1.正常 2.禁用 3.删除',
  sort int null comment '排序值',
  created datetime null,
  updated datetime null
  )
  comment '虚拟商品' charset=utf8mb4;

create index brand_id
on virtual_item (brand_id);

create index cate_id
on virtual_item (cate_id);

create table if not exists virtual_item_brand
(
  id int auto_increment
  primary key,
  name varchar(200) null comment '虚拟商品品牌名称',
  status tinyint null comment '1 正常 2 删除',
  updated datetime null,
  created datetime null
  )
  comment '虚拟商品品牌信息' charset=utf8mb4;

create table if not exists virtual_item_category
(
  id int auto_increment
  primary key,
  name varchar(255) null comment '分类名称',
  status tinyint null comment '分类状态 1.正常 2.禁用 3.删除',
  sort int null comment '排序值',
  created datetime null,
  updated datetime null
  )
  comment '虚拟商品分类' charset=utf8mb4;

create table if not exists virtual_item_order
(
  id int(11) unsigned auto_increment comment '虚拟商品订单id'
  primary key,
  order_no bigint null comment '订单号',
  user_id int null comment '用户id',
  virtual_item_id int null comment '虚拟商品id',
  quantity int null comment '数量',
  name varchar(200) null comment '商品名称',
  cover varchar(500) null comment '虚拟商品封面图片',
  summary varchar(500) null comment '虚拟商品描述',
  price decimal(18,2) null comment '商品单价',
  total_price decimal(18,2) null comment '订单总价',
  status tinyint null comment '订单状态, 1 未支付 2 已支付 3 已取消，4已删除',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '虚拟商品订单表' charset=utf8mb4;

create index user_id
on virtual_item_order (user_id);

create index virtual_item_id
on virtual_item_order (virtual_item_id);

create table if not exists wallet
(
  user_id int auto_increment comment '用户id'
  primary key,
  balance decimal(18,2) null comment '钱包余额',
  total_amount decimal(18,2) null comment '历史总金额',
  consume_amount decimal(18,2) null comment '消费总金额',
  created datetime null comment '创建时间',
  updated datetime null comment '更新时间'
  )
  comment '钱包' charset=utf8mb4;

create table if not exists wallet_record
(
  id int auto_increment
  primary key,
  user_id int null comment '用户id',
  `explain` varchar(200) null comment '钱包使用记录说明',
  amount decimal(18,2) null comment '变动金额',
  target_id bigint null comment '目标Id',
  type tinyint null comment '记录类型 1-充值，2-商品折现，3-虚拟物品折现，4-寄售台出售物品，5-购买商品，6-寄售台物品购买, 7-提现失败返还额度',
  updated datetime null,
  created datetime null
  )
  comment '钱包收益记录' charset=utf8mb4;


package com.chouchongkeji.util;

import com.chouchongkeji.service.user.friend.vo.FriendVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yichenshanren
 * @date 2017/10/15
 */

public class Constants {
    //默认头像
    public static final String DEFALUT_AVATAR = "/avatar.jpg";
    public static final String GROUP_DEFAULT_NAME = "未分组";

    // 送礼微信头像昵称
    public static final String WEIXIINAVATAR = "https://liyuquan.cn/static/bank/weixin.png";
    public static final String WEIXINNAME = "微信好友";

    //默认地区
    public static final String DEFALUT_DISTRICT = "昆明";
    public static final int BP_EXPIRE_TIME = 30; // 背包物品提货有效期

    public static final String getRandomName() {
        return RandomStringUtils.randomNumeric(7);
    }

    /* app用户购买商品之后的年收益利率 */
    public static final float USER_EARNINGS_RATE = 0.002F;
    public static final float AGENT_EARNINGS_RATE = 0.003F;

    // 用户收益有限期 一年
    public static final int USER_EARNIGS_EXPIRE = 12;
    public static final long USER_EARNIGS_EXPIRE_MI = 365 * 24 * 60 * 60 * 1000L;

    public static final String PAY_BODY = "礼遇圈";
    public static final String PAY_SUBJECT_ORDER = "-充值";

    public static final String PAY_CON_ORDER = "-寄售台商品购买";


    public static final String PAY_ITEM_ORDER = "-商品购买";

    // 后台用户session名称
    public static final String SESSION_KEY = "J84JDfdsf34wJSDKJdsf";

    public static final String USER_TOKEN_ERROR = "登录信息无效或已过期,请重新登录!";

//    //    商品状态，1-正常，2-下架，3-删除
//    public static final byte ITEM_NORMAL = 1;
//    public static final byte ITEM_DOWN = 2;
//    public static final byte ITEM_DEL = 3;

    // app用户账号状态
    public static final byte USER_NORMAL = 1; // 正常
    public static final byte USER_INVALID = 2; // 不可用

    // 购物车操作
    public static final int DECR = 1; // 加1
    public static final int INCR = 1; // 减1
    public static final int PAY_ORDER = 1;

    public static String genName(FriendVo friend) {
        String name = friend.getRemark();
        if (StringUtils.isEmpty(name)) {
            return friend.getNickname();
        }
        return name;
    }

    /* 文章类型 1 banner，2 星座，3 首页普通文章 4 商城首页普通文章*/
    public interface ARTICLE_TYPE {
        byte BANNER = 1;
        byte SIGN = 2;
        byte ARTICLE = 3;
        byte MALL = 4;
    }

    /*是否从购物车购买 1 是 2 不是*/
    public interface ISSHOPPINGCART {
        byte YES = 1;
        byte NO = 2;
    }

    /* 背包物品索要/交换操作 */
    public interface BP_ITEM {
        byte DEFAULT = 0;    // 默认无操作
        byte AGREE = 1;      // 同意
        byte REFUSE = 2;    // 拒绝
    }

    /* 背包物品索要状态 */
    public interface FOR_RECORD_STATUS {
        byte ASK_FOR_THE = 1;    // 1-索要中
        byte ACCESS = 2;      // 2-索要成功
        byte FAIL = 3;    // 3-索要失败
    }

    /* 背包物品交换 */ //礼品添加状态，1-用户提交，2-好友提交，3-已完成，4-交换失败
    public interface GIFT_EXCHANGE {
        byte USER_EX = 1;      // 1-用户提交
        byte FRIEND_EX = 2;      // 2-好友提交
        byte ACCESS = 3;      // // 3-已完成
        byte FAIL = 4;    // 4-交换失败
    }

    /* 商品的状态 */
    public interface ITEM {
        byte NORMAL = 1;    // 正常
        byte DOWN = 2;      // 下架
        byte DELETE = 3;    // 已删除
    }

    /* 寄售台商品的状态 */ //1 上架 2 已购买未支付 3 交易完成 4 已下架
    public interface CONSIGNMENT_ITEM {
        byte UP = 1;    // 上架
        byte NO_PAY = 2; //已购买未支付
        byte PAY = 3; //交易完成
        byte DOWN = 4;      // 下架
    }

    /*充值订单状态*/
    //充值状态，1-未支付，2-已支付',
    public interface CHARGE_ORDER_STATUS {
        byte NO_PAY = 1;
        byte PAY = 2;

    }

    /*折现状态 1-折现成功，2-折现失败，3-取消折现 -1删除折现记录*/
    public interface DISCOUNT_STATUS {
        byte DISCOUNTING = 1;
        byte DONE = 2;
        byte CANCEL = 3;
        byte DELETE = 3;
    }

    /*背包物品折现率*/
    public interface DISCOUNT_RATE {
        double DISCOUNTING = 0.80;
        double DISCOUNTING_SJ = 0.05;
    }

    /*商品订单状态 */
//    订单状态: 1-未完成（未付款），2-已完成（已付款） ，3-已取消,4-已删除
    public interface ORDER_STATUS extends ORDER_BASE_STATUS {
        int CANCELED = 3;
        int DELETED = 4;
    }

    /* 商品订单支付状态 */
//    订单状态: 1-未付款，2-已付款 ,3已取消 ,4-已删除
    public interface ORDER_BASE_STATUS {
        int NO_PAY = 1; // 未支付
        int PAID = 2;
    }

    /* 订单提货状态 */ //1-待发货 2-已发货 3-已收货，未评价，4-已经评论，5-取消，6-删除（1.2未完成，3.4已完成）
    public interface ORDER_DELIVER {
        byte NO_DELIVER = 1; // 待发货
        byte DELIVER = 2; // 已发货
        byte RECEIVE = 3; // 已收货，未评价
        byte COMMENT = 4; // 已经评论
        byte CANCLED = 5; // 取消
        byte DELETED = 6; // 删除
    }

    /*背包类型*/ //1 物品 2 虚拟物品 3 优惠券
    public interface BACKPACK_TYPE {
        byte ITEM = 1;
        byte VIRTUAL_ITEM = 2;
        byte DISCOUNT_COUPON = 3;
    }

    /* 叮当退款状态 */
    // 1-未申请退款, 2-已申请退款, 3-退款处理中, 4-退款成功, 5-退款失败
    public interface ORDER_REFUND {
        int NONE = 1;
        int APPLY = 2;
        int HANDLE = 3;
        int SUCCESS = 4;
        int FAILED = 5;
    }

    /* 订单创建方式 */
    public interface ORDER_CREATE_TYPE {
        byte APP = 1;       // app创建
        byte AUCTION = 2;   // 拍卖自动创建
    }

    /* 订单配送方式 */
    public interface ORDER_DISPATCH {
        byte AUTO = 1; // 用户自取
        byte DISPATCH = 2; // 配送
    }


    /* 初始订单号 */
    public static final long ORDER_NO_INIT = 10000L;
    public static final long AUCTION_ORDER_NO_INIT = 10000L;

    /* 支付类型 */
    public interface PAY_TYPE {
        int WX = 24656;// 微信
        int ALI = 78990; //支付宝
        int yue = 98001; //钱包余额
        int LIHUA = 87661; //礼花支付
        int CARD = 36666; // 会员卡支付
    }

//    public static void main(String[] args) {
//        System.out.println((byte)PAY_TYPE.yue);
//    }

    /* 订单类型 */   //1-充值订单，2-商品订单,3-寄售台订单 4-会员卡充值订单
    public interface ORDER_TYPE {
        byte CHARGE = 1;// 充值订单
        byte ITEM = 2; //商品订单
        byte CON_ITEM = 3;//寄售台订单
        byte MEMBER_CHARGE = 4;// 会员卡充值订单
    }

    /*寄售台卖家/买家类型*/  //1-卖家  2-买家
    public interface SELLER_BUYER {
        byte SELLER = 1;//卖家
        byte BUYER = 2;//买家
    }

    /*寄售台商家订单状态*/ //1-全部 ，2-交易中，3-已完成
    public interface BUSINESS_ORDER_STATUS {
        byte ALL = 1;//
        byte TRADING = 2;//
        byte DONE = 3;//完成
    }

    //备忘录事件是否循环
    public interface MEMO {
        byte YES = 1;//循环
        byte NO = 2;//不循环
    }

    /*首页Banner状态*/
    public interface HOME_ITEM_STATUS {
        byte NORMAL = 1;//正常
        byte FORBID = 2;//禁用
    }

    /*商家审核状态*/
    public interface MERCHANT_STATUS {
        byte NORMAL = 1;//默认
        byte IN_REVIEW = 2;//审核中
        byte PASS = 3; // 审核通过
        byte NO_PASS = 4; //审核不通过
    }

//    public static String genPayUrl(int type, int orderType) {
//        String url;
//        if (orderType == 0) { // 商品订单
//            if (type == PAY_TYPE.ALI) {
//                url = "order/ali";
//            } else {
//                url = "order/wx";
//            }
//        } else { // 拍卖订单
//            if (type == PAY_TYPE.ALI) {
//                url = "auction/ali";
//            } else {
//                url = "auction/wx";
//            }
//        }
//        return PropertiesUtil.getServerUrl() + url;
//    }

    /**
     * 是否匿名评论
     */
    public interface ANONYMITY {
        byte YES = 2;
        byte NO = 1;
    }

    // 提现类型
    public interface EARNINGS_TYPE {
        short EARNINGS = 1; // 收益
        short WITHDRAW = 2; // 提现
        short WITHDRAW_FAILED = 3; // 提现失败
    }

    // 提现状态，1-申请提现，2-提现成功，3-提现失败
    public interface WIDTH_STATUS {
        short APPLY = 1; // 申请
        short SUCCESS = 2; // 成功
        short FAILED = 3; // 失败
        short DEL = 5; // 已删除

    }


    // 短信验证码类型
    public interface SMS_TYPE {
        int REGISTER = 1;
        int REST_PWD = 2;
        int THIRD = 3;
    }

    // qpp用户注册类型
    public interface ACCOUNT_TYPE {
        byte PHONE = 1;
        byte QQ = 2;
        byte WX = 3;
    }

    public interface EARNIGS_OVER {
        byte NORMAL = 1;
        byte OVER = 2;
    }

    public static String genNickname() {
        return "zmall" + (int) (Math.random() * 1000000);
    }

    public static String genAvatar() {
        return "avatar/avatar.jpg";
    }

    /// 订单类型
    public interface ORDER_NO_TYPE {
        int ITEM = 3;
        int AUCTION = 4;
    }

    // 拍卖订单支付状态
    public interface AUCTION_ORDER_PAY_STATUS {
        byte NO_PAY = 1;
        byte PAIED = 2;
        byte REFUND = 3;
        byte REFUND_FAILED = 4;
    }

    // 拍卖订单状态
    public interface AUCTION_RDER_STATUS {
        int DEFAULT = 1;
        int JOIN = 2;
        int SUCCESS = 3;
    }

    // 拍卖状态
    public interface AUCTION_STATUS {
        int DEFAULT = 1;
        int RUNNING = 2;
        int END = 3;
        int CACELD = 4;
    }

    // 退款操作类型
    public interface REFUND_OPERATION {
        byte AGREE = 1;  // 同意退款申请
        byte REFUSE = 2; // 拒绝退款申请
        byte CONFIRM = 3; // 确认退款完成
    }

    /**
     * 订单支付状态
     */
    public interface ORDER_PAY_STATUS {
        byte NO_PAY = 1; // 未支付定金
        byte PAID = 2;   // 已支付定金
        byte DECLINE = 3; // 线下已付款
    }

    public interface ADMIN_TYPE {
        byte ADMIN = 1;
        byte AGENT = 2;
    }

    /**
     * 通知状态消息处理状态 1 待验证 2 同意 3 拒绝 4 已回复
     */
    public interface FRIEND_NOTIFY_STATUS {
        byte NONE = 0;
        byte DEFAULT = 1;
        byte AGREE = 2;
        byte REFUSE = 3;
        byte REPALYED = 4;
    }

    public interface MOMENT_COMMENT_TYPE {
        byte COMMENT = 1;
        byte REPLY = 2;
    }

    /**
     * 礼物赠送类型
     */
    public interface GIFT_SEND_TYPE {
        byte NOW = 1; // 立即赠送
        byte TARGET_TIME = 2; // 按时间赠送
        byte WX_FRIEND = 3; // 小程序选择微信好友赠送
        byte WX_FRIEND_RANDOM = 4; // 小程序随机赠送
    }

    /**
     * 礼物赠送状态
     */
    public interface GIFT_STATUS {
        byte CANCEL = 0; // 已取消
        byte WAIT = 1; // 未赠送
        byte PART_SEND = 2; // 部分赠送
        byte SEND = 3; // 全部一赠送
        byte EXPIRE = 4; // 超时赠送失败
        byte FALIED = 5; // 赠送失败!
    }

    /**
     * 礼物物品的类型
     */
    public interface GIFT_M_TYPE {
        byte MAIN = 1;
        byte SUB = 2;
    }

    //    '记录类型 1-充值，2-商品折现，3-虚拟物品折现，4-寄售台出售物品，5-购买商品，6-寄售台物品购买,7虚拟物品购买',
    public enum WALLET_RECORD {
        CHARGE(1, "余额充值"),
        ITEM_DISCOUNT(2, "商品折现"),
        VAR_ITEM_DISCOUNT(3, "虚拟商品折现"),
        CONSIGNMENT_ITEM(4, "寄售台出售物品"),
        BUY_ITEM(5, "购买商品"),
        CON_BUY_ITEM(6, "寄售台物品购买"),
        CON_BUY_VIRITEM(7, "虚拟物品购买");

        public int type;
        public String explain;

        WALLET_RECORD(int type, String explain) {
            this.type = type;
            this.explain = explain;
        }
    }



    public enum FIREWORKS_RECORD {
        ADDFRIEND(1, "邀请好友"),
        FRIEND_DISCOUNT(2, "好友消费"),
        our_ITEM_DISCOUNT(3, "本人购买商品");


        public int type;
        public String explain;

        FIREWORKS_RECORD(int type, String explain) {
            this.type = type;
            this.explain = explain;
        }
    }



    /**
     * 消息类型
     */
    public enum APP_MESSAGE_TYPE {
        GIFT(1, "礼物通知"),
        SYS(2, "系统通知"),
        CONSIGNMENT(3, "寄售台通知"),
        GIFT_CHANGE(4, "礼物交换通知");

        int type;
        String title;

        APP_MESSAGE_TYPE(int type, String title) {
            this.title = title;
            this.type = type;
        }

        public byte type() {
            return (byte) type;
        }

        public String title() {
            return title;
        }
    }

    /**
     * 消息已读状态
     */
    public interface MESSAGE_READ {
        byte NO = 2;
        byte YES = 1;
    }

    /**
     * 背包来源
     */
    public interface BP_ITEM_FROM {
        byte ITEM_ORDER = 1; // 商品购买
        byte VIR_ITEM_ORDER = 2; // 虚拟物品购买
        byte COUPON_SEND = 3; // 优惠券赠送
        byte GIFT = 4; // 礼物赠送
        byte GIFT_CHANGE = 5; // 礼物互换
        byte CONSIGNMENG_ORDER = 6; // 寄售台商品购买
        byte ASK_FOR = 7; //向好友索要物品
        byte WELFARE = 8; //福利领取
    }
}

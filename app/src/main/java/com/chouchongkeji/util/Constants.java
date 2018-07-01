package com.chouchongkeji.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

/**
 * @author yichenshanren
 * @date 2017/10/15
 */

public class Constants {
    //默认头像
    public static final String DEFALUT_AVATAR = "avatar.jpg";
    public static final String GROUP_DEFAULT_NAME = "未分组";

    public static final String getRandomName() {
        return RandomStringUtils.randomNumeric(7);
    }

    /* app用户购买商品之后的年收益利率 */
    public static final float USER_EARNINGS_RATE = 0.002F;
    public static final float AGENT_EARNINGS_RATE = 0.003F;

    // 用户收益有限期 一年
    public static final int USER_EARNIGS_EXPIRE = 12;
    public static final long USER_EARNIGS_EXPIRE_MI = 365 * 24 * 60 * 60 * 1000L;

    // 退款类型
    public final static HashMap<Integer, String> REFUND_TYPES = new HashMap<>();
    // 退款说明
    public static final String REFUND_INFO = "退款说明：\n凡是在我们平台购买商品的客户，而且消费的客户满足于从消费之日到现在是在3个月内，如果有质量问题或者您不满意，您都可以申请退款，申请通过后，我们将在7个工作日内退款给客户。\n" +
            "退款流程：\n" +
            "1、APP平台在线支付，如涉及到银行信息乾亿珠宝会依据银行及相关机构已经建立的条例处理退款，为了保证客户账户金额的安全，我们均会安排原卡原退。\n" +
            "2、微信支付和支付宝支付用余额付款的，我们的退款将直接退回到您的余额里面。";
    public static final String AGENTPWD = "FDSJKJK839dskfjkdsfjg.,.vf;lr;i43o25efskedjrfkew4j32kjfrkesfds,mcfds,";
    public static final String ADMINPWD = "43./;'rewff434rlrfFDSFDedsrfSfds3243sDSkehgdjrfke432dsewDS,mcfds,";

    static {
        REFUND_TYPES.put(1, "不想要了");
        REFUND_TYPES.put(2, "质量出现严重问题");
        REFUND_TYPES.put(3, "是假的");
    }

    public static final String PAY_BODY = "礼遇圈";
    public static final String PAY_SUBJECT_ORDER = "-充值";


    public static final String PAY_ITEM_ORDER = "-商品购买";

    // 后台用户session名称
    public static final String SESSION_KEY = "J84JDfdsf34wJSDKJdsf";

    public static final String USER_TOKEN_ERROR = "登录信息无效或已过期,请重新登录!";

    //    商品状态，1-正常，2-下架，3-删除
    public static final byte ITEM_NORMAL = 1;
    public static final byte ITEM_DOWN = 2;
    public static final byte ITEM_DEL = 3;

    // app用户账号状态
    public static final byte USER_NORMAL = 1; // 正常
    public static final byte USER_INVALID = 2; // 不可用

    // 购物车操作
    public static final int DECR = 1; // 加1
    public static final int INCR = 1; // 减1
    public static final int PAY_ORDER = 1;

    /* 商品的状态 */
    public interface ITEM {
        byte NORMAL = 1;    // 正常
        byte DOWN = 2;      // 下架
        byte DELETE = 3;    // 已删除
    }

    /*充值订单状态*/
    //充值状态，1-未支付，2-已支付',
    public interface CHARGE_ORDER_STATUS {
        byte NO_PAY = 1;
        byte PAY = 2;
    }


    /* 订单状态 */
//    订单状态: 1-未完成（未付款），2-已完成（已付款） ，3-已取消,4-已删除
    public interface ORDER_STATUS extends ORDER_BASE_STATUS{
        int CANCELED = 3;
        int DELETED = 4;
    }

    /* 订单支付状态 */
//    订单状态: 1-未付款，2-已付款
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

//    /* 订单评论状态 */
//    public interface ORDER_COMMENT extends ORDER_DELIVER {
//        byte NO_COMMENT = 4; // 未评论
//        byte COMMENT = 5; // 已经评论
//    }


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
        int WX = 24656;//
        int ALI = 78990;
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

    // 提现状态
    public interface WIDTH_STATUS {
        short APPLY = 1; // 申请
        short HANDLING = 2; // 处理中
        short SUCCESS = 3; // 成功
        short FAILED = 4; // 失败
        short DEL = 5; // 已删除

    }

    // 支付平台类型
//    public interface PAY_PALATFORM {
//        int WX = 1;
//        int ALI = 2;
//    }

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
}

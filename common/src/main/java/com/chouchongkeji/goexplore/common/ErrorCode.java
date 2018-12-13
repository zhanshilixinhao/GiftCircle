package com.chouchongkeji.goexplore.common;

/**
 * @author yichenshanren
 * @date 2017/9/28
 */

public enum ErrorCode {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    MISSING_PARAMETER(4, "MISSING_PARAMETER"),
    SIGN_ERROR(5, "SIGN_ERROR"),

    SUCCESS_SOCKET(200, "SUCCESS_SOCKET"),
    NO_AUTH(401, "NO_AUTH"),
    NOT_FOUND(404, "NOT_FOUND"),
    UNKNOWN_ERROR(500, "NOT_FOUND"),

    /* 用户相关 错误码 */
    NEED_LOGIN(1002, "NEED_LOGIN"), // 需要登录
    PERMISSION_DENIED(1003, "PERMISSION_DENIED"), // 权限拒绝
    NEED_BIND_PHONE(1004, "NEED_BIND_PHONE"), // 需要绑定手机号码
    USER_NOT_EXIST(1005, "USER_NOT_EXIST"),   // 用户不存在
    SMS_CODE_ERROR(1006, "SMS_CODE_ERROR"),   // 短信验证码错误
    PHONE_EXISTED(1007, "PHONE_EXISTED"),   // 手机号已存在
    CLIENT_NOT_EXIST(1008, "CLIENT_NOT_EXIST"),   // client id不存在
    INVALID_REFRESH_TOKEN(1009, "INVALID_REFRESH_TOKEN"),   // 无效的refresh token
    SMS_CODE_SEND_ERROR(1010, "SMS_CODE_SEND_ERROR"),   // 短信验证码错误
    IDCARD_NEED(1011, "IDCARD_NEED"),   // 短信验证码错误
    JOIN_TEAM(1012, "JOIN_TEAM_ERROR"),   // 用户加入战队失败
    YUE_NOT_EN(1013, "余额不足"),   // 用户加入战队失败

    CACHE_ERROR(2001, "CACHE_ERROR"),   // 缓存时发生的错误
    /* 订单相关 */
    SIT_ORDER_ERROR(3001, "SIT_ORDER_ERROR"),   // 座位订单
    PACKAGE_ORDER_ERROR(4001, "PACKAGE_ORDER_ERROR"),   // 座位订单
    CONTEST(5001, "CONTEST"),   // 赛事相关

    WALLET_ERROR(5001, "WALLET_ERROR"),   // 钱包错误


    PRODUCT_ORDER(6001, "PRODUCT_ORDER"),   // 商品订单

    GAME_ERROR(7001, "GAME_ERROR"),   // 游戏错误码
    GAME_NODE_RECORD_NOT_EXIST(7002, "GAME_NODE_RECORD_NOT_EXIST"), // 游戏节点记录不存在
    GAME_NODE_RECORD_NOT_ING(7003, "GAME_NODE_RECORD_NOT_ING"), // 游戏节点记录不在进行中
    GAME_NODE_NOT_EXIST(7004, "GAME_NODE_NOT_EXIST"), // 游戏节点不存在
    GAME_NODE_ELEM_NOT_EXIST(7005, "GAME_NODE_ELEM_NOT_EXIST"), // 游戏节点元素不存在
    GAME_NODE_COMMIT_CMD_ERR(7006, "GAME_NODE_COMMIT_CMD_ERR"), // 游戏节点提交命令错误
    GAME_NODE_COMMIT_ONCE(7007, "GAME_NODE_COMMIT_ONCE"), // 游戏节点已提交且只能提交一次
    GAME_NODE_MAP_NO_STORY(7008, "GAME_NODE_MAP_NO_STORY"), // 提交地图点击没有剧情
    GAME_NODE_UNKNOWN(7009, "UNKNOWN"), // 未知错误
    GAME_NODE_STORY_RECORD_NOT_EXIST(7010, "GAME_NODE_STORY_RECORD_NOT_EXIST"), // 剧情记录不存在
    GAME_NODE_STORY_COMPLETED(7011, "GAME_NODE_STORY_COMPLETED"), // 剧情记录已完成
    GAME_ELE_FIND_ERR(7012, "GAME_ELE_FIND_ERR"), // 元素详情查询失败
    GAME_TEAM_DETAIL_ERR(7013, "GAME_TEAM_DETAIL_ERR"), // 战队详情获取失败
    GAME_OVER(7014, "GAME_OVER"), // 游戏已结束

    // Http错误
    HTTP_NOT_AUTH(401, "HTTP_METHOD_NOT_SUPPORT"),
    HTTP_METHOD_NOT_SUPPORT(405, "HTTP_METHOD_NOT_SUPPORT");

    private int code;
    private String msg;

    private static int USER_CODE = 1000;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

package com.chouchongkeji.service.backpack.gift.vo;

/**
 * 微信 好友领取礼物 返回内容
 *
 * @author yichen
 * @date 18-10-31 下午5:43
 **/

public class WXGetGiftResVo<T> {

    private Byte type;
    private T giftInfo; // 礼物信息

    private Integer status;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public T getGiftInfo() {
        return giftInfo;
    }

    public void setGiftInfo(T giftInfo) {
        this.giftInfo = giftInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

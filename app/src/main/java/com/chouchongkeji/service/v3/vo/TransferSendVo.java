package com.chouchongkeji.service.v3.vo;

import com.chouchongkeji.dial.pojo.v3.TransferSend;

/**
 * @author linqin
 * @date 2019/12/2
 */

public class TransferSendVo extends TransferSend {

    private String title;
    /**
     * 转赠对象头像/昵称
     */
    private String avatar;

    private Integer targetUserId;

    private String nickname;

    /**
     * 转赠卡号
     */
    private Long cardNo;
    /**
     * 接收卡号
     */
    private Long targetCardNo;

    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public Long getTargetCardNo() {
        return targetCardNo;
    }

    public void setTargetCardNo(Long targetCardNo) {
        this.targetCardNo = targetCardNo;
    }
}

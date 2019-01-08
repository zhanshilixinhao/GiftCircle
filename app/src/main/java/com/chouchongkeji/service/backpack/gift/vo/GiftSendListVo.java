package com.chouchongkeji.service.backpack.gift.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;
import java.util.List;

/**
 * 赠送礼物列表
 * @author linqin
 * @date 2019/1/2 9:31
 */

public class GiftSendListVo {

    private Integer id; // 送礼记录id

    private Integer userId; //送礼者id

    private String greetting; // 祝福语

    private Byte type; //

    private String event; // 事件名称

    private Date targetTime; //目标赠送时间

    private Byte status;

    private Date updated;

    private String nickname;

    @ImgUrl
    private String avatar;

    private Byte flag; //1赠送记录  2 收礼记录

    private List<GiftRecordDetailVo> detail;

//    private Integer quantity; //附属品数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getGreetting() {
        return greetting;
    }

    public void setGreetting(String greetting) {
        this.greetting = greetting;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }


    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public List<GiftRecordDetailVo> getDetail() {
        return detail;
    }

    public void setDetail(List<GiftRecordDetailVo> detail) {
        this.detail = detail;
    }
}

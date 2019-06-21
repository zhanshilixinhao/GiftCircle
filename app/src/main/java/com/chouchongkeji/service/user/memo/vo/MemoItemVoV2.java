package com.chouchongkeji.service.user.memo.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/22
 */

public class MemoItemVoV2 implements Cloneable {

    private Integer id;
    private Integer userId; //创建者id
    private Date targetTime;
    private String detail;  //活动详情

    private String users;
    private Date created;
    private String nickname; //创建者昵称
    private Integer count; // 邀请人数
    @ImgUrl
    private String avatar;  //创建者头像
    private Integer eventTypeId;  //事件类型id
    private String eventTypeName;  //事件类型名称

    private List<Users> userList; //被邀请的人

    private Integer type; //1 自己创建 2被邀请 3 节日

    private Float hortNum;// 互动值

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

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public Float getHortNum() {
        return hortNum;
    }

    public void setHortNum(Float hortNum) {
        this.hortNum = hortNum;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }



    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
}

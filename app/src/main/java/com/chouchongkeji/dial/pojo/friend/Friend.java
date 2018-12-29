package com.chouchongkeji.dial.pojo.friend;

import com.sun.org.apache.bcel.internal.generic.FLOAD;

import java.util.Date;

public class Friend {
    private Integer id;

    private Integer userId;

    private Integer friendUserId;

    private String remark;

    private String relationship;

    private Integer groupId;

    private Integer sort;

    private Date created;

    private Date updated;

    private Float heartNum;

    public Friend(Integer id, Integer userId, Integer friendUserId, String remark, String relationship, Integer groupId, Integer sort, Date created, Date updated,Float heartNum) {
        this.id = id;
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.remark = remark;
        this.relationship = relationship;
        this.groupId = groupId;
        this.sort = sort;
        this.created = created;
        this.updated = updated;
        this.heartNum = heartNum;
    }

    public Friend() {
        super();
    }

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

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Float getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Float heartNum) {
        this.heartNum = heartNum;
    }
}

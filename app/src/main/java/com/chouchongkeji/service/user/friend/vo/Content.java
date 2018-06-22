package com.chouchongkeji.service.user.friend.vo;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public class Content {

    private Integer groupId;
    private String remark;

    public Content() {
    }

    public Content(Integer groupId, String remark) {
        this.groupId = groupId;
        this.remark = remark;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

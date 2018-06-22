package com.chouchongkeji.service.user.vo;

import java.util.Objects;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public class UserTagVo {

    private Integer tagId;
    private String tag;
    private Integer num;
    private Byte type;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTagVo)) return false;
        UserTagVo userTagVo = (UserTagVo) o;
        return Objects.equals(getTagId(), userTagVo.getTagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTagId());
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}

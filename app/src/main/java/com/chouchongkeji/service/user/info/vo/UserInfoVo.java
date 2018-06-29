package com.chouchongkeji.service.user.info.vo;

import com.chouchongkeji.service.user.friend.vo.MediaVo;
import com.yichen.auth.jackson.ImgUrl;

import java.util.HashSet;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/29
 */

public class UserInfoVo {

    private Integer userId;

    private String phone;

    @ImgUrl
    private String avatar;

    private String nickname;

    private Integer age;

    private Byte gender;

    private String signature;

    private String district;

    private String wxid;

    private Integer isFriend = 1;

    private String remark;

    private String relationship;

    private HashSet<UserTagVo> tags;

    @ImgUrl
    private List<MediaVo> recentMoments;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public HashSet<UserTagVo> getTags() {
        return tags;
    }

    public void setTags(HashSet<UserTagVo> tags) {
        this.tags = tags;
    }

    public List<MediaVo> getRecentMoments() {
        return recentMoments;
    }

    public void setRecentMoments(List<MediaVo> recentMoments) {
        this.recentMoments = recentMoments;
    }

}

package com.chouchongkeji.service.user.friend.vo;

import com.chouchongkeji.service.backpack.gift.vo.GiftBaseVo;
import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public class MomentVo {

    private Integer momentId;
    private Integer createUserId;
    private String content;

    private List<MediaVo> medias;
    private Date created;
    private Date updated;
    private Integer selfUserId;
    private String nickname;

    private Byte showGift;

    private Integer isFriend;

    private Byte type; //1 全部 2 只获取好友的

    @ImgUrl
    private String avatar;
    private String remark;
    private String relationship;

    private List<PraiseVo> praiseUsers;

    private List<CommentVo> comments;

    private List<GiftBaseVo> gifts;

    public MomentVo() {

    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MediaVo> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaVo> medias) {
        this.medias = medias;
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

    public Integer getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Integer selfUserId) {
        this.selfUserId = selfUserId;
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

    public List<PraiseVo> getPraiseUsers() {
        return praiseUsers;
    }

    public void setPraiseUsers(List<PraiseVo> praiseUsers) {
        this.praiseUsers = praiseUsers;
    }

    public List<CommentVo> getComments() {
        return comments;
    }

    public void setComments(List<CommentVo> comments) {
        this.comments = comments;
    }

    public List<GiftBaseVo> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftBaseVo> gifts) {
        this.gifts = gifts;
    }

    public Byte getShowGift() {
        return showGift;
    }

    public void setShowGift(Byte showGift) {
        this.showGift = showGift;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}

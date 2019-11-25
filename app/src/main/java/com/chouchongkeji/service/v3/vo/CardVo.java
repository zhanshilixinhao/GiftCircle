package com.chouchongkeji.service.v3.vo;


import com.chouchongkeji.dial.pojo.v3.Store;
import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2019/10/24
 */
public class CardVo {
    /**
     * 会员卡用户关联表id
     */
    private Integer id;

    private Integer membershipCardId;

    private Integer userId;

    private String phone;

    private BigDecimal balance;

    private Date created;

    private Long cardNo;

    private String title;

    private String summary;

    private String colour;

    @ImgUrl
    private String logo;

    private Byte type;

    private String storeIds;

    private List<Store> stores;

    private Integer grade;

    private String summaryGrade;// 等级说明

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMembershipCardId() {
        return membershipCardId;
    }

    public void setMembershipCardId(Integer membershipCardId) {
        this.membershipCardId = membershipCardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }


    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSummaryGrade() {
        return summaryGrade;
    }

    public void setSummaryGrade(String summaryGrade) {
        this.summaryGrade = summaryGrade;
    }
}

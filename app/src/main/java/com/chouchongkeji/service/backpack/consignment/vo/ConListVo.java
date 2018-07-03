package com.chouchongkeji.service.backpack.consignment.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/7/3
 */
public class ConListVo {
    private Integer targetId;

    private  Byte type;

    @ImgUrl
    private String cover;

    private String title;

    private BigDecimal newset;

    private BigDecimal hight;

    private BigDecimal low;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getNewset() {
        return newset;
    }

    public void setNewset(BigDecimal newset) {
        this.newset = newset;
    }

    public BigDecimal getHight() {
        return hight;
    }

    public void setHight(BigDecimal hight) {
        this.hight = hight;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }
}

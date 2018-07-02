package com.chouchongkeji.service.backpack.item.vo;

import com.chouchongkeji.dial.pojo.iwant.receiveAddress.Shipping;
import com.chouchongkeji.service.kdapi.KdResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/29
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReOrderDetailVo {
    private Integer id;

    private Integer userId;

    private Integer itemId;

    private Long bpId;

    private Integer skuId;

    private Long orderNo;

    private String title;

    private String description;

    @ImgUrl
    private String cover;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Integer quantity;

    private Shipping receiveInfo;

    private LogisticsInfoVo logisticsInfo;

    private List<KdResult.DataBean> logisticsTrace;

    private Byte status;

    private Date created;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<KdResult.DataBean> getLogisticsTrace() {
        return logisticsTrace;
    }

    public void setLogisticsTrace(List<KdResult.DataBean> logisticsTrace) {
        this.logisticsTrace = logisticsTrace;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Shipping getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(Shipping receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public LogisticsInfoVo getLogisticsInfo() {
        return logisticsInfo;
    }

    public void setLogisticsInfo(LogisticsInfoVo logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}

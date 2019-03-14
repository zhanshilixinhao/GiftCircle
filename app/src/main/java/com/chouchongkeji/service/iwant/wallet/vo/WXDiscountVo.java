package com.chouchongkeji.service.iwant.wallet.vo;

import com.chouchongkeji.dial.pojo.user.memo.WXDiscount;
import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/3/14 21:27
 */

public class WXDiscountVo extends WXDiscount {

    @ImgUrl
    private String cover;
    private String title;
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

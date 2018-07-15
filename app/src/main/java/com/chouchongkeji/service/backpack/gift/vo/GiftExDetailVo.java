package com.chouchongkeji.service.backpack.gift.vo;

import java.util.List;

/**
 * @author linqin
 * @date 2018/7/15
 */
public class GiftExDetailVo extends GiftExListVo {
    private List<GiftExItemVo> exchangeGifts;

    private List<GiftExItemVo> wantGifts;

    private List<GiftExItemVo> submitGifts;

    public List<GiftExItemVo> getExchangeGifts() {
        return exchangeGifts;
    }

    public void setExchangeGifts(List<GiftExItemVo> exchangeGifts) {
        this.exchangeGifts = exchangeGifts;
    }

    public List<GiftExItemVo> getWantGifts() {
        return wantGifts;
    }

    public void setWantGifts(List<GiftExItemVo> wantGifts) {
        this.wantGifts = wantGifts;
    }

    public List<GiftExItemVo> getSubmitGifts() {
        return submitGifts;
    }

    public void setSubmitGifts(List<GiftExItemVo> submitGifts) {
        this.submitGifts = submitGifts;
    }
}

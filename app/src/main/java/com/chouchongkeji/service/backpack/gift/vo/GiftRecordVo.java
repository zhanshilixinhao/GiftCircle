package com.chouchongkeji.service.backpack.gift.vo;

import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/9
 */

public class GiftRecordVo {

    private List<GiftRecordItemVo> records;

    private GiftInoutVo inout;

    public List<GiftRecordItemVo> getRecords() {
        return records;
    }

    public void setRecords(List<GiftRecordItemVo> records) {
        this.records = records;
    }

    public GiftInoutVo getInout() {
        return inout;
    }

    public void setInout(GiftInoutVo inout) {
        this.inout = inout;
    }
}

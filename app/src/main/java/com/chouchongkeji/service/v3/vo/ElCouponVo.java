package com.chouchongkeji.service.v3.vo;

import com.chouchongkeji.dial.pojo.v3.Store;
import com.yichen.auth.jackson.ImgUrl;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2020/2/10 10:55
 */
@Data
public class ElCouponVo {

    private Long num;

    private Integer couponId;

    private Integer userId;

    private Integer quantity;

    private String code;

    private Date created;

    private String title;

    private String summary;

    @ImgUrl
    private String logo;

    private String storeIds;

    private Date date;

    private String storeName;

    private Byte status;

}

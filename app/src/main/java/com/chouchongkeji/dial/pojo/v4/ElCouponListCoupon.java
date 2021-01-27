package com.chouchongkeji.dial.pojo.v4;

import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 礼包优惠券关联表
 * @Author Lxh
 * @Date 2020/9/30 13:12
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "el_coupon_list_coupon")
public class ElCouponListCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer elCouponListId;

    private Integer couponId;

    private Integer quantity;

    @Transient
    private String couponName;

    @Transient
    private Date startTime;

    @Transient
    private Date endTime;

    @Transient
    private String Logo;

    @Transient
    private Integer day;

    public static final String COLUMN_EL_COUPON_ID = "elCouponListId";
}

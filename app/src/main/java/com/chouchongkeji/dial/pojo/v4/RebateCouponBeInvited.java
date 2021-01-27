package com.chouchongkeji.dial.pojo.v4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/21 11:07
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rebate_coupon_be_invited")
public class RebateCouponBeInvited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long rebateCouponId;

    private Integer lowUserId;

    private BigDecimal helpPercentage;

    private Date created;

    private Date updated;

    @Transient
    private String lowUserName;

    @Transient
    private String lowUserPhone;

    @Transient
    private String lowUserLogo;

    @Transient
    private String  createdTime;

    public static final String REBATE_COUPON_ID = "rebateCouponId";

}
